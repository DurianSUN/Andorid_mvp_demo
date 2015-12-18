package com.jucsinyu.android_mvp_demo.main.model;

import android.widget.TextView;

import com.jucsinyu.android_mvp_demo.main.presenter.OnShowChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jucsinsun on 2015/12/18.
 */
public class DataModelImpl implements DataModel {
    List<String> mDatas;

    @Override
    public void setBackground(TextView v) {
    }
    @Override
    public List<String>  setDateText( OnShowChangeListener changeListener) {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        return  mDatas;
    }
}
