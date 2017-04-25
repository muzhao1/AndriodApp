package com.example.zrj.myapplication.login.view;

/**
 * Created by zrj on 2017/4/7.
 */
public interface LoginView {
    String getLoginUsername();
    String getLoginPassword();
    void shakeUsername();
    void shakePassword();
    void loginSuccess(String message);
    void loginFailed(String message);
}
