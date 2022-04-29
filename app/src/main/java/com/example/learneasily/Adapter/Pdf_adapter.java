package com.example.learneasily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learneasily.R;
import com.example.learneasily.Task_of_CoursesActivity;
import com.example.learneasily.edite.Assignment_edites;
import com.example.learneasily.models.courses;
import com.example.learneasily.models.pdf;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Pdf_adapter extends RecyclerView.Adapter<Pdf_adapter.ViewHolder> {

    private Pdf_adapter.OnItemClickListener mListener;

    FirebaseFirestore db;
    private ArrayList<courses> list_cources;
    private Context context ;
    private ArrayList<pdf> list_pdf ;
    public interface OnItemClickListener{
        void  onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClckListener(Pdf_adapter.OnItemClickListener listener){mListener=listener;}
    public  Pdf_adapter(Context c ,ArrayList<pdf> l_pdf ){
        this.context = c;
        this.list_pdf = l_pdf;
    }

    @NonNull
    @Override
    public Pdf_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_pdf , parent , false);
        return new Pdf_adapter.ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull Pdf_adapter.ViewHolder holder, int position) {



        pdf use_pdf = list_pdf.get(position);
        holder.name_pdf.setText(use_pdf.getName());



    }

    @Override
    public int getItemCount() {
        return list_pdf.size();
    }


    public  class  ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name_pdf ;

        public Button btn_delete;
        public ViewHolder(View item_view, Pdf_adapter.OnItemClickListener listener){
            super(item_view);
            name_pdf = item_view.findViewById(R.id.name_pdf);
            btn_delete=item_view.findViewById(R.id.btn_delete_pdf);
            //profile_image =item_view.findViewById(R.id.avatar);

            item_view.setOnClickListener((v)-> {

                if(listener !=null){
                    int position=getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }

            });

            btn_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    db =  FirebaseFirestore.getInstance();

                    if(listener !=null){
                        int position=getAdapterPosition();
                        String name_pdf=  list_pdf.get(position).getName();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                        String collid=list_cources.get(position).id;

                        db.collection("courses/"+collid+"pdfs/").whereEqualTo("name",name_pdf).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        db.collection("courses/"+collid+"pdfs/").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                                .delete();

                                    }
                                });
                    }


                }
            });









        }

    }
}

