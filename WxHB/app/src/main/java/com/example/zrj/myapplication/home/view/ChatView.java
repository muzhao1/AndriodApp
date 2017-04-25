package com.example.zrj.myapplication.home.view;

import com.example.zrj.myapplication.home.beans.ChatBean;

import java.util.List;

/**
 * Created by zrj on 2017/4/8.
 */
public interface ChatView {

    void addChats(List<ChatBean> newsList);

    void showProgess();

    void hideProgess();
}
