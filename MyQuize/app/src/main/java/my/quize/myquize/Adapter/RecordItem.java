package my.quize.myquize.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import my.quize.myquize.Modal.RecordItemModal;
import my.quize.myquize.R;

public class RecordItem extends RecyclerView.Adapter<RecordItem.holder>
{
    List<RecordItemModal> list;
    Context context;

    public RecordItem(List<RecordItemModal> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.record_item,parent,false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        int resultNumber= Integer.parseInt(list.get(position).getLastRecord());
        if(resultNumber==1 ||resultNumber==7 ||resultNumber==3 ||resultNumber==9)
        {
          holder.lastResult.setBackground(context.getDrawable(R.drawable.green_circle_bgg));
        }
        else if(resultNumber==2 ||resultNumber==8 ||resultNumber==4 ||resultNumber==6)
        {
            holder.lastResult.setBackground(context.getDrawable(R.drawable.red_circle_bg));
        }
        else if(resultNumber==5)
        {
            holder.lastResult.setBackground(context.getDrawable(R.drawable.green_violet_circle_bg));
        }
        else
        {
            holder.lastResult.setBackground(context.getDrawable(R.drawable.red_violet_circle_bg));

        }
        holder.recordId.setText(list.get(position).getRecordId());
        holder.lastResult.setText(list.get(position).getLastRecord());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder
    {
          TextView recordId,lastResult;
        public holder(@NonNull View itemView) {
            super(itemView);
            recordId=itemView.findViewById(R.id.recordId);
            lastResult=itemView.findViewById(R.id.lastResult);
        }
    }

}
