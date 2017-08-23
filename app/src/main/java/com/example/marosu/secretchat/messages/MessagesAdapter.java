package com.example.marosu.secretchat.messages;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.model.db.entity.Message;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Marius-Andrei Rosu on 8/10/2017.
 */
public final class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.ViewHolder> {
    private static final int INCOMING_VIEW_TYPE = 0;
    private static final int OUTGOING_VIEW_TYPE = 1;
    private static final SimpleDateFormat MESSAGE_DATE_FORMAT = new SimpleDateFormat("hh:mm a");

    private List<Message> messages;

    public MessagesAdapter(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        final Message message = messages.get(position);
        return isMessageOutgoing(message.getSenderId()) ? OUTGOING_VIEW_TYPE : INCOMING_VIEW_TYPE;
    }

    @Override
    public MessagesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final int resourceId = viewType == OUTGOING_VIEW_TYPE ?
                R.layout.message_outgoing : R.layout.message_incoming;
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(resourceId, parent, false);
        return new MessagesAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MessagesAdapter.ViewHolder holder, int position) {
        final Message message = messages.get(position);
        holder.bind(message);
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void updateMessages(List<Message> messages) {
        this.messages = messages;
        notifyDataSetChanged();
    }

    public int addMessage(Message message) {
        messages.add(message);
        int position = messages.size();
        notifyItemInserted(position);
        return position;
    }

    private boolean isMessageOutgoing(String senderId) {
        return Session.getSession().getUserId().equals(senderId);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.message_content)
        TextView messageContent;

        @BindView(R.id.message_time)
        TextView messageTime;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Message message) {
            messageContent.setText(message.getContent());
            messageTime.setText(MESSAGE_DATE_FORMAT.format(new Date(message.getTimestamp())));
        }
    }
}
