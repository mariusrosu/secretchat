package com.example.marosu.secretchat.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.model.entity.Conversation;

import butterknife.BindView;

public class MessagesActivity extends BaseActivity<MessagesView, MessagesPresenter> implements MessagesView {
    public static final String CONVERSATION_EXTRA = "conversation_extra";

    @BindView(R.id.messages_recycler)
    RecyclerView messagesList;

    private MessagesAdapter adapter;

    @Override
    protected MessagesPresenter initPresenter() {
        return new MessagesPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_details;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle extra = getIntent().getExtras();
        if (extra != null) {
            final Conversation conversation = extra.getParcelable(CONVERSATION_EXTRA);
            onConversationLoaded(conversation);
        }
        messagesList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onConversationLoaded(Conversation conversation) {
        if (adapter == null) {
            adapter = new MessagesAdapter(conversation.getMessages());
            messagesList.setAdapter(adapter);
        } else {
            adapter.updateMessages(conversation.getMessages());
        }
    }
}
