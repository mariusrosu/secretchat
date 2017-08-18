package com.example.marosu.secretchat.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Message implements Parcelable {
    private String id;

    private String content;

    private String senderId;

    @SerializedName("createdDtm")
    private long timestamp;

    private Message(String id, String content, String senderId, long timestamp) {
        this.id = id;
        this.content = content;
        this.senderId = senderId;
        this.timestamp = timestamp;
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

    public static class Builder {
        private String content;
        private String senderId;
        private long timestamp;

        public Builder setContent(String content) {
            this.content = content;
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

        public Message build() {
            return new Message("id_1", content, senderId, timestamp);
        }
    }

    protected Message(Parcel in) {
        id = in.readString();
        content = in.readString();
        senderId = in.readString();
        timestamp = in.readLong();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(content);
        dest.writeString(senderId);
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
