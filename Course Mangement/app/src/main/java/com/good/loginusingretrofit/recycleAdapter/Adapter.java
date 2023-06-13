package com.good.loginusingretrofit.recycleAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.good.loginusingretrofit.R;
import com.good.loginusingretrofit.Subjects;
import com.good.loginusingretrofit.models.TeacherModalClass;

public class Adapter extends RecyclerView.Adapter<Adapter.holder> {
    TeacherModalClass batch[];
    Context context;
    public Adapter(TeacherModalClass batch[], Context context) {
        this.batch = batch;
        this.context=context;
    }



    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycle_adapter,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.textView.setText(batch[position].getB_name());
        holder.courseName.setText("("+batch[position].getC_name()+")");
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, Subjects.class);
                intent.putExtra("Bid",batch[holder.getAdapterPosition()].getB_id());
                intent.putExtra("c_id",batch[holder.getAdapterPosition()].getC_id());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return batch.length;
    }


    class holder extends RecyclerView.ViewHolder
    {
        TextView textView,courseName;
        LinearLayout linearLayout;
        public holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.recycle_adapter_textView);
            courseName=itemView.findViewById(R.id.recycle_adapter_Course);
            linearLayout=itemView.findViewById(R.id.recycleAdapter);
        }
    }
}
