package com.wooble.wooble.ui.credentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityForgotPasswordBinding;
import com.wooble.wooble.ui.Blogs.Controller;
import com.wooble.wooble.ui.Blogs.ResponseModel;
import com.wooble.wooble.ui.Resume.ResumeFragment;
import com.wooble.wooble.ui.Resume.UploadResumeActivity;
import com.wooble.wooble.ui.portfolio.EndPoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;

public class Forgot_PasswordActivity extends AppCompatActivity {
ActivityForgotPasswordBinding binding;
    private String email;
    String output="hii";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityForgotPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();

    gotoLogin();

   binding.passwordRecoverBtn.setOnClickListener(view -> {

       binding.passwordRecoverBtn.setEnabled(false);
       email = binding.forgotPassword.getText().toString().trim();
       if(!email.equals(""))
       {
           StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.CHECK_EMAIL, response -> {
               if (response.equals("success")) {
                   Intent intent = new Intent(Forgot_PasswordActivity.this, OtpActivity.class);
                   intent.putExtra("email",email);
                   startActivity(intent);
                   finish();
               } else if (response.equals("failure")) {
                   Toast.makeText(Forgot_PasswordActivity.this, "Invalid email", Toast.LENGTH_SHORT).show();
               }
           }, error -> Toast.makeText(Forgot_PasswordActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show()){
               @NonNull
               @Override
               protected Map<String, String> getParams() {
                   Map<String,String> data = new HashMap<>();
                   data.put("email",email);
                   return data;
               }
           };
           RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

           requestQueue.add(stringRequest).setRetryPolicy(new RetryPolicy() {
               @Override
               public int getCurrentTimeout() {
                   return 0;
               }

               @Override
               public int getCurrentRetryCount() {
                   return 0;
               }

               @Override
               public void retry(VolleyError error) throws VolleyError {

               }
           });
       }else{
           Toast.makeText(Forgot_PasswordActivity.this, "fields can not be empty", Toast.LENGTH_SHORT).show();
       }

   });
    }
    private void gotoLogin(){
        binding.gotoLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Forgot_PasswordActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }
}