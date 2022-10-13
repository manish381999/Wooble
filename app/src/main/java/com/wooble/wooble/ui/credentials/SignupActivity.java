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
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity {
ActivitySignupBinding binding;



    private EditText etname, etemail,etmobileNumber,etpassword;
    private Button btnSignup;
    private String URL="http://172.168.2.86/api/register.php";
//    private String URL="https://test.wooble.org/android_connection.php";
    private String name, email, mobile_no, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide();
        gotoLogin();

        etname = (EditText) findViewById(R.id.signup_name);
        etemail = (EditText) findViewById(R.id.signup_email);
        etmobileNumber = (EditText) findViewById(R.id.signup_mobile_no);
        etpassword = (EditText) findViewById(R.id.signup_password);
        btnSignup = (Button) findViewById(R.id.sign_btn);
        name=email=mobile_no=password="";


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etname.getText().toString().trim();
                email = etemail.getText().toString().trim();
                mobile_no = etmobileNumber.getText().toString().trim();
                password = etpassword.getText().toString().trim();

                if(!email.equals("") && !password.equals("") && !mobile_no.equals("") && !email.equals(""))
                {
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//                            System.out.println("Response" + response);
                            if (response.equals("success")) {
                                Toast.makeText(SignupActivity.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            } else if (response.equals("failure")) {
                                Toast.makeText(SignupActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }else if(response.equals("exist")){
                                Toast.makeText(SignupActivity.this, "Email already Exist Please Login", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(SignupActivity.this, error.toString().trim(),Toast.LENGTH_SHORT).show();

                        }
                    }){
                        @Nullable
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> data = new HashMap<>();
                            data.put("name",name);
                            data.put("mobile",mobile_no);
                            data.put("email",email);
                            data.put("password", password);
                            return data;
                        }
                    };

                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    requestQueue.add(stringRequest);

                }else{
                    Toast.makeText(SignupActivity.this, "fields can not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public static boolean isValidEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    private void gotoLogin() {
        binding.gotoLogin.setOnClickListener(view -> {
            Intent intent=new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }


}