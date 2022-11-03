package com.wooble.wooble.ui.Gallery;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.GalleryImageLayoutBinding;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>{

    Context context;
    List<GalleryModel> Gallerylist;

    public GalleryAdapter(Context context, List<GalleryModel> gallerylist) {
        this.context = context;
        Gallerylist = gallerylist;
    }



    @Override
    public GalleryViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.gallery_image_layout,null);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryViewHolder holder, int position) {

        GalleryModel gallery = Gallerylist.get(position);

        //loading the image
        Glide.with(context)
                .load(gallery.getImage_url())
                .centerCrop()
                .into(holder.binding.gllImage);

        holder.binding.imageTitle.setText(gallery.getTitle());

        holder.binding.gllImage.setOnClickListener(v -> {
            Intent intent=new Intent(context, Full_ImageActivity.class);
            intent.putExtra("image",gallery.getImage_url());
            intent.putExtra("title",gallery.getTitle());
            intent.putExtra("description",gallery.getDescription());
            context.startActivity(intent);
        });


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
