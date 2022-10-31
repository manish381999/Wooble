package com.wooble.wooble.ui.Gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.GalleryImageLayoutBinding;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{

    Context context;
    List<String> list;

    public GalleryAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gallery_image_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder{
GalleryImageLayoutBinding binding;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=GalleryImageLayoutBinding.bind(itemView);
        }
    }
}
