package com.example.learneasily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learneasily.R;
import com.example.learneasily.Task_of_CoursesActivity;
import com.example.learneasily.edite.Assignment_edites;
import com.example.learneasily.models.assignmint;

import java.util.ArrayList;

public class Assignment_adapter extends RecyclerView.Adapter<Assignment_adapter.ViewHolder> {

    private Context context ;
    private ArrayList<assignmint> list_assignmint ;

    public  Assignment_adapter(Context c ,ArrayList<assignmint> l_ass ){
        this.context = c;
        this.list_assignmint = l_ass;
    }

    @NonNull
    @Override
    public Assignment_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View views = LayoutInflater.from(context).inflate(R.layout.item_assignment , parent , false);
        return new Assignment_adapter.ViewHolder(views);
    }

    @Override
    public void onBindViewHolder(@NonNull Assignment_adapter.ViewHolder holder, int position) {

        assignmint ass = list_assignmint.get(position);
        holder.name_ass.setText(ass.getName());

        holder.edit.findViewById(R.id.btn_edit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , Assignment_edites.class);
                Intent ass_name = intent.putExtra("ass_name" , String.valueOf(holder.name_ass));
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context , Task_of_CoursesActivity.class);
                context.startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return list_assignmint.size();
    }


    public  class  ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name_ass ;
        public Button edit ;
        public ViewHolder( View item_view){
            super(item_view);
            name_ass = item_view.findViewById(R.id.name_of_ass);
            edit = item_view.findViewById(R.id.btn_edit);
        }

    }
}
