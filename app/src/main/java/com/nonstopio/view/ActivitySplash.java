package com.nonstopio.view;

import com.nonstopio.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class ActivitySplash extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		showSplash();
	}

	private void showSplash()
	{
		Thread mSplashThread = new Thread()
		{

			@Override
			public void run()
			{
				try
				{
					synchronized (this)
					{
						wait(1000);
					}
				}
				catch (final Exception ex)
				{
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							Log.e("ERROR", "" + ex.getLocalizedMessage());
						}
					});

				}
				finally
				{
					runOnUiThread(new Runnable()
					{
						@Override
						public void run()
						{
							goToNextScreen();
						}
					});

				}

			}

		};

		mSplashThread.start();

	}

	private void goToNextScreen()
	{
		startActivity(new Intent(this, Login_Activity.class));
	}
}
