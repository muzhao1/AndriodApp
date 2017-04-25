package com.example.zrj.myapplication.home.widget;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.home.beans.ChatBean;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_news_detail3);

        ChatBean mNews = (ChatBean) getIntent().getSerializableExtra("news");
        Toolbar toolbar = (Toolbar) findViewById(R.id.new_detail_toolbar);
        setSupportActionBar(toolbar);
        //显示菜单栏返回键
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(mNews.getChatTitle());
        TextView textView = (TextView)findViewById(R.id.tv_new_body);
        textView.setText(Html.fromHtml("<p>【大纪元2017年04月12日讯】深圳一名落马的区教育局局长牵出多名贪腐的中小学校长。日前两名校长的贪腐案被移送司法。</p>\n" +
                "<p>4月11日，深圳市检察院通报，深圳市罗湖区翠北实验小学原校长黄某、罗湖区莲南小学原校长伍某杨涉嫌受贿罪、行贿罪一案侦查终结，被分别移送司法机关审查起诉。这两人去年被立案侦查。</p>\n" +
                "<p>而这两个校长都是由一名落马的区教育局局长牵出的。</p>\n" +
                "<p>2016年6月，罗湖区教育局局长王琦被调查，此后即传出罗湖辖区内的中小学校长多人涉案，均涉嫌向王琦贿送礼金。目前王琦受贿案尚未开庭。</p>\n" +
                "<p>官方通报显示，2012年至2016年，王琦在节假日期间先后9次收受辖区内的中小学校校长的礼金人民币4.9万元、购物卡人民币2.2万元、手表一块。同时，王琦还利用职务上的便利为他人谋取利益，收受贿赂。</p>\n" +
                "<p>王琦被查之后，罗湖多名中小学校长被带走调查。</p>\n" +
                "<p>大陆教育体制的腐败已经尽人皆知，中小学教育领域的腐败更关系到老百姓的切身利益。</p>\n" +
                "<p>陆媒2014年底曾盘点，从2013年7月以来，深圳先后有7名中小学校长因受贿落马。</p>\n" +
                "<p>中小学校领导的腐败空间包括：招生收受好处费；采购招投标中收受回扣；基建工程收受好处；食堂购菜套取伙食费，报销造假骗取公款。还有一些中小学校长在决定公务车定点维修、教师入编等环节中，利用职权收受好处。</p>\n" +
                "<p>如落马的龙岗区实验学校校长果某，涉嫌帮助多名不符合入学条件的学生入读该校并收受了数十万元的财物；罗湖某学校校长邓某，违规办理了13名学生的入读手续，并收取了社会中介的好处费……</p>\n" +
                "<p>中国大陆<a href=\"http://www.epochtimes.com/gb/tag/%E6%95%99%E8%82%B2%E8%85%90%E8%B4%A5.html\">教育腐败</a>猖獗之时当属江泽民的情妇陈至立掌控教育系统时期，留下的遗祸后患无穷。</p>\n" +
                "<p>多年前大陆媒体曾自曝，中小学教育成为中国仅次于房地产的第二大暴利行业，已危及超过3亿儿童和他们的家庭利益，更事关国家民族的未来发展，因此引发社会各界的普遍关注。#</p>\n" +
                "<p>责任编辑：方明</p>"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case android.R.id.home:
                Log.i("android.R.id.home:", ""+item.getItemId());
//                Intent intent = new Intent(this, MainActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


}
