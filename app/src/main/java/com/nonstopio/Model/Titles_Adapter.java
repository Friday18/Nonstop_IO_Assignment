package com.nonstopio.Model;

import java.util.List;

import com.nonstopio.R;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Titles_Adapter extends RecyclerView.Adapter<Titles_Adapter.TitlesViewHolder>
{

	Activity mactivity;

	List<Retro_TitleList> _list_titles;

	private Images_Adapter images_adapter;

	private Paths_Adapter paths_adapter;

	LinearLayoutManager layoutManager, layoutManager1;

	private int scrollPosition = -1, scrollPosition_paths = 2;

	private IOnItemClicked iOnItemClicked;

	public Titles_Adapter(Activity activity, List<Retro_TitleList> list_titles)
	{
		this.mactivity = activity;
		this._list_titles = list_titles;
	}

	@NonNull
	@Override
	public TitlesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
	{
		View theView = LayoutInflater.from(mactivity).inflate(R.layout.subpaths_list_layout, viewGroup, false);
		return new TitlesViewHolder(theView);
	}

	@RequiresApi(api = Build.VERSION_CODES.M)
	@Override
	public void onBindViewHolder(@NonNull final Titles_Adapter.TitlesViewHolder holder, final int position)
	{

		Retro_TitleList list = _list_titles.get(position);
		holder._tv_titles.setText(list.getTitle());

		if (list.getSub_paths() != null && !list.getSub_paths().isEmpty())
		{
			//set up main recycler view for images
			layoutManager = new LinearLayoutManager(mactivity, LinearLayoutManager.HORIZONTAL, false);
			holder._rv_images.setLayoutManager(layoutManager);
			holder._rv_images.setHasFixedSize(true);
			images_adapter = new Images_Adapter(mactivity, list.getSub_paths());
			holder._rv_images.setAdapter(images_adapter);
			images_adapter.notifyDataSetChanged();

			layoutManager1 = new LinearLayoutManager(mactivity, LinearLayoutManager.HORIZONTAL, false);
			//set up main recycler view for paths
			holder._rv_paths.setLayoutManager(layoutManager1);
			holder._rv_paths.setHasFixedSize(true);
			paths_adapter = new Paths_Adapter(mactivity, list.getSub_paths(), iOnItemClicked = new IOnItemClicked()
			{
				@Override
				public void onItemChanged(int pos)
				{
					Log.d("Log---> ", "pos on click:" + pos);
					holder._rv_images.smoothScrollToPosition(pos);
				}
			});
			holder._rv_paths.setAdapter(paths_adapter);
			paths_adapter.notifyDataSetChanged();

			int cnt = paths_adapter.getItemCount();
			if (cnt >= 0)
			{
				holder._tv_no_paths.setText(cnt + " paths");
			}
			holder._rv_images.setOnScrollListener(new RecyclerView.OnScrollListener()
			{
				@Override
				public void onScrolled(RecyclerView recyclerView, int dx, int dy)
				{
					super.onScrolled(recyclerView, dx, dy);

					LinearLayoutManager linearLayoutManager = (LinearLayoutManager) holder._rv_images
							.getLayoutManager();
					scrollPosition = linearLayoutManager.findFirstVisibleItemPosition();
					Log.d("Log--> ", " scrollPosition--" + scrollPosition);

					if (scrollPosition != -1)
					{
						holder._rv_paths.smoothScrollToPosition(scrollPosition);
					}
				}
			});
		}
	}

	@Override
	public int getItemCount()
	{
		return _list_titles.size();
	}

	public class TitlesViewHolder extends RecyclerView.ViewHolder
	{
		TextView _tv_titles, _tv_no_paths;

		RecyclerView _rv_images, _rv_paths;

		public TitlesViewHolder(@NonNull View itemView)
		{
			super(itemView);
			_tv_titles = itemView.findViewById(R.id.tv_title);
			_tv_no_paths = itemView.findViewById(R.id.tv_no_paths);
			_rv_images = itemView.findViewById(R.id.rv_images);
			_rv_paths = itemView.findViewById(R.id.rv_paths);
		}
	}
}
