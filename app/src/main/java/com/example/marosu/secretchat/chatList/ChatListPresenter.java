package com.example.marosu.secretchat.chatList;

import android.os.AsyncTask;
import android.util.Log;

import com.example.marosu.secretchat.base.BasePresenter;
import com.example.marosu.secretchat.model.User;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class ChatListPresenter extends BasePresenter<ChatListView> {

    public void getConversations(InputStream inputStream) {
        new FakeConversationsTask(getView()).execute(inputStream);
    }

    @Override
    public void onPresenterDestroy() {
        detachView();
    }

    private static class FakeConversationsTask extends AsyncTask<InputStream, Void, User> {
        private WeakReference<ChatListView> viewWeakReference;

        public FakeConversationsTask(ChatListView view) {
            this.viewWeakReference = new WeakReference<>(view);
        }

        @Override
        protected User doInBackground(InputStream... inputStreams) {
            Writer writer = new StringWriter();
            try {
                final InputStream is = inputStreams[0];

                char[] buffer = new char[1024];
                try {
                    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    int n;
                    while ((n = reader.read(buffer)) != -1) {
                        writer.write(buffer, 0, n);
                    }
                } finally {
                    is.close();
                }
            } catch (IOException e) {

            }
            Log.d("Debugging", writer.toString());
            return new Gson().fromJson(writer.toString(), User.class);
        }

        @Override
        protected void onPostExecute(User user) {
            final ChatListView view = viewWeakReference.get();
            if (view != null) {
                view.onConversationsLoaded(user.getConversations());
            }

        }
    }
}
