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
import com.example.learneasily.Adapter.Courses_adapter;
import com.example.learneasily.Adapter.Pdf_adapter;
import com.example.learneasily.Add.Add_AssignmentActivity;
import com.example.learneasily.Add.Add_pdfActivity;
import com.example.learneasily.R;
import com.example.learneasily.models.assignmint;
import com.example.learneasily.models.pdf;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Rc_PdfActivity extends AppCompatActivity {


    RecyclerView rv;
    FirebaseFirestore db;
    ProgressDialog PD;
    Pdf_adapter adapter;
    ArrayList<pdf> items;
    Button pdf_add , pdf_edite;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rc_pdf);

        db =  FirebaseFirestore.getInstance();
        pdf_add = findViewById(R.id.btn_add_pdf);

        pdf_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Rc_PdfActivity.this , Add_pdfActivity.class);
                String idt=getIntent().getStringExtra("idt");
                intent.putExtra("idt",idt);
                startActivity(intent);
            }
        });

        PD = new ProgressDialog(this);
        PD.setCancelable(true);
        PD.setMessage("Please Wait The Information.... :)");
        PD.show();
        rv = findViewById(R.id.rc_pdf);
        // جعل الريسايكل الفيو الغرض تبعها ثابت
        rv.setHasFixedSize(true);
        // تستخدم لكيفية العرض داخل الريسايكل فيو
        RecyclerView.LayoutManager  LM= new LinearLayoutManager(this);
        rv.setLayoutManager(LM);

        items = new ArrayList<pdf>();
        adapter = new Pdf_adapter(this , items);
        rv.setAdapter(adapter);

        Display_Pdf();
        Deletepdf();
    }

    private void Display_Pdf() {
        String idt=getIntent().getStringExtra("idt");
        db.collection("courses").document(idt).collection("pdfs").addSnapshotListener(new EventListener<QuerySnapshot>() {
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
                        items.add(dc.getDocument().toObject(pdf.class));

                    }
                    adapter.notifyDataSetChanged();
                    if(PD.isShowing())
                        PD.dismiss();
                }
            }
        });
    }

    private void Deletepdf(){
        adapter.setOnItemClckListener(new Pdf_adapter.OnItemClickListener() {
      @Override
    public void onItemClick(int position) {
       }
      @Override
        public void onDeleteClick(int position) {
     items.remove(position);
       adapter.notifyItemRemoved(position);
                                          }
                                      }

        );
    }
}