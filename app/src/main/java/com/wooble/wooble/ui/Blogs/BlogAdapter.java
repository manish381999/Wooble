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
        holder.binding.tvDescription.setText(blogModel.getContent());
        holder.binding.datetime.setText(blogModel.getCreated_date());

        holder.binding.readBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Blog_Viewer_Activity.class);
                intent.putExtra("id",blogModel.getId());
                intent.putExtra("title",blogModel.getTitle());
                intent.putExtra("content",blogModel.getContent());
                intent.putExtra("created_date",blogModel.getCreated_date());
                intent.putExtra("image",blogModel.getImage());
                context.startActivity(intent);
            }
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
