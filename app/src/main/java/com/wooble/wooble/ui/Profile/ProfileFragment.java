package com.wooble.wooble.ui.Profile;

import static android.app.Activity.RESULT_OK;


import android.content.Intent;


import android.net.Uri;
import android.os.Bundle;


import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


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

import com.wooble.wooble.R;

import com.wooble.wooble.SessionManagement;


import com.wooble.wooble.databinding.FragmentProfileBinding;
import com.wooble.wooble.ui.Blogs.Create_BlogsActivity;
import com.wooble.wooble.ui.Gallery.Gallery_Image_CropperActivity;
import com.wooble.wooble.ui.Gallery.GifUploaderActivity;
import com.wooble.wooble.ui.Gallery.ImageUploaderActivity;
import com.wooble.wooble.ui.Gallery.VideoUploaderActivity;
import com.wooble.wooble.ui.Work.WorkUploaderActivity;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.HashMap;

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
    TextView navFullname;
    TextView navProfession;

    final int REQ = 12;
    final int REQ_video = 80;
   final int REQ_gif=70;
    Uri video_Uri, gif_Uri;


        ActivityResultLauncher<String> pickImage;

    private String[] titles=new String[]{"Work", "Blogs", "Gallery"};

    private int[] tabIcons={R.drawable.ic_work, R.drawable.ic_blog, R.drawable.ic_gallery};




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentProfileBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Profile");

        navFullname = binding.tvFullName;
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
                        Intent intent=new Intent(getActivity(), WorkUploaderActivity.class);
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
                        bottomSheetDialog=new BottomSheetDialog(getContext(),R.style.BottomSheetStyle);

                        View view1=inflater.inflate(R.layout.gallery_bottom_sheet_layout,null);
                        LinearLayout linearLayout1=view1.findViewById(R.id.gallery_sheet);

                        view1.findViewById(R.id.iv_addImage).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openGalleryImage();
                            }
                        });

                        view1.findViewById(R.id.iv_addVideo).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                              openGalleryVideo();
                            }
                        });

                        view1.findViewById(R.id.iv_addGif).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                openGalleryGif();
                            }
                        });


                        bottomSheetDialog.setContentView(view1);
                        bottomSheetDialog.show();
                    }
                });


                bottomSheetDialog.setContentView(view);
                bottomSheetDialog.show();

            }
        });


            pickImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            public void onActivityResult(Uri result) {
                Intent intent = new Intent(getActivity(), Gallery_Image_CropperActivity.class);
                intent.putExtra("DATA", result.toString());
                startActivityForResult(intent, REQ);
            }
        });

        return binding.getRoot();
    }

    private void openGalleryGif() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,REQ_gif);
    }

    private void openGalleryVideo() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQ_video);
    }



    private void openGalleryImage() {
        pickImage.launch("image/*");
    }


        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ) {
            String result = data.getStringExtra("RESULT");
     //       Uri resultUri = null;
//            if (result != null) {
//                resultUri = Uri.parse(result);
//            }
           Intent intent=new Intent(getActivity(),ImageUploaderActivity.class);
            intent.putExtra("image", result);
//            System.out.println("image"+BitMapToString(bitmap));
            startActivity(intent);
        }else if (requestCode == REQ_video && resultCode == RESULT_OK && data != null){
            video_Uri = data.getData();
            Intent intent=new Intent(getActivity(),VideoUploaderActivity.class);
            intent.putExtra("video", video_Uri.toString());
            startActivity(intent);

//            video = fileUriToBase64(video_Uri, getActivity().getContentResolver());
        }else if (requestCode==REQ_gif && resultCode==RESULT_OK && data!=null){
            gif_Uri=data.getData();
            Intent intent=new Intent(getActivity(), GifUploaderActivity.class);
            intent.putExtra("gif", gif_Uri.toString());
            startActivity(intent);


        }

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
                            binding.tvUserName.setText(jObj.getString("username"));
                            binding.tvFullName.setText(jObj.getString("fullname"));
                            binding.tvProfession.setText(jObj.getString("designation"));

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