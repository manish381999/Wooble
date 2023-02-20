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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.tabs.TabLayoutMediator;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.R;

import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.FragmentProfileBinding;
import com.wooble.wooble.ui.Blogs.Create_BlogsActivity;
import com.wooble.wooble.ui.Gallery.ImageUploaderActivity;
import com.wooble.wooble.ui.Work.Upload_Project_Activity;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ProfileFragment extends Fragment {
    FragmentProfileBinding binding;
ViewPagerAdapter viewPagerAdapter;
    BottomSheetDialog bottomSheetDialog;

    String profileEmail;
    String profileImage;
    String coverImage;
    CircularImageView circularImageView;
    ImageView coverPic;
    TextView navUsername;
    TextView navProfession;

    private String[] titles=new String[]{"Work", "Blogs", "Gallery"};

    private int[] tabIcons={R.drawable.ic_work, R.drawable.ic_blog, R.drawable.ic_gallery};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Profile");


        navUsername = binding.tvUserName;
        navProfession = binding.tvProfession;
        circularImageView = binding.profilePic;
        coverPic = binding.coverPic;
        loadProfileImage();
        loadProfileData();
        loadCoverImage();


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
                        Intent intent=new Intent(getActivity(), Create_BlogsActivity.class);
                        startActivity(intent);
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

    public void loadProfileImage() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_ONLY_PROFILE_PIC,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            profileImage = jObj.getString("image");
                            //Log.d("image",profileImage);
                            Glide.with(ProfileFragment.this)
                                    .load(profileImage)
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(circularImageView);
                            //Log.d("image",date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }
    public void loadProfileData() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_FULL_PROFILE,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            navUsername.setText(jObj.getString("fullname"));
                            navProfession.setText(jObj.getString("designation"));
                            //Log.d("fullname",jObj.getString("fullname"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                return params;
            }

        };

        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }
    public void loadCoverImage() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_ONLY_COVER_PIC,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            //Log.d("image","image");
                            JSONArray array = new JSONArray(new String(response.data));
                            //Toast.makeText(getApplicationContext(), obj.getString("image"), Toast.LENGTH_SHORT).show();
                            JSONObject jObj = array.getJSONObject(0);
                            coverImage = jObj.getString("image");
                            //Log.d("image",profileImage);
                            Glide.with(ProfileFragment.this)
                                    .load(coverImage)
                                    .placeholder(R.drawable.place_holder)
                                    .centerCrop()
                                    .into(coverPic);
                            //Log.d("image",date);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("profileEmail", profileEmail);
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }

}