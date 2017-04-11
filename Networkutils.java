package in.thesoup.thesoup;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jani on 06-04-2017.
 */

public class Networkutils {
    private  Context mcontext;
    private List<StoryData> mStoryData;

    public Networkutils(Context context, List<StoryData> storyData){
        this.mcontext = context;
        this.mStoryData= storyData;
    }

   public void getFeed(final StoryFeedAdapter feedAdapter) {

      MySingleton singleton = MySingleton.getInstance(mcontext);

       //RequestQueue queue = singleton.getRequestQueue();


    JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, SoupContract.URL, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.i("akunamatata", response.toString());



                   gsonConversion mpopulateUI = new gsonConversion();

                 mpopulateUI.fillUI(response,mStoryData,feedAdapter);


                }


                //mEarthquakedatajsonclass = red;

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // Auto-generated method stub

                }
            });/*{
        @Override
        public Map<String, String> getHeaders() throws AuthFailureError {
            HashMap<String,String> headers = new HashMap<String, String>();
            headers.put("TOKEN_KEY","TokenValue");
            headers.put("USER_ID","userId");
            return headers;
        }
        //nee to implement params for post request.
    };*/

    singleton.addToRequestQueue(jsObjRequest);

    //MySingleton.getInstance(this).addToRequestQueue(stringRequest);



}

    public void followrequest(String storyId){

        MySingleton singleton = MySingleton.getInstance(mcontext);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, SoupContract.FOLLOWURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("followjsonresponse", response.toString());




                    }


                    //mEarthquakedatajsonclass = red;

                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Auto-generated method stub

                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("TOKEN_KEY","TokenValue");
                headers.put("USER_ID","userId");
                return headers;
            }
            //nee to implement params for post request.
        };




    }

    public void loginvolleyRequest(){

        MySingleton singleton = MySingleton.getInstance(mcontext);

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, SoupContract.FOLLOWURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.i("followjsonresponse", response.toString());




                    }



                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String,String> headers = new HashMap<String, String>();
                headers.put("TOKEN_KEY","TokenValue");
                headers.put("USER_ID","userId");
                return headers;
            }
            //TODO: nee to implement params for post request.
        };



    }

}