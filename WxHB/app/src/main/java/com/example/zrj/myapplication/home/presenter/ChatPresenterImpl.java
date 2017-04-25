package com.example.zrj.myapplication.home.presenter;

import android.util.Log;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.home.beans.ChatBean;
import com.example.zrj.myapplication.home.model.ChatModel;
import com.example.zrj.myapplication.home.model.ChatModelImpl;
import com.example.zrj.myapplication.home.model.OnLoadChatListListener;
import com.example.zrj.myapplication.home.view.ChatView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zrj on 2017/4/8.
 */
public class ChatPresenterImpl implements ChatPresenter, OnLoadChatListListener {

    private ChatView mChatView;
    private ChatModel mChatModel;

    public ChatPresenterImpl(ChatView chatView) {
        this.mChatView = chatView;
        this.mChatModel = new ChatModelImpl();
    }
    @Override
    public void loadChatList(final int pageIndex) {
        if(pageIndex == 0) {
            Log.i("loadChatList","loadChatList");
            mChatView.showProgess();
        }
        Log.i("加载数据", "加载数据");
        mChatModel.loadChatList(this);
    }

    @Override
    public void onSuccess(List<ChatBean> list) {
        Log.i("加载数据", "加载数据成功");
        mChatView.hideProgess();
        mChatView.addChats(list);
    }

    @Override
    public void onFailure(String msg, Exception e) {

    }
}
