package com.wooble.wooble.ui.portfolio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebViewClient;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.R;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.ActivityEditGalleryBinding;
import com.wooble.wooble.databinding.ActivityPortfolioBinding;
import com.wooble.wooble.databinding.FragmentPortfolioBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PortfolioActivity extends AppCompatActivity {
    ActivityPortfolioBinding binding;

    private String profileEmail;
    private String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPortfolioBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        loadPortfolio();
    }
    public void loadPortfolio() {
        SessionManagement sessionManagement = new SessionManagement(getApplicationContext());
        profileEmail = sessionManagement.getSessionEmail();
        VolleyMultipartRequest volleyMultipartRequest = new VolleyMultipartRequest(Request.Method.POST, EndPoints.GET_FULL_PROFILE,
                new Response.Listener<NetworkResponse>() {
                    @Override
                    public void onResponse(NetworkResponse response) {
                        try {
                            JSONArray array = new JSONArray(new String(response.data));
                            JSONObject jObj = array.getJSONObject(0);
                            username=jObj.getString("username");
                            binding.web.loadUrl("https://wooble.org/"+username);
                            binding.web.getSettings().setJavaScriptEnabled(true);
                            binding.web.setWebViewClient(new WebViewClient());

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
        Volley.newRequestQueue(getApplicationContext()).add(volleyMultipartRequest);
    }
}