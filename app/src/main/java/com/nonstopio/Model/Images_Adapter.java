package com.nonstopio.Model;

import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.nonstopio.R;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Images_Adapter extends RecyclerView.Adapter<Images_Adapter.PathViewHolder>
{

	private Activity mactivity;

	private List<Retro_SubPaths> _list_sub_paths;

	Images_Adapter(Activity activity, List<Retro_SubPaths> sub_paths)
	{
		this.mactivity = activity;
		this._list_sub_paths = sub_paths;
	}

	@NonNull
	@Override
	public PathViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
	{
		View theView = LayoutInflater.from(mactivity).inflate(R.layout.image_list_layout, viewGroup, false);
		return new PathViewHolder(theView);
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onBindViewHolder(@NonNull PathViewHolder holder, final int position)
	{
		Retro_SubPaths retro_subPaths = _list_sub_paths.get(position);
		Glide.with(mactivity).load(retro_subPaths.getImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
				.into(holder._iv_image);
	}

	@Override
	public int getItemCount()
	{
		return _list_sub_paths.size();
	}

	public class PathViewHolder extends RecyclerView.ViewHolder
	{
		ImageView _iv_image;

		public PathViewHolder(@NonNull View itemView)
		{
			super(itemView);
			_iv_image = itemView.findViewById(R.id.iv_image);
		}
	}
}
