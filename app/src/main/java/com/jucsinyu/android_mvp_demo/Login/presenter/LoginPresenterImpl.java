package com.jucsinyu.android_mvp_demo.Login.presenter;

import com.jucsinyu.android_mvp_demo.Login.View.LoginView;
import com.jucsinyu.android_mvp_demo.Login.model.LoginInterface;
import com.jucsinyu.android_mvp_demo.Login.model.LoginInterfaceImpl;

/**
 * Created by jucsinsun on 2015/12/11.
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishListener {


    LoginView loginView;
    LoginInterface loginInterface;


    public LoginPresenterImpl(LoginView loginView) {
        //实例化为实现了方法的登录构造器，向下转型。
        this.loginView = loginView;
        this.loginInterface = new LoginInterfaceImpl();
    }


    @Override
    public void onPasswordError() {
        loginView.setPassWordError();
        loginView.hideProgressBar();

    }

    @Override
    public void onSuccess() {
        loginView.hideProgressBar();
        loginView.navigateToHome();
    }

    @Override
    public void onUsernameError() {
        loginView.userNameError();
        loginView.hideProgressBar();
    }


    @Override
    public void validateCredentials(String usename, String passWord) {

        loginView.showProgressBar();
        loginInterface.login(usename,passWord,this);

    }
}
