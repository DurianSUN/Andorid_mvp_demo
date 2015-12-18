package com.jucsinyu.android_mvp_demo.main.model;

import android.widget.TextView;

import com.jucsinyu.android_mvp_demo.main.presenter.OnShowChangeListener;

import java.util.List;

/**
 * Created by jucsinsun on 2015/12/18.
 */
public interface DataModel {
    public void setBackground(TextView v);
    public  List<String>  setDateText (OnShowChangeListener changeListener);
}
