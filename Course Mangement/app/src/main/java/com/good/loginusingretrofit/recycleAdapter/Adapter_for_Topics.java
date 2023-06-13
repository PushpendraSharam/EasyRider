package com.good.loginusingretrofit.recycleAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.good.loginusingretrofit.Controler.Controler;
import com.good.loginusingretrofit.Home;
import com.good.loginusingretrofit.MainActivity;
import com.good.loginusingretrofit.R;
import com.good.loginusingretrofit.Subjects;
import com.good.loginusingretrofit.Topics;
import com.good.loginusingretrofit.models.ToicsCheckModalClass;
import com.good.loginusingretrofit.models.TopicsModalClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Adapter_for_Topics extends RecyclerView.Adapter<Adapter_for_Topics.holder>
{
    TopicsModalClass Topics[];
    Context context;
    public Adapter_for_Topics(TopicsModalClass[] Topics, Context context) {
        this.Topics = Topics;
        this.context=context;
    }




    @NonNull
    @Override
    public Adapter_for_Topics.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.recycle_topics_layout,parent,false);
        return new Adapter_for_Topics.holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter_for_Topics.holder holder, int position) {
        try {
            holder.textView.setText(Topics[position].getTopics());
            if(Topics[position].getCompletion().equals("yes"))
            {
                holder.checkBox.setChecked(true);
                holder.date.setText("Completed On: "+Topics[position].getDate());
            }
            else
            {
                holder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        new AlertDialog.Builder(context)
                                .setTitle("Really Sure?")
                                .setMessage("Are you sure you completed the topic")
                                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        holder.checkBox.setChecked(false);
                                    }
                                })
                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface arg0, int arg1) {

                                        SharedPreferences sp= context.getSharedPreferences("DataForCheckBox",Context.MODE_PRIVATE);
                                        String tid= sp.getString("TID","1");
                                        String bid=sp.getString("BID","1");
                                        String Sid=sp.getString("SID","1");
                                        String topicid=Topics[holder.getAdapterPosition()].getId();
                                        Call<ToicsCheckModalClass> call= Controler.getInstance().getApi().setcheck(bid,tid,Sid,topicid);
                                        call.enqueue(new Callback<ToicsCheckModalClass>() {
                                            @Override
                                            public void onResponse(Call<ToicsCheckModalClass> call, Response<ToicsCheckModalClass> response) {
                                                ToicsCheckModalClass obj= response.body();
                                                Toast.makeText(context, "Hit", Toast.LENGTH_SHORT).show();


                                            }

                                            @Override
                                            public void onFailure(Call<ToicsCheckModalClass> call, Throwable t) {

                                            }
                                        });
                                        holder.checkBox.setChecked(true);
                                        holder.checkBox.setEnabled(false);
                                    }
                                }).create().show();
                    }
                });
            }
        } catch (Exception e)
        {
           holder.textView.setText(e.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return Topics.length;
    }




    class holder extends RecyclerView.ViewHolder
    {
        TextView textView,date;
        CheckBox checkBox;
        LinearLayout linearLayout;
        public holder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.recycle_adapter_textView_topics);
            checkBox=itemView.findViewById(R.id.checkbox);
            linearLayout=itemView.findViewById(R.id.recycleAdapter);
            date=itemView.findViewById(R.id.date);
        }
    }
}
