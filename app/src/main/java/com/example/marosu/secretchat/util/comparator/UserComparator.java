package com.example.marosu.secretchat.util.comparator;

import com.example.marosu.secretchat.Session;
import com.example.marosu.secretchat.model.entity.User;

import java.util.Comparator;

/**
 * Created by Marius-Andrei Rosu on 8/22/2017.
 */
public class UserComparator implements Comparator<User> {
    private final String currentUserId = Session.getSession().getUserId();

    @Override
    public int compare(User rhs, User lhs) {
        if (currentUserId.equals(rhs.getId())) {
            return 1;
        } else if (currentUserId.equals(lhs.getId())) {
            return -1;
        }
        return 0;
    }
}
