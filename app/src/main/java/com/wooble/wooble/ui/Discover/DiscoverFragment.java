package com.wooble.wooble.ui.Discover;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import com.wooble.wooble.R;



import com.wooble.wooble.databinding.FragmentDiscoverBinding;
import com.wooble.wooble.ui.portfolio.Edit_Portfolio_Activity;
import com.wooble.wooble.ui.portfolio.PortfolioActivity;


public class DiscoverFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

 FragmentDiscoverBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentDiscoverBinding.inflate(inflater, container, false);

        requireActivity().setTitle("Discover");

        return binding.getRoot();

  }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NavController navController= Navigation.findNavController(view);

        AppBarConfiguration configuration=new AppBarConfiguration.Builder(navController.getGraph())
                .setDrawerLayout(binding.drawerLayout).build();
        NavigationUI.setupWithNavController(binding.navigationDrawer, navController);
        NavigationUI.setupWithNavController(binding.toolbar,navController,configuration);

        binding.navigationDrawer.setNavigationItemSelectedListener(this);
    }

        @SuppressLint("NonConstantResourceId")
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.navigation_portfolio:
                startActivity(new Intent(getActivity(), PortfolioActivity.class));
                break;

            case R.id.navigation_template:

            case R.id.navigation_email_signature:

            case R.id.navigation_edit_portfolio:
                startActivity(new Intent(getActivity(), Edit_Portfolio_Activity.class));
                break;

            case R.id.navigation_resume:
                Toast.makeText(getContext(), "Resume", Toast.LENGTH_SHORT).show();
                break;




        }
        return true;
    }


}