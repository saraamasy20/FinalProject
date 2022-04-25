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
import android.widget.Toast;

import com.example.learneasily.Adapter.Pdf_adapter;
import com.example.learneasily.Adapter.Video_Adapter;
import com.example.learneasily.Add.Add_AssignmentActivity;
import com.example.learneasily.Add.Add_pdfActivity;
import com.example.learneasily.Add.Add_videoActivity;
import com.example.learneasily.R;
import com.example.learneasily.models.pdf;
import com.example.learneasily.models.videos;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Rc_VideoActivity extends AppCompatActivity {



    RecyclerView rv;
    FirebaseFirestore db;
    ProgressDialog PD;
    Video_Adapter adapter;
    ArrayList<videos> items;
    Button btn_add_video;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_video);

        db =  FirebaseFirestore.getInstance();
        btn_add_video = findViewById(R.id.btn_add_video);

        btn_add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rc_VideoActivity.this , Add_videoActivity.class);
                //على الid  تبع الكورس الي انبعتلك من الريسايكل ضيفيلي واجب
                intent.putExtra("idadd",getIntent().getStringExtra("idt"));
                startActivity(intent);
            }
        });

        PD = new ProgressDialog(this);
        PD.setCancelable(false);
        PD.setMessage("Please Wait The Information.... :)");
        PD.show();
        rv = findViewById(R.id.rc_video);
        // جعل الريسايكل الفيو الغرض تبعها ثابت
        rv.setHasFixedSize(true);
        // تستخدم لكيفية العرض داخل الريسايكل فيو
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        items = new ArrayList<videos>();
        adapter = new Video_Adapter(this , items);
        rv.setAdapter(adapter);

        Display_Video();











    }



    private void Display_Video() {
        //String uid = FirebaseAuth.getInstance().getUid();
        String idt=getIntent().getStringExtra("idt");

        db.collection("courses").document(idt).collection("videos").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        items.add(dc.getDocument().toObject(videos.class));


                    }
                    adapter.notifyDataSetChanged();
                    if(PD.isShowing())
                        PD.dismiss();
                }
            }
        });
    }
}