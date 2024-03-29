package com.wooble.wooble.ui.Blogs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.BlogItemLayoutBinding;

import java.util.List;


public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.BlogViewHolder>{

    Context context;
    List<BlogModel> BlogList;

    public BlogAdapter(Context context, List<BlogModel> blogList) {
        this.context = context;
        this.BlogList = blogList;
    }
    @NonNull
    @Override
    public BlogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view= LayoutInflater.from(context).inflate(R.layout.blog_item_layout, parent, false);
        return new BlogViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull BlogViewHolder holder, int position) {
        BlogModel blogModel = BlogList.get(position);
        holder.binding.tvTitle.setText(blogModel.getTitle());
        holder.binding.tvDescription.setHorizontalScrollBarEnabled(false);
        holder.binding.tvDescription.setVerticalScrollBarEnabled(false);
        holder.binding.tvDescription.loadData(blogModel.getContent(),"text/html", "UTF-8");
        holder.binding.datetime.setText(blogModel.getLast_updated());

        holder.binding.readBtn.setOnClickListener(v -> {
            Intent intent=new Intent(context, Blog_Viewer_Activity.class);
            intent.putExtra("id",blogModel.getBlog_id());
            //intent.putExtra("full_name",blogModel.getFull_name());
            intent.putExtra("full_name",blogModel.getEmail_id());
            intent.putExtra("title",blogModel.getTitle());
            intent.putExtra("content",blogModel.getContent());
            intent.putExtra("created_date",blogModel.getLast_updated());
            //intent.putExtra("image",blogModel.getImage());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return BlogList.size();
    }

    public static class BlogViewHolder extends RecyclerView.ViewHolder{

        BlogItemLayoutBinding binding;
        public BlogViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=BlogItemLayoutBinding.bind(itemView);
        }
    }
}
