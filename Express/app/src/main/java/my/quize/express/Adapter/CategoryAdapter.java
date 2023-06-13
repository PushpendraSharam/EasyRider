package my.quize.express.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import my.quize.express.Model.Category;
import my.quize.express.R;
import my.quize.express.databinding.CategoryItemBinding;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    Context context;
    ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(context).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category=categories.get(position);
        holder.binding.lable.setText(category.getName());
        Glide.with(context).load(category.getIcon()).into(holder.binding.image);
        holder.binding.image.setBackgroundColor(Color.parseColor(category.getColor()));

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        CategoryItemBinding binding;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CategoryItemBinding.bind(itemView);
        }
    }
}
