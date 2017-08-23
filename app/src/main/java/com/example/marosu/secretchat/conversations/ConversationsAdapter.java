package com.example.marosu.secretchat.conversations;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.model.db.entity.Message;
import com.example.marosu.secretchat.model.db.entity.User;
import com.example.marosu.secretchat.model.pojo.FullConversation;
import com.example.marosu.secretchat.util.AvatarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

import static android.view.View.GONE;
import static com.example.marosu.secretchat.util.Util.getRelativeTime;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public final class ConversationsAdapter extends RecyclerView.Adapter<ConversationsAdapter.ViewHolder> {
    private List<FullConversation> conversations;
    private PublishSubject<String> clickSubject = PublishSubject.create();

    public ConversationsAdapter(List<FullConversation> conversations) {
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
        final FullConversation conversation = conversations.get(position);
        holder.item.setOnClickListener(view -> clickSubject.onNext(conversation.getId()));

        final User participant = conversation.getParticipants().get(0);
        holder.avatar.setUser(participant);
        holder.title.setText(participant.getFullName());

        if (conversation.getMessages() != null && conversation.getMessages().size() > 0) {
            final Message lastMessage = conversation.getMessages().get(0);
            holder.preview.setText(lastMessage.getContent());
            holder.preview.setVisibility(View.VISIBLE);
            holder.date.setText(getRelativeTime(lastMessage.getTimestamp()));
            holder.date.setVisibility(View.VISIBLE);
        } else {
            holder.preview.setVisibility(GONE);
            holder.date.setVisibility(GONE);
        }
    }

    @Override
    public int getItemCount() {
        return conversations.size();
    }

    public void updateConversations(List<FullConversation> conversations) {
        this.conversations = conversations;
        notifyDataSetChanged();
    }

    public PublishSubject<String> getClickSubject() {
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
