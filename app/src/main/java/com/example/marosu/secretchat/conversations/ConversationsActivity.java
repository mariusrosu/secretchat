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
import com.example.marosu.secretchat.search.SearchActivity;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
        return R.layout.activity_conversations;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList.setLayoutManager(new LinearLayoutManager(this));
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

    @OnClick(R.id.try_again)
    void onTryAgainClick() {
        loadingSpinner.setVisibility(VISIBLE);
        chatListEmpty.setVisibility(GONE);
        chatListError.setVisibility(GONE);
        chatList.setVisibility(GONE);
        presenter.getConversations();
    }

    @OnClick(R.id.chat_list_add)
    void onAddClick() {
        final Intent searchIntent = new Intent(this, SearchActivity.class);
        startActivity(searchIntent);
    }

    //TODO: Remove this after the backend is ready.
    public String getConversationsJson() {
        String json;
        try {
            InputStream is = getResources().openRawResource(R.raw.user_data);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
        return json;
    }
}
