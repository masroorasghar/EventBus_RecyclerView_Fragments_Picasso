package com.example.eventbusrecyclerviewfragmentspicasso.Adapters;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.eventbusrecyclerviewfragmentspicasso.DetailsActivity;
import com.example.eventbusrecyclerviewfragmentspicasso.Models.Contact;
import com.example.eventbusrecyclerviewfragmentspicasso.R;
import com.squareup.picasso.Picasso;


import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private List<Contact> list;
    private Context context;

    public ContactAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
        for (int i = 0; i < 1000; i++) {
            list.add(new Contact((i + 1), "Person " + (i + 1), "Person_" + (i + 1) + "@example.com", "+92 " + (i + 1)));
        }
    }

    class ContactViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircleImageView circleImageView;
        TextView name;
        Button button;

        private ContactViewHolder(View itemView) {
            super(itemView);
            circleImageView = itemView.findViewById(R.id.card_imageView);
            name = itemView.findViewById(R.id.card_textView);
            button = itemView.findViewById(R.id.card_button);
            itemView.setOnClickListener(this);
            button.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == itemView.getId()) {
                Intent intent = new Intent(context, DetailsActivity.class);
                context.startActivity(intent);
                EventBus.getDefault().postSticky(list.get(getAdapterPosition()));
            } else if (view.getId() == button.getId()) {
                removeItem(getAdapterPosition());
                Snackbar.make(view, "Item removed successfully", Snackbar.LENGTH_SHORT)
                        .setAction("UNDO", new View.OnClickListener() {

                            @Override
                            public void onClick(View view) {
                                //TODO: Implement undo logic here
                            }
                        })
                        .setActionTextColor(Color.CYAN)
                        .show();

            }
        }
    }

    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.simple_contact_list_card_item, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactAdapter.ContactViewHolder holder, int position) {
        Contact contact = list.get(position);
        Picasso.with(context)
                .load("http://lorempixel.com/400/200/sports/" + contact.getName() + "/")
                .placeholder(R.drawable.placeholder)
                .error(android.R.drawable.stat_notify_error)
                .into(holder.circleImageView);
        holder.name.setText(contact.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void removeItem(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }
}
