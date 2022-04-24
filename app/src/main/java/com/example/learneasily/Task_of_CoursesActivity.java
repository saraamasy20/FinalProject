package com.example.learneasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_PdfActivity;
import com.example.learneasily.Rc.Rc_VideoActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;

public class Task_of_CoursesActivity extends AppCompatActivity {

Button ass , pdf , video;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_of_courses);

        ass = findViewById(R.id.btn_Ass);
        pdf = findViewById(R.id.btn_pdf);
        video =findViewById( R.id.btn_video);

        ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_AssignmentActivity.class);
                startActivity(intent);
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
                Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_VideoActivity.class);
                startActivity(intent);
            }
        });

    }
}