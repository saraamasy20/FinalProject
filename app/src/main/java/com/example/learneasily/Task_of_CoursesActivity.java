package com.example.learneasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;

public class Task_of_CoursesActivity extends AppCompatActivity {

Button ass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_of_courses);

        ass = findViewById(R.id.btn_Ass);
        ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Task_of_CoursesActivity.this , Rc_AssignmentActivity.class);
                startActivity(intent);
            }
        });


    }
}