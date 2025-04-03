package com.example.stiveworkoutapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.*;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.FriendViewHolder> {
    private List<Friend> friends;
    private Context context;

    public FriendsAdapter(List<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.friend_item, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friends.get(position);
        holder.nameTextView.setText(friend.getName());

        holder.viewProfileButton.setOnClickListener(v -> {
            Intent intent = new Intent(context, AccountActivity.class);
            context.startActivity(intent);
            ((Activity) context).overridePendingTransition(0, 0);
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView;
        Button viewProfileButton;
        Button pokeButton;

        FriendViewHolder(View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.friend_name);
            viewProfileButton = itemView.findViewById(R.id.view_profile_button);
            pokeButton = itemView.findViewById(R.id.poke_button);
        }
    }
}