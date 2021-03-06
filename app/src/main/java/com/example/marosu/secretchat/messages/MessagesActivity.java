package com.example.marosu.secretchat.messages;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.base.BaseActivity;
import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public final class MessagesActivity extends BaseActivity<MessagesView, MessagesPresenter> implements MessagesView {
    public static final String CONVERSATION_EXTRA = "conversation_extra";

    @BindView(R.id.messages_refresh)
    SwipeRefreshLayout messagesRefresh;

    @BindView(R.id.messages_recycler)
    RecyclerView messagesList;

    @BindView(R.id.message_input)
    EditText messageInput;

    @BindView(R.id.messages_send)
    FloatingActionButton messagesSend;

    private MessagesAdapter adapter;

    @Override
    protected MessagesPresenter initPresenter() {
        return new MessagesPresenter(getApplicationContext());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_messages;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        presenter.handleExtras(getIntent().getExtras());

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        messagesList.setLayoutManager(layoutManager);

        messagesRefresh.setOnRefreshListener(() -> presenter.refresh());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setConversationTitle(String title) {
        setTitle(title);
    }

    @Override
    public void onConversationLoaded(Conversation conversation) {
        if (adapter == null) {
            adapter = new MessagesAdapter(conversation.getMessages());
            messagesList.setAdapter(adapter);
        } else {
            adapter.updateMessages(conversation.getMessages());
        }
        messagesRefresh.setRefreshing(false);
    }

    @Override
    public void onConversationFailed() {
        messagesRefresh.setRefreshing(false);
        Toast.makeText(this, R.string.conversation_refresh_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessageSent(Message message) {
        int position = adapter.addMessage(message);
        messagesList.smoothScrollToPosition(position);
    }

    @Override
    public void onMessageFailed() {
        Toast.makeText(this, R.string.message_send_fail, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        messagesRefresh.setOnRefreshListener(null);
    }

    @OnTextChanged(R.id.message_input)
    void onInputTextChanged(CharSequence text) {
        if (text.length() > 0) {
            messagesSend.show();
        } else {
            messagesSend.hide();
        }
    }

    @OnClick(R.id.messages_send)
    void onSendClick() {
        presenter.sendMessage(messageInput.getText().toString());
        messageInput.setText("");
    }
}
