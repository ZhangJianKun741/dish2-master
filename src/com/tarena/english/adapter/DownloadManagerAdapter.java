package com.tarena.english.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tarena.english.R;
import com.tarena.english.model.DownloadEntity;
import com.tarena.english.view.ReadActivity;

public class DownloadManagerAdapter extends BaseAdapter{
Context context;
	ArrayList<DownloadEntity> list;
	
	public DownloadManagerAdapter(Context context,
			ArrayList<DownloadEntity> list) {
		super();
		this.context = context;
		if (list==null)
		{
			
		}else
		{
		this.list = list;
		}
	}
	
	public void updateData(ArrayList<DownloadEntity> list)
	{
		this.list=list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder=null;
		if (convertView==null)
		{
			viewHolder=new ViewHolder();
			convertView=View.inflate(context, R.layout.download_manager_item, null);
			viewHolder.ivState=(ImageView) convertView.findViewById(R.id.iv_download_manager_item);
			viewHolder.tvBookName=(TextView) convertView.findViewById(R.id.tv_download_manager_item_bookName);
			viewHolder.tvProgress=(TextView) convertView.findViewById(R.id.tv_download_manager_item_progress);
			viewHolder.progressBar=(ProgressBar) convertView.findViewById(R.id.progressBar_download_manager_item);
			convertView.setTag(viewHolder);
		}else
		{
			viewHolder=(ViewHolder) convertView.getTag();
		}
		
		final DownloadEntity entity=list.get(position);
		
		viewHolder.tvBookName.setText(entity.getBookName());
		if (entity.getCurrentDownloadIndex()>=entity.getLessonTotal())
		{
			viewHolder.progressBar.setVisibility(View.GONE);
			viewHolder.ivState.setImageResource(R.drawable.download_finish);
			viewHolder.tvProgress.setText("已下完，单击阅读");
			viewHolder.tvProgress.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					try {
						Intent intent=new Intent(context,ReadActivity.class);
						intent.putExtra(DownloadEntity.class.getName(), entity);
						context.startActivity(intent);
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
			});
		}else
		{
		viewHolder.tvProgress.setText(entity.getCurrentDownloadIndex()+"/"+entity.getLessonTotal());
		
		int progress=entity.getCurrentDownloadIndex()*100/entity.getLessonTotal();
		viewHolder.progressBar.setProgress(progress);
		}
		
		return convertView;
	}
	class ViewHolder
	{
		ImageView ivState;
		TextView tvBookName,tvProgress;
		ProgressBar progressBar;
	}

}
