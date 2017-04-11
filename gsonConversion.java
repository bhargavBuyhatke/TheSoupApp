package in.thesoup.thesoup;

import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.List;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.V;

/**
 * Created by Jani on 07-04-2017.
 */

public class gsonConversion {


    private JSONObject mJsonObject;
    private List<StoryData> mListFromJson;

    public void fillUI(JSONObject jsonObject,List<StoryData> mListFromJson,StoryFeedAdapter feedAdapter){

        mJsonObject = jsonObject;

        Gson gson = new Gson();
        GetStoryFeed red = gson.fromJson(jsonObject.toString(),GetStoryFeed.class);

        for(int i=0;i<red.getStoryDataList().size();i++){

            mListFromJson.add(red.getStoryDataList().get(i));
        }

       feedAdapter.refreshData(mListFromJson);
    }






}
