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
import com.example.learneasily.Task_of_CoursesActivity;
import com.example.learneasily.models.courses;

import java.util.ArrayList;
import java.util.List;

public class Courses_adapter extends RecyclerView.Adapter<Courses_adapter.ViewHolder> {



    private Context context ;
    private ArrayList<courses> list_cources ;

    public  Courses_adapter(Context c ,ArrayList<courses> l_cources ){
     this.context = c;
     this.list_cources = l_cources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_home_teacher , parent , false);
        return new Courses_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        courses user = list_cources.get(position);
        holder.name_cource.setText(user.getName());
        holder.itemView.findViewById(R.id.cardView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , Task_of_CoursesActivity.class);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list_cources.size();
    }


    public  class  ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name_cource ;
        public TextView name_description ;
        public ImageView profile_image ;
        public ViewHolder( View item_view){
            super(item_view);
            name_cource = item_view.findViewById(R.id.name_course);
            name_description = item_view.findViewById(R.id.name_description);
            //profile_image =item_view.findViewById(R.id.avatar);
        }

    }
}
