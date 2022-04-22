package com.example.learneasily.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learneasily.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

public class Add_coursesActivity extends AppCompatActivity {



    Button add_cources ;
    EditText nameCourse ;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);

        add_cources =findViewById(R.id.btn_add_name_course);
        nameCourse = findViewById(R.id.edit_text_course_name);


        db = FirebaseFirestore.getInstance();

        add_cources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                add_course(nameCourse);
            }
        });




    }
    private void add_course(EditText name_course) {

        String names = nameCourse.getText().toString().trim();
        String id = nameCourse.getText().toString().trim();


        Map<String,Object> info = new HashMap<>();
        info.put("name",names);
        info.put("id" , id);

        db.collection("courses").add(info).addOnSuccessListener(documentReference ->
        {

            Toast.makeText(Add_coursesActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
            Log.d("TTT" , " Android is Done : "+ documentReference.getId());
        }).addOnFailureListener(e -> {
            Toast.makeText(Add_coursesActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
            Log.d("TTT" , e.getMessage());
        });
    }


    }
