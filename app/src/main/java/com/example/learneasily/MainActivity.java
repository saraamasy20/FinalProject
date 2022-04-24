package com.example.learneasily;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.learneasily.Rc.Rc_courcesActivity;

public class MainActivity extends AppCompatActivity {



    Button teacher , student ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teacher =findViewById(R.id.btn_teacher_main);
        student = findViewById(R.id.btn_student_main);

        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this , Signup_teacherActivity.class);
                startActivity(intent);
            }
        });


        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this , Login_teacherActivity.class);
                startActivity(intent);
            }
        });

    }
}