package com.nonstopio.view;

import java.util.ArrayList;
import java.util.List;

import com.nonstopio.R;
import com.nonstopio.Model.Retro_TitleList;
import com.nonstopio.Model.Titles_Adapter;
import com.nonstopio.Network.RetrofitClientInstance;
import com.nonstopio.Network.iGetDataServices;
import com.nonstopio.utility.Commons;

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
			_rv_titles.setLayoutManager(layoutManager);
			_rv_titles.setAdapter(titles_adapter);
			titles_adapter.notifyDataSetChanged();
			iGetDataServices iGetDataServices = RetrofitClientInstance.getRetrofitInstance()
					.create(iGetDataServices.class);
			Call<List<Retro_TitleList>> call = iGetDataServices.getAllData();
			call.enqueue(new Callback<List<Retro_TitleList>>()
			{
				@Override
				public void onResponse(Call<List<Retro_TitleList>> call, final Response<List<Retro_TitleList>> response)
				{
					try
					{
						Toast.makeText(_mcontext, "Login Successfull...", Toast.LENGTH_LONG).show();
						//String str = response.body().toString();
						//Log.d("Log-->", "str::" + str);
						String dummyResponce = "[{\"id\":\"0\",\"title\":\"Far Rockaway/Bayswater\",\"sub_paths\":[{\"id\":\"0-0\",\"title\":\"Ocean Parkway South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-0\",\"title\":\"Cipriani\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-0\",\"title\":\"Auburn North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-0\",\"title\":\"Greater Las Vegas National\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-0\",\"title\":\"Auburn North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-0\",\"title\":\"Seven Hills Area\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"6-0\",\"title\":\"Far Rockaway/Bayswater\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"1\",\"title\":\"Sagaponack Seaside\",\"sub_paths\":[{\"id\":\"0-1\",\"title\":\"Cleveland Park\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"1-1\",\"title\":\"East Renton\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-1\",\"title\":\"Cleveland Park\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-1\",\"title\":\"Northwoods West\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"4-1\",\"title\":\"Sea Ranch Lakes\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-1\",\"title\":\"Babylon Bayside\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"6-1\",\"title\":\"Seven Hills Area\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"2\",\"title\":\"Rockville East of Hungerford Dr\",\"sub_paths\":[{\"id\":\"0-2\",\"title\":\"White Oak South of Columbia Pike\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-2\",\"title\":\"Greater Las Vegas National\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"2-2\",\"title\":\"Mott Haven/Port Morris\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"3-2\",\"title\":\"Mott Haven/Port Morris\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-2\",\"title\":\"River Heights\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-2\",\"title\":\"Sagaponack Seaside\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"6-2\",\"title\":\"Pennypack\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-2\",\"title\":\"South of Lake Ave\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"8-2\",\"title\":\"Rockville East of Hungerford Dr\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"9-2\",\"title\":\"Pound Ridge East\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"10-2\",\"title\":\"North Norridge\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"3\",\"title\":\"Seven Hills Area\",\"sub_paths\":[{\"id\":\"0-3\",\"title\":\"Jamaica Estates/Holliswood\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-3\",\"title\":\"Pennypack\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"2-3\",\"title\":\"Murray Hill\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-3\",\"title\":\"East Renton\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"4-3\",\"title\":\"Sea Ranch Lakes\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"5-3\",\"title\":\"East of Telegraph Road\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"6-3\",\"title\":\"Candlewood Country Club\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-3\",\"title\":\"Olmsted Falls Central\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"8-3\",\"title\":\"Summerlin North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"9-3\",\"title\":\"Pound Ridge East\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"10-3\",\"title\":\"Cleveland Park\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"4\",\"title\":\"Central Chandler\",\"sub_paths\":[{\"id\":\"0-4\",\"title\":\"North East Irwindale\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"1-4\",\"title\":\"Kingsbridge Heights\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-4\",\"title\":\"Florissant West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-4\",\"title\":\"Sunshine-Gardens\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-4\",\"title\":\"Bronxdale\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-4\",\"title\":\"Ocean Parkway South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"6-4\",\"title\":\"Cleveland Park\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-4\",\"title\":\"Rockville East of Hungerford Dr\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"8-4\",\"title\":\"Far Rockaway/Bayswater\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"9-4\",\"title\":\"River Heights\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"10-4\",\"title\":\"Candlewood Country Club\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"11-4\",\"title\":\"Greater Las Vegas National\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"}]},{\"id\":\"5\",\"title\":\"Auburn North\",\"sub_paths\":[{\"id\":\"0-5\",\"title\":\"Rockville East of Hungerford Dr\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"1-5\",\"title\":\"Pennypack\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-5\",\"title\":\"Ladue South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-5\",\"title\":\"White Oak South of Columbia Pike\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"4-5\",\"title\":\"Ladue South\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"5-5\",\"title\":\"Bronxdale\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"6-5\",\"title\":\"Far Rockaway/Bayswater\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"7-5\",\"title\":\"Ocean Parkway South\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"}]},{\"id\":\"6\",\"title\":\"North East Irwindale\",\"sub_paths\":[{\"id\":\"0-6\",\"title\":\"Northwest Midlothian/Midlothian Country Club\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"1-6\",\"title\":\"Renton West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-6\",\"title\":\"River Heights\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-6\",\"title\":\"Babylon Bayside\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"4-6\",\"title\":\"Northwest Midlothian/Midlothian Country Club\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"5-6\",\"title\":\"North Norridge\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"6-6\",\"title\":\"Allegheny West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-6\",\"title\":\"Murray Hill\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"8-6\",\"title\":\"White Plains Central\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"9-6\",\"title\":\"South of Lake Ave\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"7\",\"title\":\"Sagaponack Seaside\",\"sub_paths\":[{\"id\":\"0-7\",\"title\":\"phoenix\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"1-7\",\"title\":\"River Heights\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"2-7\",\"title\":\"Murray Hill\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-7\",\"title\":\"North East Irwindale\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"4-7\",\"title\":\"Allegheny West\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"5-7\",\"title\":\"Mott Haven/Port Morris\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"6-7\",\"title\":\"Mount Kisco West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-7\",\"title\":\"South of Lake Shore Blvd\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"8-7\",\"title\":\"Cleveland Park\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"9-7\",\"title\":\"Brentwood Central\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"10-7\",\"title\":\"North Norridge\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"}]},{\"id\":\"8\",\"title\":\"Candlewood Country Club\",\"sub_paths\":[{\"id\":\"0-8\",\"title\":\"Seven Hills Area\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"1-8\",\"title\":\"Cipriani\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-8\",\"title\":\"Pound Ridge East\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-8\",\"title\":\"West Covina East\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-8\",\"title\":\"Jamaica Estates/Holliswood\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"5-8\",\"title\":\"Renton West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"6-8\",\"title\":\"Florissant West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"7-8\",\"title\":\"Central Chandler\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"8-8\",\"title\":\"Central Chandler\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"9-8\",\"title\":\"Murray Hill\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"10-8\",\"title\":\"Gates Mills North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"9\",\"title\":\"Northwoods West\",\"sub_paths\":[{\"id\":\"0-9\",\"title\":\"Far Rockaway/Bayswater\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"1-9\",\"title\":\"Ocean Parkway South\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"2-9\",\"title\":\"Sea Ranch Lakes\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"10\",\"title\":\"East Renton\",\"sub_paths\":[{\"id\":\"0-10\",\"title\":\"Cleveland Park\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-10\",\"title\":\"Ocean Parkway South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-10\",\"title\":\"Northwest Midlothian/Midlothian Country Club\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"11\",\"title\":\"River Heights\",\"sub_paths\":[{\"id\":\"0-11\",\"title\":\"South of Lake Ave\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-11\",\"title\":\"Sagaponack Seaside\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-11\",\"title\":\"Rockville East of Hungerford Dr\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"3-11\",\"title\":\"Pound Ridge East\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-11\",\"title\":\"Pound Ridge East\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-11\",\"title\":\"Summerlin North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]},{\"id\":\"12\",\"title\":\"River Heights\",\"sub_paths\":[{\"id\":\"0-12\",\"title\":\"Bridesburg\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"1-12\",\"title\":\"Bushwick South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"2-12\",\"title\":\"Summerlin North\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-12\",\"title\":\"East of Telegraph Road\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-12\",\"title\":\"Rockville East of Hungerford Dr\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"5-12\",\"title\":\"Ocean Parkway South\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"}]},{\"id\":\"13\",\"title\":\"River Heights\",\"sub_paths\":[{\"id\":\"0-13\",\"title\":\"Greater Las Vegas National\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"1-13\",\"title\":\"Sea Ranch Lakes\",\"image\":\"https://www.sftravel.com/sites/sftraveldev.prod.acquia-sites.com/files/SanFrancisco_0.jpg\"},{\"id\":\"2-13\",\"title\":\"Far Rockaway/Bayswater\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-13\",\"title\":\"North East Irwindale\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"4-13\",\"title\":\"Ocean Parkway South\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"}]},{\"id\":\"14\",\"title\":\"Mount Kisco West\",\"sub_paths\":[{\"id\":\"0-14\",\"title\":\"South of Lake Ave\",\"image\":\"https://amp.businessinsider.com/images/5cca13ca021b4c02360e189c-750-563.jpg\"},{\"id\":\"1-14\",\"title\":\"Sunshine-Gardens\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"2-14\",\"title\":\"Sunshine-Gardens\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"3-14\",\"title\":\"Bushwick South\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"4-14\",\"title\":\"Murray Hill\",\"image\":\"https://cdn.traveltripper.io/site-assets/192_665_3009/media/2017-05-05-072306/DIVA-Exterior-Day-lg.jpg\"},{\"id\":\"5-14\",\"title\":\"Greater Las Vegas National\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"6-14\",\"title\":\"Summerlin North\",\"image\":\"https://media.cntraveler.com/photos/5a67942178741f59690321ec/4:3/w_735,c_limit/The-Fairmont__2018_Buckingham-Balcony-Suite-Balcony.jpg\"},{\"id\":\"7-14\",\"title\":\"South of Bell Road\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"},{\"id\":\"8-14\",\"title\":\"Mount Kisco West\",\"image\":\"https://brokeassstuart-9uzlt3u.netdna-ssl.com/wp-content/pictsnShit/2019/03/san-francisco.jpg\"}]}]";
						Log.d("Log-->", "dummyResponce::" + dummyResponce);
						runOnUiThread(new Runnable()
						{
							@Override
							public void run()
							{
								List<Retro_TitleList> list_resp = response.body();
								retro_titleList.addAll(list_resp);
								titles_adapter.notifyDataSetChanged();
							}
						});

						LinearLayout ll_pb = findViewById(R.id.ll_pb);
						ll_pb.setVisibility(View.GONE);
					}
					catch (Exception e)
					{
						LinearLayout ll_pb = findViewById(R.id.ll_pb);
						ll_pb.setVisibility(View.GONE);
						Toast.makeText(Home_Screen_Activity.this, "Error", Toast.LENGTH_SHORT).show();
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
