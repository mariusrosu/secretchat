package com.example.marosu.secretchat.util.comparator;

import com.example.marosu.secretchat.model.entity.Conversation;
import com.example.marosu.secretchat.model.entity.Message;

import java.util.Comparator;

/**
 * Created by Marius-Andrei Rosu on 8/22/2017.
 */
public class ConversationComparator implements Comparator<Conversation> {
    @Override
    public int compare(Conversation rhs, Conversation lhs) {
        Message rhsMessage = null;
        if (rhs.getMessages() != null && rhs.getMessages().size() > 0) {
            rhsMessage = rhs.getMessages().get(0);
        }

        Message lhsMessage = null;
        if (lhs.getMessages() != null && lhs.getMessages().size() > 0) {
            lhsMessage = lhs.getMessages().get(0);
        }

        if (rhsMessage == null && lhsMessage == null) {
            return (int) (rhs.getTimestamp() - lhs.getTimestamp());
        } else if (rhsMessage != null && lhsMessage == null) {
            return (int) (rhsMessage.getTimestamp() - lhs.getTimestamp());
        } else if (rhsMessage == null && lhsMessage != null) {
            return (int) (rhs.getTimestamp() - lhsMessage.getTimestamp());
        } else {
            return (int) (rhsMessage.getTimestamp() - lhsMessage.getTimestamp());
        }
    }
}
