package com.wooble.wooble.ui.credentials;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.R;
import com.wooble.wooble.databinding.ActivitySignupBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    //private final String URL = "http://172.168.0.182/wooble-api/register.php";
    private String URL="https://app.wooble.org/android_dir/register.php";
    private String name, email, mobile_no, password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Objects.requireNonNull(getSupportActionBar()).hide();
        gotoLogin();
        name = email = mobile_no = password = "";
        binding.signBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = binding.signupName.getText().toString().trim();
                email = binding.signupEmail.getText().toString().trim();
                mobile_no = binding.signupMobileNo.getText().toString().trim();
                password = binding.signupPassword.getText().toString().trim();

                if (!email.equals("") && !password.equals("") && !mobile_no.equals("") && !email.equals("") && !name.equals("")) {
                    if(isValidEmail(email)==false){
                        Toast.makeText(SignupActivity.this, "Please Enter valid Email", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if(isValidMobile(mobile_no)==false){
                        Toast.makeText(SignupActivity.this, "Please Enter valid Mobile No", Toast.LENGTH_SHORT).show();
                        return;
                    }


                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("success")) {
                                Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.contains("failure")) {
                                Toast.makeText(SignupActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            } else if (response.contains("exist")) {
                                Toast.makeText(SignupActivity.this, "Email already Exist! Please Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignupActivity.this, error.toString().trim(), Toast.LENGTH_SHORT).show();

                        }
                    }) {
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String, String> data = new HashMap<>();
                            data.put("name", name);
                            data.put("mobile", mobile_no);
                            data.put("email", email);
                            data.put("password", password);
                            return data;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                } else {
                    Toast.makeText(SignupActivity.this, "fields can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean isValidMobile(String s)
    {
        Pattern p = Pattern.compile("(0|91)?[6-9][0-9]{9}");
        Matcher m = p.matcher(s);
        return (m.find() && m.group().equals(s));
    }
    private void gotoLogin() {
        binding.gotoLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}