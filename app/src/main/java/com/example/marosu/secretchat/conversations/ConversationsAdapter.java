package com.example.marosu.secretchat.conversations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;
import com.example.marosu.secretchat.model.entity.User;
import com.example.marosu.secretchat.util.AvatarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

import static com.example.marosu.secretchat.util.Util.getRelativeTime;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class ConversationsAdapter extends RecyclerView.Adapter<ConversationsAdapter.ViewHolder> {
    private List<Conversation> conversations;
    private PublishSubject<Conversation> clickSubject = PublishSubject.create();

    public ConversationsAdapter(List<Conversation> conversations) {
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
        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickSubject.onNext(conversation);
            }
        });

        final User participant = conversation.getParticipants().get(0);
        holder.avatar.setUser(participant);
        holder.title.setText(participant.getFullName());

        final Message lastMessage = conversation.getMessages().get(0);
        holder.preview.setText(lastMessage.getContent());
        holder.date.setText(getRelativeTime(lastMessage.getTimestamp()));
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public void updateConversations(List<Conversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    public PublishSubject<Conversation> getClickSubject() {
        return clickSubject;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.conversation_item)
        ViewGroup item;

        @BindView(R.id.conversation_avatar)
        AvatarView avatar;

        @BindView(R.id.conversation_title)
        TextView title;

        @BindView(R.id.conversation_preview)
        TextView preview;

        @BindView(R.id.conversation_date)
        TextView date;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
