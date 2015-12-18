package com.jucsinyu.android_mvp_demo.main.model;

import com.jucsinyu.android_mvp_demo.main.presenter.OnShowChangeListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jucsinsun on 2015/12/18.
 */
public class DataModelImpl implements DataModel {
    List<String> mDatas;
    List<String> mPicUrlDatas;

    @Override
    public List<String> setDateUrlPic() {
        mPicUrlDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            int k = 1;
            int j = k % 3;
            mPicUrlDatas.add("http://7xofac.com1.z0.glb.clouddn.com/mn" + j + "jpg");
            k++;
        }

        return mPicUrlDatas;
    }

    @Override
    public List<String> setDateText(OnShowChangeListener changeListener) {
        mDatas = new ArrayList<String>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add("" + (char) i);
        }
        return mDatas;
    }


}
