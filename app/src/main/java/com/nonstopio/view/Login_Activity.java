package com.nonstopio.view;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.nonstopio.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends Activity
{

	private CallbackManager mcallbackManager;

	SharedPreferences sharedPreferences;

	private LoginButton _fbLoginbtn;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sharedPreferences = getSharedPreferences(Commons.SP, Context.MODE_PRIVATE);
		FacebookSdk.sdkInitialize(getApplicationContext());
		AppEventsLogger.activateApp(this);
		initView();
		makeKeyHash();

	}

	private void makeKeyHash()
	{
		try
		{
			PackageInfo info = this.getPackageManager().getPackageInfo("com.nonstopio", PackageManager.GET_SIGNATURES);
			for (Signature signature : info.signatures)
			{
				MessageDigest md = MessageDigest.getInstance("SHA");
				md.update(signature.toByteArray());
				//Log.d("KeyHash", "KeyHash:" + Base64.encodeToString(md.digest(), Base64.DEFAULT));
				Toast.makeText(getApplicationContext(), Base64.encodeToString(md.digest(), Base64.DEFAULT),
						Toast.LENGTH_LONG).show();
			}
		}
		catch (PackageManager.NameNotFoundException e)
		{

		}
		catch (NoSuchAlgorithmException e)
		{

		}
	}

	private void initView()
	{
		_fbLoginbtn = findViewById(R.id.fb_loginbtn);
		_fbLoginbtn.setReadPermissions(Arrays.asList("email", "public_profile"));

		mcallbackManager = CallbackManager.Factory.create();

		//registering call back
		_fbLoginbtn.registerCallback(mcallbackManager, new FacebookCallback<LoginResult>()
		{
			@Override
			public void onSuccess(LoginResult loginResult)
			{
				// Retrieving access token using the LoginResult
				AccessToken accessToken = loginResult.getAccessToken();
				boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

				Log.d("Log---> ", "isLoggedIn : " + isLoggedIn);
				if (isLoggedIn)
				{
					useLoginInformation(accessToken);
					Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
				}
				else
				{
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString(Commons.NAME, "");
					editor.apply();
					Toast.makeText(Login_Activity.this, getResources().getString(R.string.failure_login_message),
							Toast.LENGTH_SHORT).show();

				}
			}

			@Override
			public void onCancel()
			{
				Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onError(FacebookException error)
			{
				Toast.makeText(getApplicationContext(), R.string.some_error_occured, Toast.LENGTH_LONG).show();
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
					startActivity(new Intent(Login_Activity.this, Home_Screen_Activity.class));
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
}
