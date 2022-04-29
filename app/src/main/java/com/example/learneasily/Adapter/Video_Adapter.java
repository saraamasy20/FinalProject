package com.example.learneasily.Adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learneasily.R;
import com.example.learneasily.edite.Video_edites;
import com.example.learneasily.models.videos;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;

import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;

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



        }

//       private void initPlayer(){
//
//           player=new SimpleExoPlayer.Builder(this).build();
//           pv.setPlayer(player);
//           MediaItem item=MediaItem.fromUri(videolink);
//           player.setMediaItem(item);
//           player.setPlayWhenReady(playerReady);
//           player.seekTo(currentWindow,currentPosition);
//           player.prepare();
//       }



@Override
public int getItemCount() {
        return list_video.size();
        }


public  class  ViewHolder extends  RecyclerView.ViewHolder {

    public TextView name_video ;
public PlayerView vview;
    SimpleExoPlayer exoPlayer;
    public ViewHolder( View item_view){
        super(item_view);

    }

    public void SetExoplayer(Application application,String title,String url ){
        name_video = itemView.findViewById(R.id.text_view_video);
        vview=itemView.findViewById(R.id.vview);
        name_video.setText(title);
     try{
//            BandwidthMeter bandwidthMeter= new DefaultBandwidthMeter();
//         TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
//         exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
//         Uri videouri = Uri.parse(url);
//         DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
//         ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
//         MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
//         vview.setPlayer(exoPlayer);
//         exoPlayer.prepare(mediaSource);
//         exoPlayer.setPlayWhenReady(true);

     } catch (Exception e) {
         Log.e("TAG", "Error : " + e.toString());
     }
     }



    }

}
//}


