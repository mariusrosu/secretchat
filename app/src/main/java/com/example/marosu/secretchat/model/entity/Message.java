package com.example.marosu.secretchat.model.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.marosu.secretchat.model.body.MessageBody;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
@Entity(tableName = "message")
public class Message implements Parcelable {
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
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "conversation_id")
    private String conversationId;
    @ColumnInfo(name = "content")
    private String content;
    @ColumnInfo(name = "sender_id")
    private String senderId;
    @ColumnInfo(name = "sending")
    private boolean sending;
    @ColumnInfo(name = "timestamp")
    @SerializedName("createdDtm")
    private long timestamp;

    public Message() {
        //Public constructor needed for Room persistency
    }

    private Message(String content, String conversationId, String senderId, boolean sending) {
        this.content = content;
        this.conversationId = conversationId;
        this.senderId = senderId;
        this.sending = sending;
    }

    protected Message(Parcel in) {
        id = in.readString();
        conversationId = in.readString();
        content = in.readString();
        senderId = in.readString();
        sending = in.readByte() != 0x00;
        timestamp = in.readLong();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public boolean isSending() {
        return sending;
    }

    public void setSending(boolean sending) {
        this.sending = sending;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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

        public Builder setBody(MessageBody body) {
            this.conversationId = body.getConversationId();
            this.senderId = body.getSenderId();
            this.content = body.getContent();
            return this;
        }

        public Message build() {
            return new Message(content, conversationId, senderId, sending);
        }
    }
}