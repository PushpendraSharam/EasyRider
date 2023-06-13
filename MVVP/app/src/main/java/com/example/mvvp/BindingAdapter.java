package com.example.mvvp;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvp.databinding.LayoutRowBinding;

import java.util.List;

public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.Holder>
{
    List<ModalClass> list;
    public BindingAdapter(List<ModalClass> list) {
        this.list = list;
    }
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutRowBinding layoutRowBinding= DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.layout_row,parent,false);
        return new Holder(layoutRowBinding);
    }

    @Override
     public void onBindViewHolder(@NonNull Holder holder, int position) {
      ModalClass modalClass=list.get(position);
      holder.onBind(modalClass);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder
    {
      LayoutRowBinding layoutRowBinding;
        public Holder(@NonNull LayoutRowBinding layoutRowBinding) {
            super(layoutRowBinding.getRoot());
            this.layoutRowBinding=layoutRowBinding;

        }
        public void onBind(Object obj)
        {
            layoutRowBinding.setVariable(BR.data,obj);
            layoutRowBinding.executePendingBindings();
        }
    }
}
