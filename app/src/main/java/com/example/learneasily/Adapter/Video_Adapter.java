package com.example.learneasily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learneasily.R;
import com.example.learneasily.edite.Video_edites;
import com.example.learneasily.models.videos;

import java.util.ArrayList;

   public class Video_Adapter extends RecyclerView.Adapter<Video_Adapter.ViewHolder> {



      private Context context ;
      private ArrayList<videos> list_video ;

      public  Video_Adapter(Context c ,ArrayList<videos> l_video ){
        this.context = c;
        this.list_video = l_video;
        }

    @NonNull
  @Override
     public Video_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video , parent , false);
        return new Video_Adapter.ViewHolder(view);
        }

 @Override
       public void onBindViewHolder(@NonNull Video_Adapter.ViewHolder holder, int position) {

            videos use_vidio = list_video.get(position);
            holder.name_video.setText(use_vidio.getTitle());
             setVideoUrl(use_vidio,holder);


        }

        private void setVideoUrl(videos video,ViewHolder holder){
         Uri videoUrl=video.getUrl();
          MediaController mediaController=new MediaController(context);
            mediaController.setAnchorView(holder.vview);


holder.vview.setMediaController(mediaController);
holder.vview.setVideoURI(videoUrl);

holder.vview.requestFocus();
holder.vview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
});
holder.vview.setOnInfoListener(new MediaPlayer.OnInfoListener() {
    @Override
    public boolean onInfo(MediaPlayer mediaPlayer, int what, int extra) {
        switch(what){
            case MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START:{

                return true;
            }
            case MediaPlayer.MEDIA_INFO_BUFFERING_START:{

                return true;
            }
            case MediaPlayer.MEDIA_INFO_BUFFERING_END:{

                return true;
            }



        }





        return false;
    }
});

holder.vview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
    @Override
    public void onCompletion(MediaPlayer mediaPlayer) {
        mediaPlayer.start();
    }
});
      }

@Override
public int getItemCount() {
        return list_video.size();
        }


public  class  ViewHolder extends  RecyclerView.ViewHolder {

    public TextView name_video ;
public VideoView vview;
    public ViewHolder( View item_view){
        super(item_view);
        name_video = item_view.findViewById(R.id.text_view_video);
        vview=item_view.findViewById(R.id.vview);
    }

}
}


