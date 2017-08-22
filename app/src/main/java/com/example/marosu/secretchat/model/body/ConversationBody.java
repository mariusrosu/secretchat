package com.example.marosu.secretchat.model.body;

import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/22/2017.
 */
public class ConversationBody {
    private String userId;
    private List<String> participantIds;

    public ConversationBody(String userId, List<String> participantIds) {
        this.userId = userId;
        this.participantIds = participantIds;
    }
}
