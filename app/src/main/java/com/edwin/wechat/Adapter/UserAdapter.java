package com.edwin.wechat.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.edwin.wechat.MessageActivity;
import com.edwin.wechat.Model.User;
import com.edwin.wechat.R;
import com.edwin.wechat.StartActivity;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    List<User> musers;
    Context context;

    public UserAdapter(List<User> musers, Context context) {
        this.musers = musers;
        this.context = context;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.user_item,parent,false);
        return new UserAdapter.UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

        final User user = musers.get(position);

        holder.user_username.setText(user.getUsername());

        if (user.getImageURL().equals("default")){
            holder.user_profile.setImageResource(R.mipmap.ic_launcher);
        }
        else{
            Glide.with(context).load(user.getImageURL()).into(holder.user_profile);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MessageActivity.class);
                intent.putExtra("userid",user.getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return musers.size();
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        public TextView user_username;
        public CircleImageView user_profile;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            user_profile = itemView.findViewById(R.id.user_profile);
            user_username = itemView.findViewById(R.id.user_username);
        }
    }
}
