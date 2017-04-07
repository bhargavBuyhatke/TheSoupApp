package in.thesoup.thesoup;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONObject;

/**
 * Created by Jani on 06-04-2017.
 */

public class Networkutils {
    private  Context mcontext;

    public Networkutils(Context context){
         mcontext = context;
    }

   public void makeVolleyRequest() {

      MySingleton singleton = new MySingleton(mcontext);

       RequestQueue queue = singleton.getRequestQueue();


    JsonObjectRequest jsObjRequest = new JsonObjectRequest
            (Request.Method.GET, SoupContract.URL, null, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.i("akunamatata", response.toString());


                }


                //mEarthquakedatajsonclass = red;

            }, new Response.ErrorListener() {

                @Override
                public void onErrorResponse(VolleyError error) {
                    // Auto-generated method stub

                }
            });

    singleton.addToRequestQueue(jsObjRequest);

    //MySingleton.getInstance(this).addToRequestQueue(stringRequest);


}

}