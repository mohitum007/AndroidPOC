package com.mohitum.androidpoc.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.mohitum.androidpoc.R;
import com.mohitum.androidpoc.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity class to fetch and display list of the feeds
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class FeedsActivity extends AppCompatActivity {

    @BindView(R.id.rec_vw_feeds) RecyclerView recVwCourse;
    @BindView(R.id.txt_vw_error) TextView txtVwCourseError;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        initializeLayout();
        setUpFragmentLayout();
    }

    /**
     * initialize activity UI and instance variables
     */
    public void initializeLayout() {
        ButterKnife.bind(FeedsActivity.this);
    }

    /**
     * Set up activity UI with necessary data
     */
    public void setUpFragmentLayout() {
        if(Utils.isInternetConnectionAvailable(FeedsActivity.this)){
            //fetchFeedsDataAndDisplay();
        } else {
            //Show error
        }
    }

}
