package com.wooble.wooble.ui.Gallery;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.FullImageLayoutBinding;

import java.util.List;

public class Full_image_Adapter extends RecyclerView.Adapter<Full_image_Adapter.FullImageViewHolder> {
Context context;
List list;

    public Full_image_Adapter(Context context, List list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FullImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.full_image_layout,parent,false);
        return new FullImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FullImageViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class FullImageViewHolder extends RecyclerView.ViewHolder{
FullImageLayoutBinding binding;
        public FullImageViewHolder(@NonNull View itemView) {
            super(itemView);

            binding=FullImageLayoutBinding.bind(itemView);
        }
    }
}
