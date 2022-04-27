package com.example.learneasily.Rc;

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

import com.example.learneasily.Adapter.Assignment_adapter;
import com.example.learneasily.Add.Add_AssignmentActivity;
import com.example.learneasily.R;
import com.example.learneasily.models.assignmint;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Rc_AssignmentActivity extends AppCompatActivity {
    RecyclerView rv;
    FirebaseFirestore db;
    ProgressDialog PD;
    Assignment_adapter adapter;
    ArrayList<assignmint> items;
    Button ass_add_rc , ass_edite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_assignment);

      //  setTitle("Courses");
         db =  FirebaseFirestore.getInstance();
        ass_add_rc = findViewById(R.id.add_ass_rc);

        ass_add_rc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rc_AssignmentActivity.this , Add_AssignmentActivity.class);
                //على الid  تبع الكورس الي انبعتلك من الريسايكل ضيفيلي واجب
                intent.putExtra("idadd",getIntent().getStringExtra("idt"));
                startActivity(intent);
            }
        });
        PD = new ProgressDialog(this);
        PD.setCancelable(false);
        PD.setMessage("Please Wait The Information.... :)");
        PD.show();

        rv = findViewById(R.id.rc_ass);
        // جعل الريسايكل الفيو الغرض تبعها ثابت
        rv.setHasFixedSize(true);
        // تستخدم لكيفية العرض داخل الريسايكل فيو
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        items = new ArrayList<assignmint>();
        adapter = new Assignment_adapter(this , items);
        rv.setAdapter(adapter);

        Display_Assigniment();

    }
    private void Display_Assigniment() {
    String uid = FirebaseFirestore.getInstance().collection("courses").getId();
        String idt=getIntent().getStringExtra("idt");


db.collection("courses").document(idt).collection("assignments").orderBy("name", Query.Direction.ASCENDING).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override

            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if(e != null){
                    if(PD.isShowing())
                        PD.dismiss();
                    Log.e("FireBase" , e.getMessage());
                    return;
                }else{
                    Intent intent = new Intent(Rc_AssignmentActivity.this , Add_AssignmentActivity.class);
                    //على الid  تبع الكورس الي انبعتلك من الريسايكل ضيفيلي واجب
                    intent.putExtra("idadd",getIntent().getStringExtra("idt"));
                    startActivity(intent);
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()){

                    if(dc.getType() == DocumentChange.Type.ADDED){
                        Log.d("test" , String.valueOf(dc.getDocument()));
                        items.add(dc.getDocument().toObject(assignmint.class));

                    }
                    adapter.notifyDataSetChanged();
                    if(PD.isShowing())
                        PD.dismiss();
                }
            }
        });
    }
}