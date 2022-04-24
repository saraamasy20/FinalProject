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
import com.example.learneasily.edite.Assignment_edites;
import com.example.learneasily.models.courses;
import com.example.learneasily.models.pdf;

import java.util.ArrayList;

public class Pdf_adapter extends RecyclerView.Adapter<Pdf_adapter.ViewHolder> {



    private Context context ;
    private ArrayList<pdf> list_pdf ;

    public  Pdf_adapter(Context c ,ArrayList<pdf> l_pdf ){
        this.context = c;
        this.list_pdf = l_pdf;
    }

    @NonNull
    @Override
    public Pdf_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pdf , parent , false);
        return new Pdf_adapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Pdf_adapter.ViewHolder holder, int position) {



        pdf use_pdf = list_pdf.get(position);
        holder.name_pdf.setText(use_pdf.getName());
        holder.itemView.findViewById(R.id.btn_edit_pdf).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , Assignment_edites.class);
                Intent pdf_name = intent.putExtra("name_pdf" , String.valueOf(holder.name_pdf));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list_pdf.size();
    }


    public  class  ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name_pdf ;
        public ImageView profile_image ;
        public ViewHolder( View item_view){
            super(item_view);
            name_pdf = item_view.findViewById(R.id.name_pdf);
            //profile_image =item_view.findViewById(R.id.avatar);
        }

    }
}

