package com.example.learneasily.Add;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.learneasily.R;
import com.example.learneasily.Rc.Rc_AssignmentActivity;
import com.example.learneasily.Rc.Rc_VideoActivity;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Add_videoActivity extends AppCompatActivity {
Button Add_video;
Button uploadVideo;
EditText TitleEt;
VideoView videoView;
ProgressDialog progressDialog;
    StorageReference storageReference;
    FirebaseFirestore db ;
private Uri videoUri=null;
    String title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_video);
        setTitle("Videos");
        db = FirebaseFirestore.getInstance();

        Add_video=findViewById(R.id.Add_video);
        uploadVideo=findViewById(R.id.uploadVideo);
        TitleEt=findViewById(R.id.titleEt);

        videoView=findViewById(R.id.videoView);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Upload file....");


uploadVideo.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        title=TitleEt.getText().toString().trim();
        if(TextUtils.isEmpty(title)){
            Toast.makeText(Add_videoActivity.this,"Title is required...",Toast.LENGTH_SHORT).show();
        }
        else if(videoUri==null){
            Toast.makeText(Add_videoActivity.this,"pick video before upload...",Toast.LENGTH_SHORT).show();

        }else{
            uploadVideoFirebase();
        }
    }
});
//pick video
        Add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent();
                intent.setType("video/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,100);

            }
        });

    }

    private void uploadVideoFirebase() {
        progressDialog.show();
        String timestamp=""+System.currentTimeMillis();

        String fileName="videos/"+timestamp;
        storageReference = FirebaseStorage.getInstance().getReference(fileName);
        storageReference.putFile(videoUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        Toast.makeText(Add_videoActivity.this,"sucessfully uploaded",Toast.LENGTH_SHORT).show();
                        if(progressDialog.isShowing())
                            progressDialog.dismiss();

                        Task<Uri>uriTask= taskSnapshot.getStorage().getDownloadUrl();
                        while(!uriTask.isSuccessful());
                        Uri downloadUri=uriTask.getResult();
                        if(uriTask.isSuccessful()){

                            Map<String,Object> HashMap = new HashMap<>();
                            HashMap.put("id",""+timestamp);
                            HashMap.put("title" , title);
                            HashMap.put("videoUri","" + downloadUri);

                            String uid =
                                    getIntent().getStringExtra("idadd");

                            db.collection("courses").document(uid).collection("videos").add(HashMap).addOnSuccessListener(documentReference ->
                            {


                                Toast.makeText(Add_videoActivity.this ,"Add is Success...",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Add_videoActivity.this , Rc_VideoActivity.class);
                                startActivity(intent);
                                Log.d("TTT" , " Android is Done : "+ documentReference.getId());
                            }).addOnFailureListener(e -> {
                                Toast.makeText(Add_videoActivity.this ,"Add is Not...",Toast.LENGTH_LONG).show();
                                Log.d("TTT" , e.getMessage());
                            });

                        }


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(Add_videoActivity.this,"Failer uploaded",Toast.LENGTH_SHORT).show();
                if(progressDialog.isShowing())
                    progressDialog.dismiss();
                Toast.makeText(Add_videoActivity.this,"Failer uploaded",Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setVideoToVideoView(){
        MediaController mediaController=new MediaController(this);
        mediaController.setAnchorView(videoView);
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);
        videoView.requestFocus();
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                videoView.pause();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == 100){
                videoUri = data.getData();
setVideoToVideoView();
            }

        }

    }
}