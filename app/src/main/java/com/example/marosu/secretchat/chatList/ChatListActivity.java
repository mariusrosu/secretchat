package com.example.marosu.secretchat.chatList;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.chatDetails.ChatDetailsActivity;
import com.example.marosu.secretchat.model.Conversation;

import java.util.List;

import butterknife.BindView;
import rx.functions.Action1;

public class ChatListActivity extends BaseActivity<ChatListView, ChatListPresenter> implements ChatListView {
    @BindView(R.id.chat_list_loading)
    ProgressBar loadingSpinner;

    @BindView(R.id.chat_list_recycler)
    RecyclerView chatList;

    private ChatListAdapter adapter;

    @Override
    protected ChatListPresenter initPresenter() {
        return new ChatListPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat_list;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        chatList.setLayoutManager(new LinearLayoutManager(this));
        presenter.getConversations(getResources().openRawResource(R.raw.user_data));
    }

    @Override
    public void onConversationsLoaded(List<Conversation> conversations) {
        loadingSpinner.setVisibility(View.GONE);
        chatList.setVisibility(View.VISIBLE);
        if (adapter == null) {
            adapter = new ChatListAdapter(conversations);
            adapter.getPositionClicks().subscribe(new OnConversationClickSubscriber());
            chatList.setAdapter(adapter);
        } else {
            adapter.updateConversations(conversations);
        }
    }

    private class OnConversationClickSubscriber implements Action1<String> {

        @Override
        public void call(String conversationId) {
            startActivity(new Intent(ChatListActivity.this, ChatDetailsActivity.class));
        }
    }
}
