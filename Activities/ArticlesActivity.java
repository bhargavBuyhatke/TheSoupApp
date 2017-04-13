package in.thesoup.thesoup.Activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import in.thesoup.thesoup.R;

/**
 * Created by Jani on 13-04-2017.
 */

public class ArticlesActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.articles);
    }
}
