package com.example.zrj.myapplication.login.widget;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.zrj.myapplication.R;
import com.example.zrj.myapplication.custom.EditTextWithDeleteButton;
import com.example.zrj.myapplication.login.presenter.LoginPresenter;
import com.example.zrj.myapplication.login.presenter.LoginPresenterImpl;
import com.example.zrj.myapplication.login.view.LoginView;
import com.example.zrj.myapplication.main.widget.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView{
    private EditTextWithDeleteButton userNameEditView;
    private EditTextWithDeleteButton passwordEditView;
    private Button loginButton;
    private Button passwdEyeButton;
    private Button clearButton;
    private boolean flag = false;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();
    }

    private void init(){
        loginPresenter = new LoginPresenterImpl(this);
        userNameEditView = (EditTextWithDeleteButton)findViewById(R.id.et_username);
        passwordEditView = (EditTextWithDeleteButton)findViewById(R.id.et_password);
        loginButton = (Button)findViewById(R.id.btn_login);
        passwdEyeButton = (Button)findViewById(R.id.btn_pwd_eye);
        loginButton.setOnClickListener(new LoginBtnOnClickListener());
        passwdEyeButton.setOnClickListener(new PwdEyeBtnOnClickListener());
    }

    /**
     * 登录按钮的点击事件
     */
    private class LoginBtnOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            loginPresenter.login();
        }
    }

    /**
     * 登录按钮的点击事件
     */
    private class PwdEyeBtnOnClickListener implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            if(!flag){
                passwordEditView.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }else{
                passwordEditView.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            }
            // 设置光标位置到行尾
            passwordEditView.setSelection(passwordEditView.getText().length());
            flag = !flag;
        }
    }


    @Override
    public String getLoginUsername() {
        return userNameEditView.getText().toString();
    }

    @Override
    public String getLoginPassword() {
        return passwordEditView.getText().toString();
    }

    @Override
    public void shakeUsername() {
        userNameEditView.setShakeAnimation();
    }

    @Override
    public void shakePassword() {
        passwordEditView.setShakeAnimation();
    }

    @Override
    public void loginSuccess(String message) {
        Toast.makeText(getApplicationContext(), "登录成功:"+message, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void loginFailed(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
