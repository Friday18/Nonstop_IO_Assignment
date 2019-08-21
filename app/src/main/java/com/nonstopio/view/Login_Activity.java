package com.nonstopio.view;

import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nonstopio.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity implements View.OnClickListener
{

	private CallbackManager mcallbackManager;

	SharedPreferences sharedPreferences;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sharedPreferences = getSharedPreferences(Commons.SP, Context.MODE_PRIVATE);
		initView();
	}

	private void initView()
	{
		TextView _tv_signIn = findViewById(R.id.tv_signIn);
		_tv_signIn.setOnClickListener(this);
		LoginButton _fb_loginbtn = findViewById(R.id.fb_loginbtn);
		_fb_loginbtn.setReadPermissions(Arrays.asList("email", "public_profile"));

		mcallbackManager = CallbackManager.Factory.create();

		//registering call back
		_fb_loginbtn.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>()
		{
			@Override
			public void onSuccess(LoginResult loginResult)
			{
				// Retrieving access token using the LoginResult
				AccessToken accessToken = loginResult.getAccessToken();
				useLoginInformation(accessToken);
				Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();

			}

			@Override
			public void onCancel()
			{
				Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onError(FacebookException error)
			{
				Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
				Log.d("Log-", "" + error);
			}
		});

	}

	private void useLoginInformation(AccessToken accessToken)
	{
		/**
		 Creating the GraphRequest to fetch user details
		 1st Param - AccessToken
		 2nd Param - Callback (which will be invoked once the request is successful)
		 **/
		GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback()
		{
			//OnCompleted is invoked once the GraphRequest is successful
			@Override
			public void onCompleted(JSONObject object, GraphResponse response)
			{
				try
				{
					String name = object.getString("name");
					Log.d("Name : ", "" + name);

					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString(Commons.NAME, name.trim());
					editor.apply();
				}
				catch (JSONException e)
				{
					e.printStackTrace();
				}
			}
		});
		// We set parameters to the GraphRequest using a Bundle.
		Bundle parameters = new Bundle();
		parameters.putString("fields", "id,name,email,picture.width(200)");
		request.setParameters(parameters);
		// Initiate the GraphRequest
		request.executeAsync();
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		mcallbackManager.onActivityResult(requestCode, resultCode, data);
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void onClick(View v)
	{
		try
		{
			if (v.getId() == R.id.tv_signIn)
			{
				startActivity(new Intent(this, Home_Screen_Activity.class));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
