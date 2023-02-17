package com.wooble.wooble.ui.Notification;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wooble.wooble.R;
import com.wooble.wooble.databinding.FragmentNotificationBinding;

public class NotificationFragment extends Fragment {
FragmentNotificationBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentNotificationBinding.inflate(inflater, container, false);


        requireActivity().setTitle("Notification");

        return binding.getRoot();
    }
}