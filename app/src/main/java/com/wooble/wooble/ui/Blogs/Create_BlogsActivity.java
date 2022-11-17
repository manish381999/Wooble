package com.wooble.wooble.ui.Blogs;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityCreateBlogsBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;
import com.wooble.wooble.ui.portfolio.VolleyMultipartRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;


public class Create_BlogsActivity extends AppCompatActivity {
    ActivityCreateBlogsBinding binding;


//    String imageView;
    Bitmap bitmap;
    private String profileEmail;

    String image = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateBlogsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBar);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_arrow_back_);


binding.publish.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
//        insertBlogData();
        insertData();
    }
});

    }


    private String getBase64String(Bitmap bitmap)
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();

        String base64String = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            base64String = Base64.getEncoder().encodeToString(imageBytes);
        }
        return base64String;

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==33 && resultCode==RESULT_OK && data!=null){
            Uri uri=data.getData();

            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);

            } catch (IOException e) {
                e.printStackTrace();
            }
            ImageView imageView=new ImageView(Create_BlogsActivity.this);
            imageView.setImageBitmap(bitmap);
            addView(imageView, 400, 400);
            image = getBase64String(bitmap);

        }

        }

       public void addView(ImageView imageView, int width, int height){
           LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(width, height);
           layoutParams.setMargins(5,0,5,0);

           imageView.setLayoutParams(layoutParams);
           binding.linerLayout.addView(imageView);
       }


    public void bold_btn(View view) {
        binding.boldBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
                spannableString.setSpan(new StyleSpan(Typeface.BOLD),
                        binding.createDescription.getSelectionStart(),
                        binding.createDescription.getSelectionEnd(),0);

                binding.createDescription.setText(spannableString);
            }
        });

    }

    public void italic_btn(View view) {
        binding.italicBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
                spannableString.setSpan(new StyleSpan(Typeface.ITALIC),
                        binding.createDescription.getSelectionStart(),
                        binding.createDescription.getSelectionEnd(),0);

                binding.createDescription.setText(spannableString);
            }
        });
    }

    public void underline_btn(View view) {
        binding.underlineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
                spannableString.setSpan(new UnderlineSpan(),
                        binding.createDescription.getSelectionStart(),
                        binding.createDescription.getSelectionEnd(),0);

                binding.createDescription.setText(spannableString);
            }
        });
    }

    public void default_btn(View view) {

        String stringText=binding.createDescription.getText().toString();
        binding.createDescription.setText(stringText);
    }

    public void left_btn(View view) {
        binding.createDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
        Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
        binding.createDescription.setText(spannableString);

    }

    public void center_btn(View view) {
        binding.createDescription.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
        binding.createDescription.setText(spannableString);
    }

    public void right_btn(View view) {
        binding.createDescription.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
        Spannable spannableString=new SpannableStringBuilder(binding.createDescription.getText());
        binding.createDescription.setText(spannableString);
    }


    public void add_image_btn(View view) {
        Intent intent=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 33);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void insertBlogData() {

        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        String title = binding.createTitle.getText().toString().trim();
        String content = binding.createDescription.getText().toString().trim();

        //our custom volley request
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.INSERT_BLOG_DATA,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONObject obj = new JSONObject(new String(response.data));
                            Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(Create_BlogsActivity.this, BlogFragment.class);
                            startActivity(intent);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email_id", profileEmail);
                params.put("title", title);
                params.put("content", content);
                return params;
            }

        };

        //adding the request to volley
        Volley.newRequestQueue(this).add(volleyMultipartRequest);
    }

    void insertData(){
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        String email_id = sessionManagement.getSessionEmail();
        String title = binding.createTitle.getText().toString().trim();
        String content = binding.createDescription.getText().toString().trim();
        String name = String.valueOf(System.currentTimeMillis());

        Call<ResponseModel> call = Controller.getInstance()
                .getApiInterface()
                .insertBlog(email_id,title,content,image,name);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, retrofit2.Response<ResponseModel> response) {
                ResponseModel responseModel = response.body();
                String output = responseModel.getMessage();
                Toast.makeText(Create_BlogsActivity.this, output, Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Create_BlogsActivity.this, BlogFragment.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Toast.makeText(Create_BlogsActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
