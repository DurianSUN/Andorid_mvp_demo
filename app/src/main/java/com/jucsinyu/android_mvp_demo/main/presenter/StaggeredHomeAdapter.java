package com.jucsinyu.android_mvp_demo.main.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.util.LruCache;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.jucsinyu.android_mvp_demo.R;
import com.jucsinyu.android_mvp_demo.main.model.DataModel;
import com.jucsinyu.android_mvp_demo.main.model.DataModelImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class StaggeredHomeAdapter extends
        RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder> implements SetPDataPersenter, OnShowChangeListener {

    private List<String> mDatas;
    private List<String> mPicUrlDatas;
    private LayoutInflater mInflater;

    private List<Integer> mHeights;

    private DataModel picModel;

    private RequestQueue mRequestQueue;

    private ImageLoader mImageLoader;

    private ImageView imageView;

    ImageLoader.ImageListener listener01;


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    public StaggeredHomeAdapter(Context context, List<String> datas) {

        mRequestQueue = Volley.newRequestQueue(context);
        picModel = new DataModelImpl();
        final LruCache<String, Bitmap> mImageCache = new LruCache<String, Bitmap>(
                20);
        ImageLoader.ImageCache imageCache = new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String key, Bitmap value) {
                mImageCache.put(key, value);
            }

            @Override
            public Bitmap getBitmap(String key) {
                return mImageCache.get(key);
            }
        };
        mImageLoader = new ImageLoader(mRequestQueue, imageCache);

        mInflater = LayoutInflater.from(context);
        setData();
        setBackground();

        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < mDatas.size(); i++) {
            mHeights.add((int) (210 + Math.random() * 300));
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
//		LayoutParams lp = holder.tv.getLayoutParams();

        LayoutParams lp1 = holder.pic_1.getLayoutParams();
        lp1.height = mHeights.get(position);
//		holder.tv.setLayoutParams(lp);

        holder.pic_1.setLayoutParams(lp1);
//        holder.tv.setText(mDatas.get(position));

        listener01 = ImageLoader
                .getImageListener(holder.pic_1, android.R.drawable.ic_menu_rotate,
                        android.R.drawable.ic_delete);
        try {
            mImageLoader.get(mPicUrlDatas.get(position + 1), listener01);
        } catch (Exception e) {
            e.printStackTrace();
        }


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

            holder.itemView.setOnLongClickListener(new OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                    removeData(pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }


    @Override
    public void addData(int position) {
        mDatas.add(position, "Insert 1");
        mHeights.add((int) (100 + Math.random() * 200));
        notifyItemInserted(position);

    }

    @Override
    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);

    }

    @Override
    public void setData() {
        mDatas = picModel.setDateText(this);
    }

    @Override
    public void setBackground() {
//		mPicUrlDatas= picModel.setDateUrlPic();

        mPicUrlDatas = new ArrayList<String>();
        int k = 1;
        for (int i = 'A'; i < 'z'; i++) {
            int j = k % 3;
            mPicUrlDatas.add("http://7xofac.com1.z0.glb.clouddn.com/mn" + (j + 1) + ".jpg");
            k++;
        }
    }

    class MyViewHolder extends ViewHolder {


        //        TextView tv;
        ImageView pic_1;

        public MyViewHolder(View view) {
            super(view);
//            tv = (TextView) view.findViewById(R.id.id_num);
            pic_1 = (ImageView) view.findViewById(R.id.id_pic);


        }
    }
}