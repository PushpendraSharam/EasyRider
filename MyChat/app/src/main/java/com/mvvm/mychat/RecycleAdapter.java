package com.mvvm.mychat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.holder> {
    Context context;
    List<ModalClass> list;

    public RecycleAdapter(Context context, List<ModalClass> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_row, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, @SuppressLint("RecyclerView") int position) {
      holder.name.setText(list.get(position).getUserName());
      holder.email.setText(list.get(position).getUserEmail());
      holder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              Intent intent=new Intent(context,Chat.class);
              intent.putExtra("id",list.get(position).getUserId());
              intent.putExtra("userName",list.get(position).getUserName());
              context.startActivity(intent);
          }
      });
    }

    @Override
    public int getItemCount() {
        if(list.size()>0)
        return list.size();
        return 0;
    }

    class holder extends RecyclerView.ViewHolder {
         TextView name,email;
        public holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.userName);
            email=itemView.findViewById(R.id.userEmail);
        }
    }
}
