package com.jucsinyu.android_mvp_demo.Login.View;

/**
 * Created by jucsinsun on 2015/12/11.
 */
public interface LoginView {

    public void setPassWordError();
    public void userNameError();
    public void showProgressBar();
    public void hideProgressBar();
    public void navigateToHome();



}