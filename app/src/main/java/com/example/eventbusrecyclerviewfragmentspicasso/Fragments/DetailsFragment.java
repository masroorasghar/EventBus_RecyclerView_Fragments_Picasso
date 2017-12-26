package com.example.eventbusrecyclerviewfragmentspicasso.Fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.eventbusrecyclerviewfragmentspicasso.Models.Contact;
import com.example.eventbusrecyclerviewfragmentspicasso.R;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;


public class DetailsFragment extends Fragment {

    private static final String ARG_PARAM = "Contact";

    private static Gson gson = new Gson();
    private Contact contact;
    TextView id, name, email, phone;
    ImageView circleImageView;
    FloatingActionButton fab;

    public static DetailsFragment newInstance(Contact contact) {
        DetailsFragment fragment = new DetailsFragment();
        String contactData = gson.toJson(contact);
        Bundle args = new Bundle();
        args.putString(ARG_PARAM, contactData);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);
        fab = view.findViewById(R.id.fab);

        id = view.findViewById(R.id.id);
        name = view.findViewById(R.id.name);
        email = view.findViewById(R.id.email);
        phone = view.findViewById(R.id.phone);
        circleImageView = view.findViewById(R.id.details_circularImage);

        Bundle bundle = getArguments();
        contact = gson.fromJson(bundle.getString(ARG_PARAM), Contact.class);

        id.setText(String.valueOf(contact.getId()));
        name.setText(contact.getName());
        email.setText(contact.getEmail());
        phone.setText(contact.getPhone());
        Picasso.with(getActivity())
                .load("http://lorempixel.com/400/200/sports/" + contact.getName() + "/")
                .placeholder(R.drawable.placeholder)
                .error(android.R.drawable.stat_notify_error)
                .into(circleImageView);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri call = Uri.parse("tel: " + contact.getPhone());
                Intent intent = new Intent(Intent.ACTION_DIAL, call);
                getActivity().startActivity(intent);
            }
        });
        return view;
    }

}
