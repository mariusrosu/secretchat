package com.example.marosu.secretchat.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Message implements Parcelable {
    private String id;

    private String conversationId;

    private String content;

    private String senderId;

    private boolean sending;

    @SerializedName("createdDtm")
    private long timestamp;

    private Message(String content, String conversationId, String senderId, boolean sending) {
        this.content = content;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.sending = sending;
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public String getSenderId() {
        return senderId;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getConversationId() {
        return conversationId;
    }

    public boolean isSending() {
        return sending;
    }

    public static class Builder {
        private String content;
        private String conversationId;
        private String senderId;
        private long timestamp;
        private boolean sending;

        public Builder setContent(String content) {
            this.content = content;
            return this;
        }

        public Builder setConversationId(String conversationId) {
            this.conversationId = conversationId;
            return this;
        }

        public Builder setSenderId(String senderId) {
            this.senderId = senderId;
            return this;
        }

        public Builder setTimestamp(long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder isSending(boolean sending) {
            this.sending = sending;
            return this;
        }

        public Message build() {
            return new Message(content, conversationId, senderId, sending);
        }
    }

    protected Message(Parcel in) {
        id = in.readString();
        conversationId = in.readString();
        content = in.readString();
        senderId = in.readString();
        sending = in.readByte() != 0x00;
        timestamp = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(conversationId);
        dest.writeString(content);
        dest.writeString(senderId);
        dest.writeByte((byte) (sending ? 0x01 : 0x00));
        dest.writeLong(timestamp);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Message> CREATOR = new Parcelable.Creator<Message>() {
        @Override
        public Message createFromParcel(Parcel in) {
            return new Message(in);
        }

        @Override
        public Message[] newArray(int size) {
            return new Message[size];
        }
    };
}