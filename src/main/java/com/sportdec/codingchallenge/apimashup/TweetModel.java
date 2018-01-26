package com.sportdec.codingchallenge.apimashup;

import java.util.Date;

public class TweetModel {

    private String text;
    private Date createdAt;
    private String fromUser;

    public TweetModel(String text, Date createdAt, String fromUser) {
        this.text = text;
        this.createdAt = createdAt;
        this.fromUser = fromUser;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }
}
