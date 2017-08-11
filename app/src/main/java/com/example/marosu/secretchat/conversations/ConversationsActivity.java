package com.example.marosu.secretchat.conversations;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.messages.MessagesActivity;
import com.example.marosu.secretchat.model.entity.Conversation;

import java.util.List;

import butterknife.BindView;
import io.reactivex.functions.Consumer;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class ConversationsActivity extends BaseActivity<ConversationsView, ConversationsPresenter>
        implements ConversationsView {
    @BindView(R.id.chat_list_loading)
    ProgressBar loadingSpinner;

    @BindView(R.id.chat_list_empty)
    ViewGroup chatListEmpty;

    @BindView(R.id.chat_list_error)
    ViewGroup chatListError;

    @BindView(R.id.chat_list_recycler)
    RecyclerView chatList;

    private ConversationsAdapter adapter;

    @Override
    protected ConversationsPresenter initPresenter() {
        return new ConversationsPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList.setLayoutManager(new LinearLayoutManager(this));
        chatList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        chatList.setHasFixedSize(true);
        presenter.getConversations();
    }

    @Override
    public void onConversationsLoaded(List<Conversation> conversations) {
        loadingSpinner.setVisibility(GONE);
        chatListEmpty.setVisibility(GONE);
        chatListError.setVisibility(GONE);
        chatList.setVisibility(VISIBLE);
        if (adapter == null) {
            initConversationsAdapter(conversations);
            chatList.setAdapter(adapter);
        } else {
            adapter.updateConversations(conversations);
        }
    }

    @Override
    public void onConversationsEmpty() {
        loadingSpinner.setVisibility(GONE);
        chatListEmpty.setVisibility(VISIBLE);
        chatListError.setVisibility(GONE);
        chatList.setVisibility(GONE);
    }

    @Override
    public void onConversationsFailed() {
        loadingSpinner.setVisibility(GONE);
        chatListEmpty.setVisibility(GONE);
        chatListError.setVisibility(VISIBLE);
        chatList.setVisibility(GONE);
    }

    private void initConversationsAdapter(List<Conversation> conversations) {
        adapter = new ConversationsAdapter(conversations);
        adapter.getClickSubject().subscribe(new Consumer<Conversation>() {
            @Override
            public void accept(Conversation conversation) throws Exception {
                final Intent messagesIntent =
                        new Intent(ConversationsActivity.this, MessagesActivity.class);
                messagesIntent.putExtra(MessagesActivity.CONVERSATION_EXTRA, conversation);
                startActivity(messagesIntent);
            }
        });
    }
}
