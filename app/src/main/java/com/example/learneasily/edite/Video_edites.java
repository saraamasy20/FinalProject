package com.example.learneasily.edite;

import androidx.appcompat.app.AppCompatActivity;

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

public class Video_edites extends AppCompatActivity {



    EditText name_video ;
    Button edit_video ;
    TextView names_of_video ;
    FirebaseFirestore db ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_edites);

        db = FirebaseFirestore.getInstance();
        name_video =findViewById(R.id.edit_video_name);
        names_of_video = findViewById(R.id.text_view_video);
        edit_video = findViewById(R.id.btn_edit_name_video);

        edit_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_edit_of_ass = (String) names_of_video.getText();
                String name_new = getIntent().getStringExtra("video_name");
                name_edit_of_ass = name_new;
                update_assignment(name_video) ;
            }
        });

    }

    private void update_assignment(EditText name_video) {

        String names = name_video.getText().toString().trim();

        Map<String,Object> info_pdf = new HashMap<>();
        info_pdf.put("name",names);


        db.collection("pdf").whereEqualTo("name",names).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                db.collection("courses").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                        .update("name",info_pdf.get(names));

            }
        });
    }
}