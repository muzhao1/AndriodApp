package com.example.zrj.myapplication.home.widget;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.driver.RecycleViewDivider;
import com.example.zrj.myapplication.home.adapter.ChatAdapter;
import com.example.zrj.myapplication.home.beans.ChatBean;
import com.example.zrj.myapplication.home.presenter.ChatPresenter;
import com.example.zrj.myapplication.home.presenter.ChatPresenterImpl;
import com.example.zrj.myapplication.home.view.ChatView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by donglinghao on 2016-01-28.
 */
public class HomeFragment extends Fragment implements ChatView, SwipeRefreshLayout.OnRefreshListener {

    private View mRootView;

    private ChatAdapter mAdapter;

    private RecyclerView mRecyclerView;

    private LinearLayoutManager mLayoutManager;

    private ChatPresenter mChatPresenter;

    private SwipeRefreshLayout mSwipeRefreshWidget;

    private List<ChatBean> mData;

    private int pageIndex = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mChatPresenter = new ChatPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.e("初始化","初始化");
        if (mRootView == null){
            Log.e("666","HomeFragment");
            mRootView = inflater.inflate(R.layout.home_fragment,container,false);
        }
        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null){
            parent.removeView(mRootView);
        }

        mSwipeRefreshWidget = (SwipeRefreshLayout) mRootView.findViewById(R.id.swipe_refresh_widget);
        mSwipeRefreshWidget.setColorSchemeResources(R.color.primary, R.color.primary_dark, R.color.primary_light, R.color.accent);
        mSwipeRefreshWidget.setOnRefreshListener(this);

        mRecyclerView = (RecyclerView)mRootView.findViewById(R.id.rv_chat_recycle_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);
        //分隔线
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getActivity(), RecycleViewDivider.VERTICAL_LIST, R.drawable.divider));
        mAdapter = new ChatAdapter(getActivity().getApplicationContext());
        mAdapter.setOnItemClickListener(mOnItemClickListener);
        mAdapter.setOnItemLongClickListener(mOnItemLongClickListener);
        mRecyclerView.setAdapter(mAdapter);
        //上拉加载更多
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        onRefresh();
        return mRootView;
    }

    //滚动事件
    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {

        private int lastVisibleItem;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            lastVisibleItem = mLayoutManager.findLastVisibleItemPosition();

        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if (newState == RecyclerView.SCROLL_STATE_IDLE
                    && lastVisibleItem + 1 == mAdapter.getItemCount()
                    && mAdapter.isShowFooter()) {
                //加载更多
                Log.i("onScrollStateChanged", "load more");
                mChatPresenter.loadChatList(pageIndex + 20);
            }
            //Toast.makeText(getActivity(), "onScrollStateChanged:"+newState, Toast.LENGTH_SHORT).show();
        }
    };

    private ChatAdapter.OnItemClickListener mOnItemClickListener = new ChatAdapter.OnItemClickListener() {
        @Override
        public void onItemClick(View view, int position) {
            ChatBean news = mAdapter.getItem(position);
            String message = news.getChatTitle();
            Toast.makeText(getActivity(), "点击事件："+message, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), NewsDetailActivity.class);
            intent.putExtra("news", news);
            ActivityCompat.startActivity(getActivity(), intent, null);

        }

    };

    private ChatAdapter.OnItemLongClickListener mOnItemLongClickListener = new ChatAdapter.OnItemLongClickListener() {
        @Override
        public void onItemLongClick(View view, int position) {
            ChatBean news = mAdapter.getItem(position);
            String message = news.getChatTitle();
            Toast.makeText(getActivity(), "长点击事件："+message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(getActivity())
                    .setTitle("标题")
                    .setMessage("简单的消息提示框")
                    .setPositiveButton("确定", null)
                    .show();
        }

    };

    @Override
    public void addChats(List<ChatBean> newsList) {
       // mAdapter.isShowFooter(true);
        if(mData == null) {
            mData = new ArrayList<ChatBean>();
        }
        mData.addAll(newsList);
        //mAdapter.setDate(mData);
        if(pageIndex == 0) {
            mAdapter.setDate(mData);
        } else {
            //如果没有更多数据了,则隐藏footer布局
            if(newsList == null || newsList.size() == 0) {
                mAdapter.isShowFooter(false);
            }
            mAdapter.notifyDataSetChanged();
        }
        pageIndex += 20;
    }

    @Override
    public void showProgess() {
        mSwipeRefreshWidget.setRefreshing(true);
    }

    @Override
    public void hideProgess() {
        mSwipeRefreshWidget.setRefreshing(false);
    }


    @Override
    public void onRefresh() {
        //上拉刷新调用这个函数
        Log.i("上拉刷新", "onRefresh");
        pageIndex = 0;
        if(mData != null) {
            Log.i("上拉刷新", "清除数据");
            mData.clear();
        }
        mChatPresenter.loadChatList(pageIndex);
    }
}
