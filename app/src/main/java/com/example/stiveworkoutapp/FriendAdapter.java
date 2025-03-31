package com.example.stiveworkoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.stiveworkoutapp.R;
import com.example.stiveworkoutapp.Friend;

import java.util.List;

public class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {

    private Context context;
    private List<Friend> friendsList;

    public FriendAdapter(Context context, List<Friend> friendsList) {
        this.context = context;
        this.friendsList = friendsList;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.friend_item, parent, false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {
        Friend friend = friendsList.get(position);
        holder.usernameText.setText(friend.getUsername());
        holder.profileImage.setImageResource(friend.getProfileImageResId());

        // Handle button clicks (for now just log actions)
        holder.viewProfileBtn.setOnClickListener(v -> {
            System.out.println("View Profile clicked for " + friend.getUsername());
        });

        holder.pokeBtn.setOnClickListener(v -> {
            System.out.println("Poked " + friend.getUsername());
        });
    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }

    static class FriendViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView usernameText;
        Button viewProfileBtn, pokeBtn;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profileImage);
            usernameText = itemView.findViewById(R.id.usernameText);
            viewProfileBtn = itemView.findViewById(R.id.viewProfileBtn);
            pokeBtn = itemView.findViewById(R.id.pokeBtn);
        }
    }
}
