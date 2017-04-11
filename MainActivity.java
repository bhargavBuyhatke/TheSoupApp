package in.thesoup.thesoup;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private StoryFeedAdapter mStoryfeedAdapter;
    private List<StoryData> mStoryData;
    private RecyclerView StoryView;
    public LoginResult mloginRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstorieslist);


       /* loginButton = (LoginButton) findViewById(R.id.login_button);
        callbackmanager = CallbackManager.Factory.create();

        loginButton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("User ID: ", loginResult.getAccessToken().getUserId());
                Log.d("Auth Token: ", loginResult.getAccessToken().getToken());

            }




            @Override
            public void onCancel() {


            }

            @Override
            public void onError(FacebookException error) {


            }
        });*/



    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Semibolditalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );





        /*callbackmanager= CallbackManager.Factory.create();

        loginButton = (LoginButton)findViewById(R.id.followbutton);

        loginButton.registerCallback(callbackmanager, new FacebookCallback<LoginResult>() {*/


        mStoryData = new ArrayList<>();



        StoryView = (RecyclerView)findViewById(R.id.list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StoryView.setLayoutManager(layoutManager);

        StoryView.setHasFixedSize(true);

        Networkutils networkutils = new Networkutils(MainActivity.this,mStoryData);

        mStoryfeedAdapter = new StoryFeedAdapter(mStoryData,MainActivity.this);
        networkutils.getFeed(mStoryfeedAdapter);



        StoryView.setAdapter(mStoryfeedAdapter);



    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    }



