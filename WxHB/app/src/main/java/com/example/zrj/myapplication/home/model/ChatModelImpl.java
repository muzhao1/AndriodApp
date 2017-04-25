package com.example.zrj.myapplication.home.model;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.home.beans.ChatBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zrj on 2017/4/10.
 */
public class ChatModelImpl implements ChatModel {
    @Override
    public void loadChatList(OnLoadChatListListener onLoadChatListListener) {
        List<ChatBean> list = new ArrayList<>();
        for (int i=0; i < 20 ; i++){
            ChatBean chatBean = new ChatBean();
            chatBean.setChatTitle("陌生人"+i);
            chatBean.setChatSubTitle("朋友:我在回家的路上的");
            chatBean.setChatImg(R.drawable.chat_img);
            list.add(chatBean);
        }
        onLoadChatListListener.onSuccess(list);
    }
}
