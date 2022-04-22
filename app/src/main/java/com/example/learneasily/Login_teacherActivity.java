package com.example.learneasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learneasily.Rc.Rc_courcesActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Login_teacherActivity extends AppCompatActivity {


    EditText fin_login , pass_login;
    Button login_teacher;


    FirebaseAuth auth ;
   // FirebaseFirestore db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_teach);

        auth = FirebaseAuth.getInstance();
        fin_login = findViewById(R.id.edit_Text_FIN);
        pass_login = findViewById(R.id.edit_Text_Pass);
        login_teacher = findViewById(R.id.login_teacher);
        auth = FirebaseAuth.getInstance();

        //db = FirebaseFirestore.getInstance();



        login_teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String  fin_login_teacher = fin_login.getText().toString();
                String  passwords_login_teacher= pass_login.getText().toString();

                // يفحص الشروط اللازمة و اذا كلها صح يتم الدخول الى التطبيق
                if( fin_login_teacher.isEmpty() ||  passwords_login_teacher.isEmpty()){

                    Toast.makeText(Login_teacherActivity.this , "All filed are riquierd" , Toast.LENGTH_LONG).show();
                }
                else{

                    auth.signInWithEmailAndPassword(fin_login_teacher , passwords_login_teacher ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Login_teacherActivity.this , Rc_courcesActivity.class);
                               // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                startActivity(intent);
                                finish();
                            }
                            else{
                                Toast.makeText(Login_teacherActivity.this , "Lodin is failed" , Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}