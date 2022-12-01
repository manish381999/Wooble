package com.wooble.wooble.ui.credentials;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivityResetPasswordBinding;
import com.wooble.wooble.ui.portfolio.EndPoints;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reset_PasswordActivity extends AppCompatActivity {
ActivityResetPasswordBinding binding;
    private String email;
    private String password,confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityResetPasswordBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();

        email=getIntent().getStringExtra("email");

        binding.btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.btnSubmit.setEnabled(false);
                password = binding.etNewPassword.getText().toString().trim();
                confirm_password = binding.etConfirmNewPassword.getText().toString().trim();

                if( !email.equals(null) && !password.equals(null) & !confirm_password.equals(null))
                {
                    if(!password.equals(confirm_password)){
                        Toast.makeText(Reset_PasswordActivity.this, "Confirm Password is not matching", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, EndPoints.RESET_PASSWORD, response -> {
                        if (response.equals("success")) {
                            Intent intent=new Intent(Reset_PasswordActivity.this, LoginActivity.class);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        } else if (response.equals("failure")) {
                            Toast.makeText(Reset_PasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }, error -> Toast.makeText(Reset_PasswordActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show()){
                        @NonNull
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String,String> data = new HashMap<>();
                            data.put("email",email);
                            data.put("password",password);
                            return data;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);
                }else{
                    Toast.makeText(Reset_PasswordActivity.this, "Please a put a valid input", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

}