package com.example.zrj.myapplication.home.model;

import com.example.zrj.myapplication.home.beans.ChatBean;

import java.util.List;

/**
 * Created by zrj on 2017/4/10.
 */
public interface OnLoadChatListListener {

    void onSuccess(List<ChatBean> list);

    void onFailure(String msg, Exception e);
}
