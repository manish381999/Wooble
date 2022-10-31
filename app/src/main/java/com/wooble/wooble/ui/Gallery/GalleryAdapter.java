package com.wooble.wooble.ui.Gallery;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.GalleryImageLayoutBinding;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{

    Context context;
    List<Gallery> Gallerylist;

    public GalleryAdapter(Context context, List<Gallery> Gallerylist) {
        this.context = context;
        this.Gallerylist = Gallerylist;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gallery_image_layout,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        Gallery gallery = Gallerylist.get(position);

        //loading the image
        Glide.with(context)
                .load(gallery.getImage_url())
                .centerCrop()
                .into(holder.binding.gllImage);
    }

    @Override
    public int getItemCount() {
        return Gallerylist.size();
    }

    public static class GalleryViewHolder extends RecyclerView.ViewHolder{

        GalleryImageLayoutBinding binding;
        public GalleryViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=GalleryImageLayoutBinding.bind(itemView);

        }
    }
}
