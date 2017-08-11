package com.example.marosu.secretchat.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marius-Andrei Rosu on 8/7/2017.
 */
public class Conversation implements Parcelable {
    private String id;
    private List<Message> messages;
    private List<User> participants;

    public String getId() {
        return id;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<User> getParticipants() {
        return participants;
    }

    protected Conversation(Parcel in) {
        id = in.readString();
        if (in.readByte() == 0x01) {
            messages = new ArrayList<>();
            in.readList(messages, Message.class.getClassLoader());
        } else {
            messages = null;
        }
        if (in.readByte() == 0x01) {
            participants = new ArrayList<>();
            in.readList(participants, User.class.getClassLoader());
        } else {
            participants = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        if (messages == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(messages);
        }
        if (participants == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(participants);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Conversation> CREATOR = new Parcelable.Creator<Conversation>() {
        @Override
        public Conversation createFromParcel(Parcel in) {
            return new Conversation(in);
        }

        @Override
        public Conversation[] newArray(int size) {
            return new Conversation[size];
        }
    };
}