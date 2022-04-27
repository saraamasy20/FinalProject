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
import com.example.learneasily.Rc.Rc_PdfActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Add_pdfActivity extends AppCompatActivity {


    FirebaseFirestore db ;
    EditText name_pdf  ;
    Button add_pdf ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);
        db = FirebaseFirestore.getInstance();
        name_pdf =findViewById(R.id.edit_add_name);

        add_pdf = findViewById(R.id.btn_add_new_ass);





        add_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_assignment(name_pdf ) ;
                Intent intent = new Intent(Add_pdfActivity.this , Rc_PdfActivity.class);
                startActivity(intent);
            }
        });



    }

    private void add_assignment(EditText name_ass ) {

        String names = name_ass.getText().toString().trim();

        Map<String,Object> info_pdf = new HashMap<>();
        info_pdf.put("name",names);

        db.collection("courses").document().collection("assignments").add(info_pdf).addOnSuccessListener(documentReference ->
        {
            Toast.makeText(Add_pdfActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
            Log.d("TTT" , " Android is Done : "+ documentReference.getId());
        }).addOnFailureListener(e -> {
            Toast.makeText(Add_pdfActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
            Log.d("TTT" , e.getMessage());
        });
    }

}