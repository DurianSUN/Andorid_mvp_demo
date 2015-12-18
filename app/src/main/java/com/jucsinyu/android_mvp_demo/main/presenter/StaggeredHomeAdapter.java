package com.jucsinyu.android_mvp_demo.main.presenter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TextView;

import com.jucsinyu.android_mvp_demo.R;
import com.jucsinyu.android_mvp_demo.main.model.DataModel;
import com.jucsinyu.android_mvp_demo.main.model.DataModelImpl;

import java.util.ArrayList;
import java.util.List;

public class StaggeredHomeAdapter extends
		RecyclerView.Adapter<StaggeredHomeAdapter.MyViewHolder >implements SetPDataPersenter,OnShowChangeListener
{

	private List<String> mDatas;
	private LayoutInflater mInflater;

	private List<Integer> mHeights;

	private DataModel picModel;

	public interface OnItemClickLitener
	{
		void onItemClick(View view, int position);

		void onItemLongClick(View view, int position);
	}

	private OnItemClickLitener mOnItemClickLitener;

	public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
	{
		this.mOnItemClickLitener = mOnItemClickLitener;
	}

	public StaggeredHomeAdapter(Context context, List<String> datas)
	{
		picModel = new DataModelImpl();
		mInflater = LayoutInflater.from(context);
		mDatas = datas;

		mHeights = new ArrayList<Integer>();
		for (int i = 0; i < mDatas.size(); i++)
		{
			mHeights.add( (int) (100 + Math.random() * 300));
		}
	}

	@Override
	public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		MyViewHolder holder = new MyViewHolder(mInflater.inflate(
				R.layout.layout, parent, false));
		return holder;
	}

	@Override
	public void onBindViewHolder(final MyViewHolder holder, final int position)
	{
		LayoutParams lp = holder.tv.getLayoutParams();
		lp.height = mHeights.get(position);
		
		holder.tv.setLayoutParams(lp);
		holder.tv.setText(mDatas.get(position));

		// 如果设置了回调，则设置点击事件
		if (mOnItemClickLitener != null)
		{
			holder.itemView.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemClick(holder.itemView, pos);
				}
			});

			holder.itemView.setOnLongClickListener(new OnLongClickListener()
			{
				@Override
				public boolean onLongClick(View v)
				{
					int pos = holder.getLayoutPosition();
					mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
					removeData(pos);
					return false;
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
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
	public void SetData() {
		mDatas = picModel.setDateText(this);
	}

	@Override
	public void background(TextView view) {
		view.setBackgroundResource(R.mipmap.ic_launcher);
	}

	class MyViewHolder extends ViewHolder
	{
		public TextView getTv() {
			return tv;
		}
		TextView tv;
		public MyViewHolder(View view)
		{
			super(view);
			tv = (TextView) view.findViewById(R.id.id_num);
			background(tv);

		}
	}
}