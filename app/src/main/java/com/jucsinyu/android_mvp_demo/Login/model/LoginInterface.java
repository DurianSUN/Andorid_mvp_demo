package com.jucsinyu.android_mvp_demo.Login.model;

import com.jucsinyu.android_mvp_demo.Login.presenter.OnLoginFinishListener;

/**
 * Created by jucsinsun on 2015/12/11.
 */
public interface LoginInterface {
    public void login(String usename,String password,OnLoginFinishListener listener);
}
