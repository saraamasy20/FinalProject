package com.example.learneasily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
        View view = LayoutInflater.from(context).inflate(R.layout.item_pdf , parent , false);
        return new Video_Adapter.ViewHolder(view);
        }

 @Override
       public void onBindViewHolder(@NonNull Video_Adapter.ViewHolder holder, int position) {

            videos use_vidio = list_video.get(position);
            holder.name_video.setText(use_vidio.getName());
            holder.itemView.findViewById(R.id.btn_edit_pdf).setOnClickListener(new View.OnClickListener() {
       @Override
         public void onClick(View view) {
        Context context = view.getContext();
        Intent intent = new Intent(context , Video_edites.class);
        Intent pdf_name = intent.putExtra("video_name" , String.valueOf(holder.name_video));
        context.startActivity(intent);
        }
        });


        }

@Override
public int getItemCount() {
        return list_video.size();
        }


public  class  ViewHolder extends  RecyclerView.ViewHolder {

    public TextView name_video ;

    public ViewHolder( View item_view){
        super(item_view);
        name_video = item_view.findViewById(R.id.text_view_video);

    }

}
}


