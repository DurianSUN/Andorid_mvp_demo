package com.jucsinyu.android_mvp_demo.Login.presenter;

/**
 * Created by jucsinsun on 2015/12/11.
 */
public interface OnLoginFinishListener {
    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess();
}
