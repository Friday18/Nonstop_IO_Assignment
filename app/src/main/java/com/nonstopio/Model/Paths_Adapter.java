package com.nonstopio.Model;

import java.util.List;

import com.nonstopio.R;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class Paths_Adapter extends RecyclerView.Adapter<Paths_Adapter.PathViewHolder>
{

	private Activity mactivity;

	private List<Retro_SubPaths> _list_sub_paths;

	private IOnItemClicked iOnItemClicked;

	public Paths_Adapter(Activity activity, List<Retro_SubPaths> sub_paths, IOnItemClicked onItemClicked)
	{
		this.mactivity = activity;
		this._list_sub_paths = sub_paths;
		this.iOnItemClicked = onItemClicked;
	}

	@NonNull
	@Override
	public PathViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
	{
		View theView = LayoutInflater.from(mactivity).inflate(R.layout.paths_list_layout, viewGroup, false);
		return new PathViewHolder(theView);
	}

	@Override
	public void onBindViewHolder(@NonNull PathViewHolder holder, final int position)
	{
		Retro_SubPaths retro_subPaths = _list_sub_paths.get(position);

		String str = retro_subPaths.getTitle();
		if (str != null)
		{
			holder._tv_path.setText(str);
			holder._tv_path.setOnClickListener(new View.OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					iOnItemClicked.onItemChanged(position);
				}
			});
			if (position == getItemCount() - 1)
			{
				holder._iv_next.setVisibility(View.GONE);
			}

		}

	}

	@Override
	public int getItemCount()
	{
		return _list_sub_paths.size();
	}

	public class PathViewHolder extends RecyclerView.ViewHolder
	{
		TextView _tv_path;

		ImageView _iv_next;

		public PathViewHolder(@NonNull View itemView)
		{
			super(itemView);
			_tv_path = itemView.findViewById(R.id.tv_path);
			_iv_next = itemView.findViewById(R.id.iv_next);
		}
	}
}
