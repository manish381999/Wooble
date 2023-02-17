package com.wooble.wooble.ui.Profile;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wooble.wooble.ui.Blogs.BlogFragment;
import com.wooble.wooble.ui.Gallery.GalleryFragment;
import com.wooble.wooble.ui.Work.ProjectFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {

private String[] titles=new String[]{"Work", "Blogs", "Gallery"};


    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new ProjectFragment();
           case 1:
               return new BlogFragment();
           case 2:
               return new GalleryFragment();
       }
       return new ProjectFragment();
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
