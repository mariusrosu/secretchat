package com.example.marosu.secretchat.search;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.marosu.secretchat.R;
import com.example.marosu.secretchat.model.entity.User;
import com.example.marosu.secretchat.util.AvatarView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Marius-Andrei Rosu on 8/22/2017.
 */
public final class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<User> users;
    private PublishSubject<User> clickSubject = PublishSubject.create();

    public SearchAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User user = users.get(position);
        holder.searchItem.setOnClickListener(view -> clickSubject.onNext(user));
        holder.searchAvatar.setUser(user);
        holder.searchName.setText(user.getFullName());
        holder.searchEmail.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void updateUsers(List<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public PublishSubject<User> getClickSubject() {
        return clickSubject;
    }

    protected static final class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.search_item)
        ViewGroup searchItem;

        @BindView(R.id.search_avatar)
        AvatarView searchAvatar;

        @BindView(R.id.search_name)
        TextView searchName;

        @BindView(R.id.search_email)
        TextView searchEmail;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
