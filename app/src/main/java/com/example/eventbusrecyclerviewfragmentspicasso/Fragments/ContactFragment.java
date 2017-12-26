package com.example.eventbusrecyclerviewfragmentspicasso.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;

import com.example.eventbusrecyclerviewfragmentspicasso.Adapters.ContactAdapter;
import com.example.eventbusrecyclerviewfragmentspicasso.R;

import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;

public class ContactFragment extends Fragment {

    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_contact, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new SlideInRightAnimator(new OvershootInterpolator(1f)));

        ContactAdapter contactAdapter = new ContactAdapter(getContext());
        recyclerView.setAdapter(contactAdapter);

        return view;
    }

}
