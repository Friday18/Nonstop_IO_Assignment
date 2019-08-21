package com.nonstopio.Network;

import java.util.List;

import com.nonstopio.Model.Retro_TitleList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface iGetDataServices
{

	@GET("paths")
	Call<List<Retro_TitleList>> getAllData();
}
