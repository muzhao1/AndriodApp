package com.example.zrj.myapplication.welcome;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.login.widget.LoginActivity;
import com.example.zrj.myapplication.main.widget.MainActivity;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class WelcomeActivity extends AppCompatActivity {

    SharedPreferences spre=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Shimmer 闪光字
        ShimmerTextView shimmerTextView= (ShimmerTextView)findViewById(R.id.shimmer_tv);

        Shimmer shimmer = new Shimmer();
        shimmer.start(shimmerTextView);

        spre=this.getSharedPreferences("waijie", MODE_PRIVATE);

        initMethod();
    }

    /**
     * Handler 操作进行延时操作 ，延时 3s 后 ，进入 主界面
     */
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            //默认值为1 ， 第一次运行必为1，下次运行的时候，就不为1了。
            if(spre.getInt("once",1)==1){

                //修改为0,后跳转
                SharedPreferences.Editor editor=spre.edit();
                editor.putInt("once",0);
                editor.commit();

                //第一次运行
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);


            }else{
                //不是第一次运行
                startActivity(new Intent(WelcomeActivity.this,MainActivity.class));
            }


            finish();
        }
    };

    private  void initMethod(){
        //Log.i("LeDou",spre.getInt("once",0)+"");
        //延时操作
        handler.sendEmptyMessageDelayed(0, 1000);

    }
}
