package com.jucsinyu.android_mvp_demo.Login.View;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.jucsinyu.android_mvp_demo.Login.presenter.LoginPresenter;
import com.jucsinyu.android_mvp_demo.Login.presenter.LoginPresenterImpl;
import com.jucsinyu.android_mvp_demo.R;
import com.jucsinyu.android_mvp_demo.main.view.MainActivity;

public class LoginActivity extends Activity implements LoginView, View.OnClickListener {

    private EditText usename_et;
    private EditText password_et;
    private ProgressBar login_pb;
    private LoginPresenter prestenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    public void init() {
        usename_et = (EditText) findViewById(R.id.usename_et);
        password_et = (EditText) findViewById(R.id.password_et);
        findViewById(R.id.login_bt).setOnClickListener(this);
        login_pb = (ProgressBar) findViewById(R.id.login_pb);
        prestenter = new LoginPresenterImpl(this);
    }

    @Override
    public void setPassWordError() {
        password_et.setError("用户名错误");
    }

    @Override
    public void userNameError() {
        usename_et.setError("用户名错误");
    }

    @Override
    public void showProgressBar() {
        login_pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        login_pb.setVisibility(View.GONE);
    }

    @Override
    public void navigateToHome() {
        Intent i = new Intent();
        i.setClass(this, MainActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View v) {
        prestenter.validateCredentials(usename_et.getText().toString(),
                password_et.getText().toString());
    }
}
