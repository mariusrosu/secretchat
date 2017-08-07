package com.example.marosu.secretchat.chatList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.model.Conversation;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private List<Conversation> conversations;

    public ChatListAdapter(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.conversation_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Conversation conversation = conversations.get(position);
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public void updateConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
