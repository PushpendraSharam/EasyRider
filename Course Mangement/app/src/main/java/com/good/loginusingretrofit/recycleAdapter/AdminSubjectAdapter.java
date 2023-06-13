package com.good.loginusingretrofit.recycleAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.good.loginusingretrofit.AdminSubjects;
import com.good.loginusingretrofit.AdminTopics;
import com.good.loginusingretrofit.R;
import com.good.loginusingretrofit.models.AdminSubjectsSubModalClass;

public class AdminSubjectAdapter extends RecyclerView.Adapter<AdminSubjectAdapter.holder> {
   Context context;
   AdminSubjectsSubModalClass data[];

    public AdminSubjectAdapter(Context context, AdminSubjectsSubModalClass[] data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        View view=layoutInflater.inflate(R.layout.admin_recycle_adapter,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
    holder.subject.setText(data[holder.getAdapterPosition()].getS_name());
        holder.progressBar.setProgress(data[holder.getAdapterPosition()].getPercent());
        holder.percentage.setText(String.valueOf(data[holder.getAdapterPosition()].getPercent())+"%");
        holder.done.setText("Done : " +data[position].getCompleted_topics());
       // holder.course.setText("("+data[position].getB_name()+")");
        holder.total.setText("Total Topics : "+data[holder.getAdapterPosition()].getTotal_topics());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, AdminTopics.class);
                intent.putExtra("AdminSid",data[holder.getAdapterPosition()].getS_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    class holder extends RecyclerView.ViewHolder {
        TextView subject, percentage, course, done,total;
        ProgressBar progressBar;
        CardView cardView;

        public holder(@NonNull View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.adminTextView);
            percentage = itemView.findViewById(R.id.adminPercent);
            progressBar = itemView.findViewById(R.id.progressBar);
            course = itemView.findViewById(R.id.adminCourseName);
            done = itemView.findViewById(R.id.complete);
            total=itemView.findViewById(R.id.total);
            cardView=itemView.findViewById(R.id.cardViewForAdminAdapter);

        }
    }
}
