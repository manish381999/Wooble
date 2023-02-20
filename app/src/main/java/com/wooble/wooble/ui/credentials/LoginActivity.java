package com.wooble.wooble.ui.credentials;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.MainActivity;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityLoginBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
ActivityLoginBinding binding;
    private EditText etEmail, etPassword;
    private String email, password;
    //private final String URL="https://172.168.0.182/wooble-api/login.php";
    private String URL="https://app.wooble.org/android_dir/login.php";
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        etEmail = findViewById(R.id.login_email);
        etPassword = findViewById(R.id.login_password);
        Button loginButton = findViewById(R.id.login_btn);
        gotoSignup();

        gotoForgot_Password();
        loginButton.setOnClickListener(view -> {
            email = etEmail.getText().toString().trim();
            password = etPassword.getText().toString().trim();
            if(!email.equals("") && !password.equals(""))
            {
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, response -> {
                    if (response.equals("success")) {
                        User user = new User(email,password);
                        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
                        sessionManagement.saveSession(user);
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(LoginActivity.this, "Invalid id/password", Toast.LENGTH_SHORT).show();
                    }
                }, error -> Toast.makeText(LoginActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show()){
                    @NonNull
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String,String> data = new HashMap<>();
                        data.put("email",email);
                        data.put("password", password);
                        return data;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);
            }else{
                Toast.makeText(LoginActivity.this, "fields can not be empty", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void gotoForgot_Password() {
        binding.forgotPasswordTv.setOnClickListener(view -> {
            Intent intent=new Intent(LoginActivity.this,Forgot_PasswordActivity.class);
            startActivity(intent);
        });
    }
    private void gotoSignup(){
        binding.gotoSignup.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this,SignupActivity.class);
            startActivity(intent);

        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        SessionManagement sessionManagement = new SessionManagement(LoginActivity.this);
        String userEmail = sessionManagement.getSessionEmail();
        String userPassword = sessionManagement.getSessionPassword();

        if(userEmail != null && userPassword != null){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
            else{
        }
    }
}