package com.example.zrj.myapplication.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.home.beans.ChatBean;

import java.util.List;

/**
 * Created by zrj on 2017/4/8.
 */
public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ChatBean> mData;

    private Context mContext;

    private OnItemClickListener mOnItemClickListener;

    private OnItemLongClickListener mOnItemLongClickListener;

    private boolean mShowFooter = true;

    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;


    public ChatAdapter(Context context) {
        this.mContext = context;
    }

    public void setDate(List<ChatBean> data) {
        this.mData = data;
        this.notifyDataSetChanged();
        Log.i("notifyDataSetChanged", "通知数据成功："+mData.size());
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.mOnItemLongClickListener = onItemLongClickListener;
    }

    @Override
    public int getItemViewType(int position) {
        // 最后一个item设置为footerView
        if(!mShowFooter) {
            return TYPE_ITEM;
        }
        if (position + 1 == getItemCount()) {
            return TYPE_FOOTER;
        } else {
            return TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if(viewType == TYPE_ITEM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item,parent,false);
            ItemViewHolder vh = new ItemViewHolder(view);
            return vh;
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.loading_footer, null);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new FooterViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder) {
            ChatBean chatBean = mData.get(position);
            if(chatBean == null) {
                return;
            }
            ((ItemViewHolder) holder).mChatTitle.setText(chatBean.getChatTitle());
            ((ItemViewHolder) holder).mChatSubTitle.setText(chatBean.getChatSubTitle());
            ((ItemViewHolder) holder).mChatImg.setImageResource(chatBean.getChatImg());
        }
    }

    @Override
    public int getItemCount() {
        int begin = mShowFooter?1:0;
        if(mData == null) {
            return begin;
        }
        Log.i("getItemCount","getItemCount:"+mData.size() + begin);
        return mData.size() + begin;
    }

    public void isShowFooter(boolean showFooter) {
        this.mShowFooter = showFooter;
    }

    public boolean isShowFooter() {
        return this.mShowFooter;
    }

    public ChatBean getItem(int position) {
        return mData == null ? null : mData.get(position);
    }


    public interface OnItemClickListener {
         void onItemClick(View view, int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }


    public class FooterViewHolder extends RecyclerView.ViewHolder {

        public FooterViewHolder(View view) {
            super(view);
        }

    }

    public class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {

        public TextView mChatTitle;
        public TextView mChatSubTitle;
        public ImageView mChatImg;

        public ItemViewHolder(View v) {
            super(v);
            mChatTitle = (TextView) v.findViewById(R.id.tv_chat_title);
            mChatSubTitle = (TextView) v.findViewById(R.id.tv_chat_sub_title);
            mChatImg = (ImageView) v.findViewById(R.id.iv_chat_img);
            v.setOnClickListener(this);
            v.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(view, this.getPosition());
            }
        }


        @Override
        public boolean onLongClick(View v) {
            if(mOnItemLongClickListener != null) {
                mOnItemLongClickListener.onItemLongClick(v, this.getPosition());
            }
            return true;
        }
    }
}
