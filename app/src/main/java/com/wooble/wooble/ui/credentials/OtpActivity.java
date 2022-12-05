package com.wooble.wooble.ui.credentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.databinding.ActivityOtpBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class OtpActivity extends AppCompatActivity {
ActivityOtpBinding binding;
    private String email,otp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        email=getIntent().getStringExtra("email");
        binding.tv3.setText(email);

        binding.btnVerify.setOnClickListener(view -> {
            binding.btnVerify.setEnabled(false);
            otp = binding.otpView.getOTP();
            if(!email.equals(null) && !otp.equals(null))
            {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.CHECK_OTP, response -> {
                    if (response.equals("success")) {
                        Intent intent=new Intent(OtpActivity.this, Reset_PasswordActivity.class);
                        intent.putExtra("email",email);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(OtpActivity.this, "Wrong OTP", Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(OtpActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show()){
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> data = new HashMap<>();
                        data.put("email",email);
                        data.put("otp",otp);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }else{
                Toast.makeText(OtpActivity.this, "fields can not be empty", Toast.LENGTH_SHORT).show();
            }



        });

    }
}