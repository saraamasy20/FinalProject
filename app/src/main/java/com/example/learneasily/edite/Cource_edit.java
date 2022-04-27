package com.example.learneasily.edite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learneasily.Adapter.Courses_adapter;
import com.example.learneasily.Add.Add_coursesActivity;
import com.example.learneasily.R;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.example.learneasily.models.courses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Cource_edit extends AppCompatActivity {
    EditText name_course , descriptions_ass  , name_id;
    Button edit_c ;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cource_edit);

        db = FirebaseFirestore.getInstance();
        name_course = findViewById(R.id.edit_name);
        descriptions_ass =findViewById(R.id.edit_description);
        name_id = findViewById(R.id.id_edit);
        edit_c = findViewById(R.id.btn_edit_name_course);
        courses c = new courses();

        edit_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getData();
                update_assignment();
            }
        });


    }


    private  void  getData()
    {

       String m_name = getIntent().getStringExtra("course_name");
        db.collection("courses").whereEqualTo("name",m_name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                name_course.setText(queryDocumentSnapshots.getDocuments().get(0).toString());
                descriptions_ass.setText(queryDocumentSnapshots.getDocuments().get(0).toString());
                name_id.setText(queryDocumentSnapshots.getDocuments().get(0).toString());


            }
        });
    }



    private void update_assignment() {
        String m_name = getIntent().getStringExtra("course_name");

        Map<String,Object> info_course = new HashMap<>();
        info_course.put("name",name_course.getText());
        info_course.put("descraption" , descriptions_ass.getText());
        info_course.put("id" ,name_id.getText());

        db.collection("courses").whereEqualTo("name",m_name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {


                db.collection("courses").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                        .update("name",info_course.get(name_course),
                                "descraption",info_course.get(descriptions_ass),
                                "id",info_course.get(name_id));

            }
        });

    }



}