package com.nonstopio.Model;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.nonstopio.R;
import com.squareup.picasso.Picasso;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

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
	public void onBindViewHolder(@NonNull final PathViewHolder holder, final int position)
	{
		final Retro_SubPaths retro_subPaths = _list_sub_paths.get(position);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		mactivity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int width = displayMetrics.widthPixels;

		Timer buttonTimer = new Timer();
		buttonTimer.schedule(new TimerTask()
		{

			@Override
			public void run()
			{
				mactivity.runOnUiThread(new Runnable()
				{
					@Override
					public void run()
					{
						Picasso.with(mactivity).load(retro_subPaths.getImage()).resize(width, 350)
								.into(holder._iv_image);
						holder._ll_loadImage.setVisibility(View.GONE);
					}
				});
			}
		}, 1100);
	}

	@Override
	public int getItemCount()
	{
		return _list_sub_paths.size();
	}

	public class PathViewHolder extends RecyclerView.ViewHolder
	{
		ImageView _iv_image;

		LinearLayout _ll_loadImage;

		public PathViewHolder(@NonNull View itemView)
		{
			super(itemView);
			_iv_image = itemView.findViewById(R.id.iv_image);
			_ll_loadImage = itemView.findViewById(R.id.ll_loadImage);
			ProgressBar mProgressBar = itemView.findViewById(R.id.pb_1);
			Drawable drawable = mactivity.getResources().getDrawable(R.drawable.progress_bar);
			mProgressBar.setProgressDrawable(drawable);
		}
	}

	private Animation inFromRightAnimation()
	{
		AnimationSet animationSet = new AnimationSet(true);
		Animation inFromRight = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, -1.0f,
				Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT,
				0.0f);
		inFromRight.setDuration(500);
		inFromRight.setFillAfter(false);
		inFromRight.setInterpolator(new AccelerateInterpolator());

		animationSet.addAnimation(inFromRight);

		return animationSet;
	}

}
