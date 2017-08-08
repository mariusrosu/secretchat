package com.example.marosu.secretchat.chatList;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.model.Conversation;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ViewHolder> {
    private List<Conversation> conversations;
    private final PublishSubject<String> onClickSubject;

    public ChatListAdapter(List<Conversation> conversations) {
        this.conversations = conversations;
        this.onClickSubject = PublishSubject.create();
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
        holder.title.setText(conversation.getParticipant().getEmail());
        holder.preview.setText(conversation.getLastMessage().getText());
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubject.onNext(conversation.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public void updateConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    public Observable<String> getPositionClicks() {
        return onClickSubject.asObservable();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.conversation_item)
        ViewGroup item;

        @BindView(R.id.conversation_title)
        TextView title;

        @BindView(R.id.conversation_preview)
        TextView preview;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
