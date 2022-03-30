package com.example.pawfect_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter {
    private ArrayList<ResponseStorage> responseStorageArrayList;
    private Context context;

    public ChatAdapter(ArrayList<ResponseStorage> responseStorageArrayList, Context context) {
        this.responseStorageArrayList = responseStorageArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_message_response, parent, false);
                return new UserViewHolder(view);
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bot_message_response, parent, false);
                return new BotViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ResponseStorage responseStorage = responseStorageArrayList.get(position);
        switch (responseStorage.getSender()){
            case "user":
                ((UserViewHolder)holder).userTV.setText(responseStorage.getMessage());
                break;
            case "bot":
                ((BotViewHolder)holder).botMsgTV.setText(responseStorage.getMessage());
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        switch (responseStorageArrayList.get(position).getSender()){
            case "user":
                return 0;
            case "bot":
                return 1;
            default:
                return -1;
        }
    }


    @Override
    public int getItemCount() {
        return responseStorageArrayList.size();

    }
    public static class UserViewHolder extends RecyclerView.ViewHolder{
        TextView userTV;
        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            userTV = itemView.findViewById(R.id.idTVUser);
        }
    }

    public static class BotViewHolder extends RecyclerView.ViewHolder{
        TextView botMsgTV;
        public BotViewHolder(@NonNull View itemView) {
            super(itemView);
            botMsgTV = itemView.findViewById(R.id.idTVBot);
        }
    }
}
