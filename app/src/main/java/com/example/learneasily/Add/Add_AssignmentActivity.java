package com.example.learneasily.Add;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learneasily.Login_teacherActivity;
import com.example.learneasily.R;
import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_PdfActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.example.learneasily.models.courses;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_AssignmentActivity extends AppCompatActivity {
    FirebaseFirestore db ;
    EditText name_ass , descriptions_ass ;
    Button add_ass ;
    courses course;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);


course =new courses();
        db = FirebaseFirestore.getInstance();
        name_ass =findViewById(R.id.edit_add_name);
        descriptions_ass =findViewById(R.id.edit_add_description);
        add_ass = findViewById(R.id.btn_add_new_ass);
        add_ass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_assignment(name_ass ,descriptions_ass ) ;

            }
        });


    }

    private void add_assignment(EditText name_ass ,EditText  desecription_ass) {

        String names = name_ass.getText().toString().trim();

        String desecription= descriptions_ass.getText().toString().trim();

        Map<String,Object> info_ass = new HashMap<>();
        info_ass.put("name",names);
        info_ass.put("description" , desecription);


        String uid =
        getIntent().getStringExtra("idadd");
        db.collection("courses").document(uid).collection("assignments").add(info_ass).addOnSuccessListener(documentReference ->
        {
            Toast.makeText(Add_AssignmentActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Add_AssignmentActivity.this , Rc_AssignmentActivity.class);
            startActivity(intent);
            Log.d("TTT" , " Android is Done : "+ documentReference.getId());
        }).addOnFailureListener(e -> {
            Toast.makeText(Add_AssignmentActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
            Log.d("TTT" , e.getMessage());
        });
    }


}