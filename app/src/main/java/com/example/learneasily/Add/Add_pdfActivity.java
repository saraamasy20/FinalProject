package com.example.learneasily.Add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.learneasily.R;
import com.example.learneasily.Rc.Rc_PdfActivity;
import com.example.learneasily.Rc.Rc_VideoActivity;
import com.example.learneasily.Rc.Rc_courcesActivity;
import com.example.learneasily.models.pdf;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;
import java.util.Map;

public class Add_pdfActivity extends AppCompatActivity {


    FirebaseFirestore db ;
    EditText pdf_name  ;
    Button add_pdf ,upload_pdf;
    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_pdf);
        db = FirebaseFirestore.getInstance();
        pdf_name =findViewById(R.id.pdf_name);

        add_pdf = findViewById(R.id.add_pdf);
        upload_pdf=findViewById(R.id.upload_pdf);




        add_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  add_pdf(pdf_name ) ;
                selectFile();


            }
        });

        upload_pdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pdf cpdf=new pdf();
//                String url=cpdf.getUrl();
//                UploadFiles(Uri.parse(url));
                Intent intent = new Intent(Add_pdfActivity.this , Rc_PdfActivity.class);
                String uid =
                        getIntent().getStringExtra("idt");
                intent.putExtra("idt",uid);
                startActivity(intent);
            }
        });


    }

    private void selectFile() {
        Intent intent=new Intent();
        intent.setType("application/pdf");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File ..."),1);
    }

//


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1 && resultCode==RESULT_OK && data != null && data.getData() != null){

          UploadFiles(data.getData());

        }
    }

    private void UploadFiles(Uri data) {
        final ProgressDialog progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("uploading pdf......");
        progressDialog.show();
        String timestamp=""+System.currentTimeMillis();

        String fileName="pdf/"+timestamp;
        storageReference = FirebaseStorage.getInstance().getReference(fileName);
        storageReference.putFile(data)
               .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                   @Override
                   public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                       Task<Uri> uriTask= taskSnapshot.getStorage().getDownloadUrl();
                       while(!uriTask.isSuccessful());
                       Uri url=uriTask.getResult();


                       pdf pdfClass=new pdf(pdf_name.getText().toString(),url.toString());

                       if(uriTask.isSuccessful()){

                           String uid =
                                   getIntent().getStringExtra("idt");

                           db.collection("courses").document(uid).collection("pdfs").add(pdfClass).addOnSuccessListener(documentReference ->
                           {


                               Toast.makeText(Add_pdfActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
                               Intent intent = new Intent(Add_pdfActivity.this , Rc_PdfActivity.class);
                               startActivity(intent);
                               Log.d("TTT" , " Android is Done : "+ documentReference.getId());
                           }).addOnFailureListener(e -> {
                               Toast.makeText(Add_pdfActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
                               Log.d("TTT" , e.getMessage());
                           });


                       }






                   }
               }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                  double progress=(100.0* snapshot.getBytesTransferred())/snapshot.getTotalByteCount();
                  progressDialog.setMessage("Upload:"+(int)progress+"%");
           }
       });
    }


}















  //  private void add_pdf(EditText name_ass ) {
//
//        String names = name_ass.getText().toString().trim();
//
//        Map<String,Object> info_pdf = new HashMap<>();
//        info_pdf.put("name",names);
//
//        db.collection("courses").document().collection("assignments").add(info_pdf).addOnSuccessListener(documentReference ->
//        {
//            Toast.makeText(Add_pdfActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
//            Log.d("TTT" , " Android is Done : "+ documentReference.getId());
//        }).addOnFailureListener(e -> {
//            Toast.makeText(Add_pdfActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
//            Log.d("TTT" , e.getMessage());
//        });
//    }