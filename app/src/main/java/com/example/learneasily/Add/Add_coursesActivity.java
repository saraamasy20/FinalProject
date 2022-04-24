package com.example.learneasily.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learneasily.R;
import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_coursesActivity extends AppCompatActivity {



    Button add_cources ;
    EditText nameCourse,namedescription ,idCourse;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_courses);

        add_cources =findViewById(R.id.btn_add_name_course);
        nameCourse = findViewById(R.id.edit_text_course_name);
        namedescription=findViewById(R.id.edit_text_descrption2);
        idCourse=findViewById(R.id.edit_text_id);
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
        String id = idCourse.getText().toString().trim();
        String description = namedescription.getText().toString().trim();

        Map<String,Object> info = new HashMap<>();
        info.put("name",names);
        info.put("id" , id);
        info.put("description" , description);

        CollectionReference course= db.collection("courses");
        course.add(info).addOnSuccessListener(documentReference ->

        {
            Intent i=new Intent(Add_coursesActivity.this, Rc_AssignmentActivity.class);
            i.putExtra("idcourse",documentReference.getId());
startActivity(i);
           // db.collection("courses").document().collection();
           // db.collection("courses").document().collection();
            ///db.collection("courses").document().collection();
            Toast.makeText(Add_coursesActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_coursesActivity.this , Rc_courcesActivity.class);
            startActivity(intent);
            Log.d("TTT" , " Android is Done : "+ documentReference.getId());
        }).addOnFailureListener(e -> {
            Toast.makeText(Add_coursesActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
            Log.d("TTT" , e.getMessage());
        });
    }


    }
