package com.nonstopio.Network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nonstopio.utility.Commons;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance
{
	private static Retrofit retrofit;

	public static Retrofit getRetrofitInstance()
	{

		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		// set your desired log level
		logging.setLevel(HttpLoggingInterceptor.Level.BODY);

		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		// add your other interceptors …

		// add logging as last interceptor
		httpClient.addInterceptor(logging); // <-- this is the important line!

		if (retrofit == null)
		{
			Gson gson = new GsonBuilder().setLenient().serializeNulls()/*.excludeFieldsWithoutExposeAnnotation()*/.create();
			retrofit = new Retrofit.Builder().baseUrl(Commons.BASE_URL)
					.addConverterFactory(GsonConverterFactory.create(gson)).client(httpClient.build()).build();
		}
		return retrofit;
	}
}
