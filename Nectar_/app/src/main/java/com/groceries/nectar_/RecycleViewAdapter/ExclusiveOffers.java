package com.groceries.nectar_.RecycleViewAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.groceries.nectar_.Databases.CartDatabase;
import com.groceries.nectar_.R;
import com.groceries.nectar_.item;

public class ExclusiveOffers extends RecyclerView.Adapter<ExclusiveOffers.holder>
{
    int image[];
    String name[];
    String price[];
    Context context;

    public ExclusiveOffers(int[] image, String[] name, String[] price, Context context) {
        this.image = image;
        this.name = name;
        this.price = price;
        this.context = context;
    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.exclusive_offer_recycle_adapter,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
    holder.imageView.setImageResource(image[holder.getAdapterPosition()]);
    holder.name.setText(name[holder.getAdapterPosition()]);
    holder.price.setText(price[holder.getAdapterPosition()]);
    holder.imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            context.startActivity(new Intent(context, item.class));
            
        }
    });
    holder.addItem.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CartDatabase database=new CartDatabase(context);
            database.addData(image[holder.getAdapterPosition()],name[holder.getAdapterPosition()],price[holder.getAdapterPosition()]);
            Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show();
        }
    });

    }

    @Override
    public int getItemCount() {
        return name.length;
    }

    class holder extends RecyclerView.ViewHolder
    {
        ImageView imageView,addItem;
        TextView name,price;
        MaterialCardView cardView;

        public holder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imageViewAdapter);
            name=itemView.findViewById(R.id.textViewAdapter);
            price=itemView.findViewById(R.id.priceAdapter);
            cardView=itemView.findViewById(R.id.cardViewAdapter);
            addItem=itemView.findViewById(R.id.additionButton);
        }
    }
}
