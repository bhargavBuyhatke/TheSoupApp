package in.thesoup.thesoup.Activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.List;

import in.thesoup.thesoup.GSONclasses.FeedGSON.StoryData;
import in.thesoup.thesoup.NetworkCalls.Networkutils;
import in.thesoup.thesoup.R;
import in.thesoup.thesoup.Adapters.StoryFeedAdapter;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity {

    private StoryFeedAdapter mStoryfeedAdapter;
    private List<StoryData> mStoryData;
    private RecyclerView StoryView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        FacebookSdk.sdkInitialize(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.getstorieslist);



    CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/OpenSans-Semibolditalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );





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



