package com.example.learneasily.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learneasily.R;
import com.example.learneasily.Task_of_CoursesActivity;
import com.example.learneasily.models.courses;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Courses_adapter extends RecyclerView.Adapter<Courses_adapter.ViewHolder> {


    FirebaseFirestore db;
    TextView name_course;
    private Context context ;
    private ArrayList<courses> list_cources ;
    private OnItemClickListener mListener;
    public interface OnItemClickListener{
        void  onItemClick(int position);
        void onDeleteClick(int position);
    }
    public void setOnItemClckListener(OnItemClickListener listener){mListener=listener;}
    public  Courses_adapter(Context c ,ArrayList<courses> l_cources ){
     this.context = c;
     this.list_cources = l_cources;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_home_teacher , parent , false);
        return new Courses_adapter.ViewHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



        courses user = list_cources.get(position);
        holder.name_cource.setText(user.getName());
        holder.name_description.setText(user.getDescription());
        holder.name_id.setText(user.getId());
        holder.itemView.findViewById(R.id.cardView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(context , Task_of_CoursesActivity.class);
                intent.putExtra("idfor",user.getId());
                context.startActivity(intent);
            }
        });

//        holder.itemView.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                db =  FirebaseFirestore.getInstance();
//                db.collection("courses").whereEqualTo("name",user.getName()).get()
//                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
//                            @Override
//                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
//                                db.collection("courses").document(queryDocumentSnapshots.getDocuments().get(0).getId())
//                                        .delete();
//notifyItemRemoved(position);
//                            }
//                        });
//            }
//        }
//
//
//        );

    }

    @Override
    public int getItemCount() {
        return list_cources.size();
    }


    public  class  ViewHolder extends  RecyclerView.ViewHolder {

        public TextView name_cource ;
        public TextView name_description ;
        public TextView name_id ;
        public Button btn_delete;

        public ImageView profile_image ;
        public ViewHolder(View item_view, OnItemClickListener listener){
            super(item_view);
            name_cource = item_view.findViewById(R.id.name_course);
            name_description = item_view.findViewById(R.id.name_description);
            name_id =item_view.findViewById(R.id.course_id);
            btn_delete=item_view.findViewById(R.id.btn_delete);
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
                         String name_course=  list_cources.get(position).getName();
                        if(position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                        db.collection("courses").whereEqualTo("name",name_course).get()
                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                    @Override
                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                        db.collection("courses").document(queryDocumentSnapshots.getDocuments().get(0).getId())
                                                .delete();

                                    }
                                });
                    }


                }
            });
        }

    }
}
