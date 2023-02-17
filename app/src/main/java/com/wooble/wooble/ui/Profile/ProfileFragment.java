package com.wooble.wooble.ui.Profile;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayoutMediator;
import com.wooble.wooble.R;

import com.wooble.wooble.databinding.FragmentProfileBinding;
import com.wooble.wooble.ui.Gallery.ImageUploaderActivity;
import com.wooble.wooble.ui.Work.Upload_Project_Activity;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
ViewPagerAdapter viewPagerAdapter;
    BottomSheetDialog bottomSheetDialog;

    private String[] titles=new String[]{"Work", "Blogs", "Gallery"};

    private int[] tabIcons={R.drawable.ic_work, R.drawable.ic_blog, R.drawable.ic_gallery};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Profile");


        viewPagerAdapter =new ViewPagerAdapter(getActivity());
        binding.viewPager2.setAdapter(viewPagerAdapter);

        new TabLayoutMediator(binding.tabLayout,binding.viewPager2,((tab, position) -> tab.setText(titles[position]))).attach();

        setupTabIcons();

        binding.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog=new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);

                View view=inflater.inflate(R.layout.bottom_sheet_layout,null);
                LinearLayout linearLayout=view.findViewById(R.id.sheet);

                view.findViewById(R.id.edit_profile).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), Edit_Profile_Activity.class);
                        startActivity(intent);
                    }
                });

                view.findViewById(R.id.add_work).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), Upload_Project_Activity.class);
                        startActivity(intent);
                    }
                });

                view.findViewById(R.id.add_blogs).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "Blogs", Toast.LENGTH_SHORT).show();
                    }
                });

                view.findViewById(R.id.add_gallery).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(), ImageUploaderActivity.class);
                        startActivity(intent);
                    }
                });


                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

            }
        });


        return binding.getRoot();
    }

    private void setupTabIcons() {
      binding.tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        binding.tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        binding.tabLayout.getTabAt(2).setIcon(tabIcons[2]);


    }

}