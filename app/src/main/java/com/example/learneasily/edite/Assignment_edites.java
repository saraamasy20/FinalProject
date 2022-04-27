package com.example.learneasily.edite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.learneasily.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Assignment_edites extends AppCompatActivity {
    EditText name_ass , descriptions_ass ;
    Button  edit_ass ;
    TextView names_of_ass ;
    FirebaseFirestore db ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment_edites);
        db = FirebaseFirestore.getInstance();
        name_ass =findViewById(R.id.edit_text_course_name);
        names_of_ass = findViewById(R.id.edit_name_ass);
        descriptions_ass =findViewById(R.id.edit_description_ass);
        edit_ass = findViewById(R.id.btn_edit_name_ass);

        edit_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String name_edit_of_ass = (String) names_of_ass.getText();
               String name_new = getIntent().getStringExtra("ass_name");
                name_edit_of_ass = name_new;
                update_assignment(name_ass ,descriptions_ass ) ;
            }
        });
    }


    private void update_assignment(EditText name_course , EditText  desecription_ass) {
        String names = name_ass.getText().toString().trim();
        String descraption = descriptions_ass.getText().toString().trim();


        Map<String,Object> info_course = new HashMap<>();
        info_course.put("name",names);
        info_course.put("descraption" , descraption);

        db.collection("assignments").whereEqualTo("name",names).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                db.collection("courses").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                        .update("name",info_course.get(names),
                                "descraption",info_course.get(descraption));

            }
        });

    }
}