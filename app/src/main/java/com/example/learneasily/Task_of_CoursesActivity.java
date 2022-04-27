package com.example.learneasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.learneasily.Add.Add_AssignmentActivity;
import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_PdfActivity;
import com.example.learneasily.Rc.Rc_VideoActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Task_of_CoursesActivity extends AppCompatActivity {

Button ass , pdf , video;
    FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_of_courses);
        db =  FirebaseFirestore.getInstance();
        ass = findViewById(R.id.btn_Ass);
        pdf = findViewById(R.id.btn_pdf);
        video =findViewById( R.id.btn_video);
        String idfor=getIntent().getStringExtra("idfor");
        Toast.makeText(Task_of_CoursesActivity.this,idfor,Toast.LENGTH_SHORT).show();

        ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//idfor is the id or the number of the course
                db.collection("courses").whereEqualTo("id",idfor).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_AssignmentActivity.class);
                        //idt is the uid of the course
                        Intent idt= intent.putExtra("idt",queryDocumentSnapshots.getDocuments().get(0).getId());
                        Toast.makeText(Task_of_CoursesActivity.this,queryDocumentSnapshots.getDocuments().get(0).getId(),Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

            }
        });

        pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_PdfActivity.class);
                startActivity(intent);
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.collection("courses").whereEqualTo("id",idfor).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_VideoActivity.class);
                        //idt is the uid of the course
                        Intent idt= intent.putExtra("idt",queryDocumentSnapshots.getDocuments().get(0).getId());
                        Toast.makeText(Task_of_CoursesActivity.this,queryDocumentSnapshots.getDocuments().get(0).getId(),Toast.LENGTH_SHORT).show();

                        startActivity(intent);
                    }
                });
            }
        });

    }
}