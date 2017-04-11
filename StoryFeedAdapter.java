package in.thesoup.thesoup;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.List;

import static android.R.attr.format;
import static android.R.attr.start;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.google.gson.internal.bind.util.ISO8601Utils.format;
import static in.thesoup.thesoup.R.id.Story_title;
import static in.thesoup.thesoup.R.id.month;

/**
 * Created by Jani on 07-04-2017.
 */

public class StoryFeedAdapter extends RecyclerView.Adapter<StoryFeedAdapter.DataViewHolder> {

    private List<StoryData> StoryDataList;
    private Context context;

    public StoryFeedAdapter(List<StoryData> Datalist, Context context) {
        this.StoryDataList = Datalist;
        this.context = context;
    }

    public void refreshData(List<StoryData> Datalist) {
        this.StoryDataList = Datalist;
        notifyDataSetChanged();
    }

    public class DataViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView storyTitle, substoryTitle, date, month, year, numberOfArticles;
        public ImageView imageView;
        public Button mButton;
        public LoginButton loginbutton;


        public DataViewHolder(View itemView) {
            super(itemView);

            storyTitle = (TextView) itemView.findViewById(R.id.Story_title);
            substoryTitle = (TextView) itemView.findViewById(R.id.substory_title);
            date = (TextView) itemView.findViewById(R.id.date);
            month = (TextView) itemView.findViewById(R.id.month);
            year = (TextView) itemView.findViewById(R.id.year);
            numberOfArticles = (TextView) itemView.findViewById(R.id.number_of_articles);
            mButton = (Button) itemView.findViewById(R.id.followbutton);

            imageView = (ImageView) itemView.findViewById(R.id.main_image);
            loginbutton = (LoginButton) itemView.findViewById(R.id.login_button);

            loginbutton.setOnClickListener(this);

            imageView.setOnClickListener(this);

            storyTitle.setOnClickListener(this);

            mButton.setOnClickListener(this);


        }


        @Override
        public void onClick(View view) {

            int mposition = getAdapterPosition();
            String mString = StoryDataList.get(mposition).getStoryId();

            if (view == imageView || view == storyTitle) {

                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("story_id", mString);

                context.startActivity(intent);
            } else if (view == mButton) {
                String mfollowstatus = StoryDataList.get(mposition).getFollowStatus();

                if (mfollowstatus == null || mfollowstatus.equals("0")) {

                    if (AccessToken.getCurrentAccessToken() == null) {

                        Intent intent = new Intent(context, LoginActivity.class);
                        //TODO: let the user be shown following after sucess

                        context.startActivity(intent);

                    } else {

                        mButton.setText("Following");


                        //TODO: server request and let him know following, pass on story ID

                    }

                }else if (mfollowstatus.equals("1")) {
                    mButton.setText("Follow");
                    //TODO: unfollow request network call return and then change text
                }
            }


        }

    }

    @Override
    public StoryFeedAdapter.DataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.story, parent, false);

        return new DataViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(StoryFeedAdapter.DataViewHolder holder, int position) {

        final StoryData mStoryData = StoryDataList.get(position);

        String storytitle = mStoryData.getStoryName();
        String substorytitle = mStoryData.getSubStoryName();
        String Time = mStoryData.getTime();
        String Num_of_articles = mStoryData.getNumArticle();
        String followstatus = mStoryData.getFollowStatus();


        String month = null;
        try {
            month = monthFomrat(Time);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("Not valid time", Time);
        }

        Log.d("Month", month);


        String Date = null;
        try {
            Date = DateFomrat(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String year = null;
        try {
            year = yearFomrat(Time);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        String ImageUrl;

        if (mStoryData.getImageUrl() != null) {
            ImageUrl = mStoryData.getImageUrl();
        } else {
            ImageUrl = mStoryData.getArticleImageUrl();
        }

        holder.storyTitle.setText(storytitle);
        holder.substoryTitle.setText(substorytitle);
        Picasso.with(context).load(ImageUrl).into(holder.imageView);

        holder.date.setText(Date);
        holder.month.setText(month);
        holder.year.setText(year);
        holder.numberOfArticles.setText(Num_of_articles + " ARTICLES ");

        if (followstatus == "1") {
            holder.mButton.setText("Following");
        }

       /* holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("story_id",mStoryData.getStoryId());

                context.startActivity(intent);

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return StoryDataList.size();
    }


    public String monthFomrat(String string) throws ParseException {
        SimpleDateFormat monthformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = monthformat.parse(string);
       /* try {
            date = dateformat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("Time sent is not valid",string);
        }*/

        SimpleDateFormat monthFormat2 = new SimpleDateFormat("MMMM");

        return monthFormat2.format(date);


    }

    public String yearFomrat(String string) throws ParseException {
        SimpleDateFormat yearformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = yearformat.parse(string);
       /* try {
            date = dateformat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("Time sent is not valid",string);
        }*/

        SimpleDateFormat yearFormat2 = new SimpleDateFormat("yyyy");

        return yearFormat2.format(date);


    }


    public String DateFomrat(String string) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dateformat.parse(string);
       /* try {
            date = dateformat.parse(string);
        } catch (ParseException e) {
            e.printStackTrace();
            Log.d("Time sent is not valid",string);
        }*/

        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd");

        return dateFormat2.format(date);


    }


}
