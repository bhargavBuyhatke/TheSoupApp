package in.thesoup.thesoup;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import in.thesoup.thesoup.Adapters.SingleStoryAdapter;
import in.thesoup.thesoup.Adapters.StoryFeedAdapter;
import in.thesoup.thesoup.GSONclasses.FeedGSON.GetStoryFeed;
import in.thesoup.thesoup.GSONclasses.FeedGSON.StoryData;
import in.thesoup.thesoup.GSONclasses.SinglestoryGSON.GetSingleStory;
import in.thesoup.thesoup.GSONclasses.SinglestoryGSON.Substories;
import in.thesoup.thesoup.GSONclasses.SinglestoryGSON.Substoryjsondata;

/**
 * Created by Jani on 07-04-2017.
 */

public class gsonConversion {


    private JSONObject mJsonObject;
    private List<StoryData> mListFromJson;
    private Substoryjsondata mSubstoryjsonData;
    private String StoryTitle , followstatus;

    public void fillUI(JSONObject jsonObject,List<StoryData> mListFromJson,StoryFeedAdapter feedAdapter){

        mJsonObject = jsonObject;

        Gson gson = new Gson();
        GetStoryFeed red = gson.fromJson(jsonObject.toString(),GetStoryFeed.class);

        for(int i=0;i<red.getStoryDataList().size();i++){

            mListFromJson.add(red.getStoryDataList().get(i));
        }

       feedAdapter.refreshData(mListFromJson);
    }

    public void fillStoryUI(JSONObject jsonObject,List<Substories> substories, SingleStoryAdapter mSingleStoryAdapter,String StoryTitle, String followstatus){

        mJsonObject = jsonObject;

        Gson gson = new Gson();
        GetSingleStory red = gson.fromJson(jsonObject.toString(),GetSingleStory.class);

        StoryTitle = red.getdata().getStoryName();
        followstatus = red.getdata().getfollowStatus();

       for(int i=0;i<red.getdata().getSubstories().size();i++){

            substories.add(red.getdata().getSubstories().get(i));
        }


      mSingleStoryAdapter.refreshData(substories,StoryTitle,followstatus);



    }





}
