package com.nonstopio.view;

import java.util.ArrayList;
import java.util.List;

import com.nonstopio.R;
import com.nonstopio.Model.Retro_TitleList;
import com.nonstopio.Model.Titles_Adapter;
import com.nonstopio.Network.RetrofitClientInstance;
import com.nonstopio.Network.iGetDataServices;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Screen_Activity extends Activity
{

	private Context _mcontext;

	SharedPreferences _sp;

	private RecyclerView _rv_titles;

	private List<Retro_TitleList> retro_titleList;

	private Titles_Adapter titles_adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		_mcontext = getApplicationContext();
		initView();
	}

	private void initView()
	{
		try
		{
			_sp = getSharedPreferences(Commons.SP, MODE_PRIVATE);
			String str_Name = _sp.getString(Commons.NAME, "");
			TextView _tv_fb_name = findViewById(R.id.tv_fb_name);
			Log.d("Log--->", "name:" + str_Name);
			_tv_fb_name.setText("Hi, " + str_Name);
			retro_titleList = new ArrayList<>();
			_rv_titles = findViewById(R.id.rv_titles);
			titles_adapter = new Titles_Adapter(this, retro_titleList);
			LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
			//set up main recycler view
			//			_rv_titles.setHasFixedSize(true);
			_rv_titles.setLayoutManager(layoutManager);
			_rv_titles.setAdapter(titles_adapter);
			titles_adapter.notifyDataSetChanged();
			iGetDataServices iGetDataServices = RetrofitClientInstance.getRetrofitInstance()
					.create(iGetDataServices.class);
			Call<List<Retro_TitleList>> call = iGetDataServices.getAllData();
			call.enqueue(new Callback<List<Retro_TitleList>>()
			{
				@Override
				public void onResponse(Call<List<Retro_TitleList>> call, Response<List<Retro_TitleList>> response)
				{
					try
					{
						Toast.makeText(_mcontext, "success", Toast.LENGTH_LONG).show();
						String str = response.body().toString();
						Log.d("Log-->", "str::" + str);
						List<Retro_TitleList> list_resp = response.body();
						retro_titleList.addAll(list_resp);
						titles_adapter.notifyDataSetChanged();

						LinearLayout ll_pb = findViewById(R.id.ll_pb);
						ll_pb.setVisibility(View.GONE);
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
				}

				@Override
				public void onFailure(Call<List<Retro_TitleList>> call, Throwable t)
				{
					Toast.makeText(_mcontext, "Failure", Toast.LENGTH_LONG).show();
					Log.e("Log-->", "ERROR\n" + t.getCause());
				}
			});
		}
		catch (Exception e)
		{
			Log.e("Log--> Error", "" + e);
			e.printStackTrace();
		}
	}
}
