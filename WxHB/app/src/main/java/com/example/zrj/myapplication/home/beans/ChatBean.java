package com.example.zrj.myapplication.home.beans;

import java.io.Serializable;

/**
 * Created by zrj on 2017/4/8.
 */
public class ChatBean implements Serializable {
    private String chatTitle;
    private String chatSubTitle;
    private int chatImg;

    public String getChatTitle() {
        return chatTitle;
    }

    public void setChatTitle(String chatTitle) {
        this.chatTitle = chatTitle;
    }

    public String getChatSubTitle() {
        return chatSubTitle;
    }

    public void setChatSubTitle(String chatSubTitle) {
        this.chatSubTitle = chatSubTitle;
    }

    public int getChatImg() {
        return chatImg;
    }

    public void setChatImg(int chatImg) {
        this.chatImg = chatImg;
    }
}
