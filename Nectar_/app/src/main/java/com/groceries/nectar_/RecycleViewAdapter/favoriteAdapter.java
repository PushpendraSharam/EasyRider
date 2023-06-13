package com.groceries.nectar_.RecycleViewAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.groceries.nectar_.R;

public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.holder>
{
    int image[];
    String name[];
    String price[];

    public favoriteAdapter(int[] image, String[] name, String[] price) {
        this.image = image;
        this.name = name;
        this.price = price;
    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favorite_adapter,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
    holder.imageView.setImageResource(image[position]);
    holder.name.setText(name[position]);
    holder.price.setText(price[position]);
    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class holder extends RecyclerView.ViewHolder
    {
       ImageView imageView;
       TextView name,price;
        public holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.favoriteImageView);
            name=itemView.findViewById(R.id.favoriteName);
            price=itemView.findViewById(R.id.favoritePrice);
        }
    }
}
