package com.example.audiocall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleAdapter extends RecyclerView.Adapter<RecycleAdapter.holder> {
    List<UserModal> list;
    String start = "https://firebasestorage.googleapis.com/v0/b/fir-tutorial-d6331.appspot.com/o/";
    String end = "?alt=media&token=c02d3248-4c89-49f9-b7a8-4e4e3c07ec86";
    String customUrl;
    Context context;
    String currentUserName ;
    String currentUserImage ;

    public RecycleAdapter(List<UserModal> list, Context context) {
        this.list = list;
        this.context = context;

    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {


            String imageRealName = list.get(holder.getAdapterPosition()).getImage();
            holder.profileImageView.setImageResource(R.drawable.user);
            if (imageRealName.isEmpty())
                holder.profileImageView.setImageResource(R.drawable.user);
            else
                customUrl = start + imageRealName + end;
            holder.name.setText(list.get(holder.getAdapterPosition()).getName());
            holder.id.setText(list.get(holder.getAdapterPosition()).getEmail());
            Picasso.get().load(customUrl.trim()).into(holder.profileImageView);




        holder.callImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= context.getSharedPreferences("UserDetails",Context.MODE_PRIVATE);
                currentUserName=sharedPreferences.getString("currentUserName","Ram");
                currentUserImage=sharedPreferences.getString("currentUserImage","Demo");

                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("VoiceCall");
                String randomId = UUID.randomUUID().toString();
                CallModalClass callModalClass = new CallModalClass(FirebaseAuth.getInstance().getUid(), list.get(holder.getAdapterPosition()).getId(), currentUserImage, currentUserName);
                databaseReference.child(randomId).setValue(callModalClass);
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("GoingWay", "indirect");
                 intent.putExtra("CallingPersonName", list.get(holder.getAdapterPosition()).getName());
                 intent.putExtra("callingPersonImage",list.get(holder.getAdapterPosition()).getImage());
                context.startActivity(intent);
                ( (Activity)context).finish();
            }
        });
        holder.videoCallImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences= context.getSharedPreferences("UserDetails",Context.MODE_PRIVATE);
                currentUserName=sharedPreferences.getString("currentUserName","Ram");
                currentUserImage=sharedPreferences.getString("currentUserImage","Demo");
                FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
                DatabaseReference databaseReference = firebaseDatabase.getReference().child("VideoCall");
                String randomId = UUID.randomUUID().toString();
                CallModalClass callModalClass = new CallModalClass(FirebaseAuth.getInstance().getUid(), list.get(holder.getAdapterPosition()).getId(), currentUserImage, currentUserName);
                databaseReference.child(randomId).setValue(callModalClass);
                Intent intent = new Intent(context, Video_Call_Activity.class);
                intent.putExtra("GoingWay", "indirect");
                intent.putExtra("CallingPersonName", list.get(holder.getAdapterPosition()).getName());
                intent.putExtra("callingPersonImage",list.get(holder.getAdapterPosition()).getImage());
                context.startActivity(intent);
                ( (Activity)context).finish();

            }
        });

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {
        CircleImageView profileImageView;
        ImageView callImageView,videoCallImageView;
        TextView name, id;
        LinearLayout linearLayout;
        public holder(@NonNull View itemView) {
            super(itemView);
            profileImageView = itemView.findViewById(R.id.userImageView);
            callImageView = itemView.findViewById(R.id.userCallImageView);
            name = itemView.findViewById(R.id.userName);
            id = itemView.findViewById(R.id.userID);
            linearLayout=itemView.findViewById(R.id.singleRowLinearLayout);
            videoCallImageView=itemView.findViewById(R.id.userVideoCallImageView);
        }
    }
}
