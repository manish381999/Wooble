package com.wooble.wooble.ui.portfolio;


import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.webkit.WebViewClient;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.Volley;
import com.wooble.wooble.SessionManagement;
import com.wooble.wooble.databinding.FragmentPortfolioBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class PortfolioFragment extends Fragment {

 FragmentPortfolioBinding binding;

    private String profileEmail;
    private String username;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentPortfolioBinding.inflate(inflater, container, false);
        requireActivity().setTitle("Portfolio");
//        binding.addPortfolio.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent=new Intent(getActivity(), Create_Portfolio_Activity.class);
//                startActivity(intent);
//            }
//        });
        loadPortfolio();
        return binding.getRoot();
    }
    public void loadPortfolio() {
        SessionManagement sessionManagement = new SessionManagement(getContext());
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
        Volley.newRequestQueue(getContext()).add(volleyMultipartRequest);
    }
}