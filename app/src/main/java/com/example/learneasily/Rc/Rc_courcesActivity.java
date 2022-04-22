package com.example.learneasily.Rc;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.learneasily.Adapter.Courses_adapter;
import com.example.learneasily.Add.Add_AssignmentActivity;
import com.example.learneasily.Add.Add_coursesActivity;
import com.example.learneasily.Login_teacherActivity;
import com.example.learneasily.R;
import com.example.learneasily.Signup_teacherActivity;
import com.example.learneasily.edite.Assignment_edites;
import com.example.learneasily.models.courses;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Rc_courcesActivity extends AppCompatActivity {


    RecyclerView rv;
    FirebaseFirestore db;
    ProgressDialog PD;
    Courses_adapter adapter;
    ArrayList<courses> items;
    Button cource_add , course_edite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_cources);

        setTitle("Courses");
        db =  FirebaseFirestore.getInstance();
        cource_add = findViewById(R.id.add_cource);


        courses course = new courses();


       /* course_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DocumentReference docRef = db.collection("courses").document(course.getName());
                Map<String, Object> updates = new HashMap<>();
                updates.put("delete_field", FieldValue.delete());

              //  ApiFuture<courses> writeResult = docRef.update(updates);

            }
        });*/


        cource_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rc_courcesActivity.this , Add_AssignmentActivity.class);
                startActivity(intent);
            }
        });

        PD = new ProgressDialog(this);
        PD.setCancelable(false);
        PD.setMessage("Please Wait The Information.... :)");
        PD.show();
        rv = findViewById(R.id.rc_courc);
        // جعل الريسايكل الفيو الغرض تبعها ثابت
        rv.setHasFixedSize(true);
        // تستخدم لكيفية العرض داخل الريسايكل فيو
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        items = new ArrayList<courses>();
        adapter = new Courses_adapter(this , items);
        rv.setAdapter(adapter);

        Display_Courses();


    }
    private void Display_Courses() {

        db.collection("courses").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override

            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    if(PD.isShowing())
                        PD.dismiss();
                    Log.e("FireBase" , e.getMessage());
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        Log.d("test" , String.valueOf(dc.getDocument()));
                        items.add(dc.getDocument().toObject(courses.class));

                    }
                    adapter.notifyDataSetChanged();
                    if(PD.isShowing())
                        PD.dismiss();
                }
            }
        });


    }
}