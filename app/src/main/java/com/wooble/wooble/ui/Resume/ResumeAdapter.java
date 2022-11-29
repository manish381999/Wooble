package com.wooble.wooble.ui.Resume;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ResumeItemLayoutBinding;

import java.util.List;

public class ResumeAdapter extends RecyclerView.Adapter<ResumeAdapter.ResumeViewHolder> {
Context context;
    List<ResumeModel> ResumeList;

    public ResumeAdapter(Context context, List list) {
        this.context = context;
        this.ResumeList = list;
    }

    @NonNull
    @Override
    public ResumeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.resume_item_layout,parent,false);
        return new ResumeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResumeViewHolder holder, int position) {
        ResumeModel resumeModel = ResumeList.get(position);
        holder.binding.tvPdfTitle.setText(resumeModel.getTitle()+".pdf");
//        holder.binding.pdfDownload.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = resumeModel.getResume();
//                DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
//                request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
//                request.setTitle("Download");
//                request.setDescription("Downloading file...");
//                request.allowScanningByMediaScanner();
//                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
//                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());
//
//                DownloadManager manager = (DownloadManager) context.getSystemService(context.DOWNLOAD_SERVICE);
//                manager.enqueue(request);
//
//
//
//
//
//            }
//        });

        holder.binding.pdfViewer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, Resume_Viewer_Activity.class);
                intent.putExtra("pdfUrl", resumeModel.getResume());
                context.startActivity(intent);
            }
        });

        holder.binding.pdfDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(resumeModel.getResume()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return ResumeList.size();
    }

    public static class ResumeViewHolder extends RecyclerView.ViewHolder{

        ResumeItemLayoutBinding binding;
        public ResumeViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=ResumeItemLayoutBinding.bind(itemView);
        }
    }
}
