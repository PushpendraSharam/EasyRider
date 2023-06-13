package my.quize.myquize.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import my.quize.myquize.Modal.ItemMyOrderModal;
import my.quize.myquize.R;

public class MyOrderAdapter extends RecyclerView.Adapter<MyOrderAdapter.holder> {
    ArrayList<ItemMyOrderModal> item;
    Context context;

    public MyOrderAdapter(ArrayList<ItemMyOrderModal> item, Context context) {
        this.item = item;
        this.context = context;
    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_my_order, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        int resultNumber= Integer.parseInt(item.get(position).getResult());
        String colorChar=item.get(position).getSelect();
        String pointWithSymbol = "\u20B9" + item.get(position).getPoint();
        String amountWithSymbol = "\u20B9" + item.get(position).getAmount();
        if(resultNumber==1 ||resultNumber==7 ||resultNumber==3 ||resultNumber==9)
        {
            holder.result.setBackground(context.getDrawable(R.drawable.green_circle_bgg));
        }
        else if(resultNumber==2 ||resultNumber==8 ||resultNumber==4 ||resultNumber==6)
        {
            holder.result.setBackground(context.getDrawable(R.drawable.red_circle_bg));
        }
        else if(resultNumber==5)
        {
            holder.result.setBackground(context.getDrawable(R.drawable.green_violet_circle_bg));
        }
        else
        {
            holder.result.setBackground(context.getDrawable(R.drawable.red_violet_circle_bg));
        }
        if(colorChar=="R")
        {
            holder.select.setBackground(context.getDrawable(R.drawable.red_circle_bg));
        }
        else if(colorChar=="G")
        {
            holder.select.setBackground(context.getDrawable(R.drawable.green_circle_bgg));
        }
        holder.select.setText(item.get(position).getSelect());
        holder.point.setText(pointWithSymbol);
        holder.result.setText(item.get(position).getResult());
        if(Integer.parseInt(item.get(position).getAmount())>30&&Integer.parseInt(item.get(position).getAmount())<71)
        {
            holder.amount.setText("+"+amountWithSymbol);
        }
        else
        {
            holder.amount.setText("-"+amountWithSymbol);
        }

    }

    @Override
    public int getItemCount() {
        return item.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView select, point, result, amount;

        public holder(@NonNull View itemView) {
            super(itemView);
            select = itemView.findViewById(R.id.selectMyOrder);
            point = itemView.findViewById(R.id.pointMyOrder);
            result = itemView.findViewById(R.id.resultMyOrder);
            amount = itemView.findViewById(R.id.amountMyOrder);
        }
    }
}
