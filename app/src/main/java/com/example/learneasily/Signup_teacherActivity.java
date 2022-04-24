package com.example.learneasily;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learneasily.Rc.Rc_courcesActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class Signup_teacherActivity extends AppCompatActivity {

    EditText  first_name_teacher , Last_name_teacher , email_teacher , password_teacher , fin_teacher;
    TextView  ed_login ;
    Button teacher_sing_up;

    FirebaseAuth auth ;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_teach);

        first_name_teacher = findViewById(R.id.edit_text_fname);
        Last_name_teacher = findViewById(R.id.edit_text_lname);
        email_teacher = findViewById(R.id.edit_text_email);
        password_teacher =  findViewById(R.id.edit_Text_password);
        fin_teacher = findViewById(R.id.edit_Text_FIN_sign);
        ed_login = findViewById(R.id.tv_login);
        teacher_sing_up =findViewById(R.id.sing_up_teacher);

        auth = FirebaseAuth.getInstance();

        ed_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Signup_teacherActivity.this , Login_teacherActivity.class);
                startActivity(intent);
                finish();
            }
        });

        teacher_sing_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // جلب المعلومات من (edit text
                String  teacher_email = email_teacher.getText().toString();
                String  teacher_password = password_teacher.getText().toString();
            //    String  teacher_fin = fin_teacher.getText().toString();
                // يفحص الشروط اللازمة و اذا كلها صح يتم الدخول الى التطبيق
                if( teacher_email.isEmpty() || teacher_password.isEmpty()){
                    Toast.makeText(Signup_teacherActivity.this , "All filed are riquierd" , Toast.LENGTH_LONG).show();
                } if(teacher_password.length() < 6){
                    Toast.makeText(Signup_teacherActivity.this , "Password must be at least 6 character" , Toast.LENGTH_LONG).show();

                }

                auth.createUserWithEmailAndPassword(teacher_email , teacher_password ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        // في حالة النجاح قم بجلب المستخدم الحالي مع ال (id) الخاص به
                        if(task.isSuccessful()){
                            Intent intent = new Intent(Signup_teacherActivity.this , Rc_courcesActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(Signup_teacherActivity.this , "you cannot register with email or password"+ task.getException().getMessage() , Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


    private  void Register( String email_teacher , String password_teacher ) {
        auth.createUserWithEmailAndPassword(email_teacher , password_teacher ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                // في حالة النجاح قم بجلب المستخدم الحالي مع ال (id) الخاص به
                if(task.isSuccessful()){
                    Intent intent = new Intent(Signup_teacherActivity.this , Rc_courcesActivity.class);
                    startActivity(intent);
                    finish();
                   /* FirebaseUser firebaseUser = auth.getCurrentUser();
                    String user_id = firebaseUser.getUid();
                    // تخزينه داخل ال(runTimeFirebase)
                   // reference = FirebaseDatabase.getInstance().getReference("Users").child(user_id);

                    HashMap<String , String> hashMap = new HashMap<>();
                    hashMap.put("id" ,user_id );
                    hashMap.put("name" ,user_name );
                    hashMap.put("imager_url" ,"defult" );
                    reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Intent intent = new Intent(Register_Activity.this , startActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    });*/
// في حال فشل الدخول (وجود خطا ب الايميل او الباسورد)
                }else{
                    Toast.makeText(Signup_teacherActivity.this , "you cannot register with email or password"+ task.getException().getMessage() , Toast.LENGTH_LONG).show();
                }

            }
        });


    }
}