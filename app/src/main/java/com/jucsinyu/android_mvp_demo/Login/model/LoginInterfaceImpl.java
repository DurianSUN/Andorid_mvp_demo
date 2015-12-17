package com.jucsinyu.android_mvp_demo.Login.model;

import android.os.Handler;
import android.text.TextUtils;

import com.jucsinyu.android_mvp_demo.Login.presenter.OnLoginFinishListener;

/**
 * Created by jucsinsun on 2015/12/11.
 */
public class LoginInterfaceImpl implements LoginInterface {


    @Override
    public void login(final String username, final String password, final OnLoginFinishListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean error = false;
                if (TextUtils.isEmpty(username) || !username.equals("123")) {
                    listener.onUsernameError();
                    error = true;
                }
                if (TextUtils.isEmpty(password) || !password.equals("123")) {
                    listener.onPasswordError();
                    error = true;
                }
                if (!error && (username.equals("123")) && (password.equals("123"))) {
                    listener.onSuccess();
                }
            }
        }, 2000);
    }
}
