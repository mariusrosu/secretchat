package com.example.marosu.secretchat.model.body;

/**
 * Created by Marius-Andrei Rosu on 8/22/2017.
 */
public class MessageBody {
    private String conversationId;
    private String senderId;
    private String content;

    public MessageBody(String conversationId, String senderId, String content) {
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.content = content;
    }

    public String getConversationId() {
        return conversationId;
    }

    public String getSenderId() {
        return senderId;
    }

    public String getContent() {
        return content;
    }
}
