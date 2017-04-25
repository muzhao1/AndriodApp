package com.example.zrj.myapplication.login.presenter;

import android.text.TextUtils;

import com.example.zrj.myapplication.login.view.LoginView;

/**
 * Created by zrj on 2017/4/7.
 */
public class LoginPresenterImpl implements LoginPresenter {
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView){
        this.loginView = loginView;
    }

    @Override
    public void login() {
        String userName = loginView.getLoginUsername();
        String password = loginView.getLoginPassword();
        if (TextUtils.isEmpty(userName)){
            loginView.shakeUsername();
            //loginView.loginFailed("请输入用户名");
            return;
        }
        if (TextUtils.isEmpty(password)){
            loginView.shakePassword();
            //loginView.loginFailed("请输入密码");
            return;
        }
        String message = "用户名:"+userName+",密码:"+password;
        //调用model判断用户名密码
        loginView.loginSuccess(message);

    }
}
