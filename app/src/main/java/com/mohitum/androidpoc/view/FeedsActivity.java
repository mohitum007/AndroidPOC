package com.mohitum.androidpoc.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.mohitum.androidpoc.R;
import com.mohitum.androidpoc.api.ApiClient;
import com.mohitum.androidpoc.api.IApiInterface;
import com.mohitum.androidpoc.model.FeedResponse;
import com.mohitum.androidpoc.utils.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Activity class to fetch and display list of the feeds
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class FeedsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.swipe_refresh_feeds) SwipeRefreshLayout swipeRefreshLayoutFeeds;
    @BindView(R.id.rec_vw_feeds) RecyclerView recVwFeed;
    @BindView(R.id.txt_vw_error) TextView txtVwError;

    private IApiInterface apiService;

    private ProgressDialog progressDialog;

    private int dataSize = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeds);

        initializeLayout();
        setUpFragmentLayout();
    }

    /**
     * initialize activity UI and instance variables
     */
    public void initializeLayout() {
        ButterKnife.bind(FeedsActivity.this);
        apiService = ApiClient.getClient().create(IApiInterface.class);
    }

    /**
     * Set up activity UI with necessary data
     */
    public void setUpFragmentLayout() {
        swipeRefreshLayoutFeeds.setOnRefreshListener(FeedsActivity.this);
        if(Utils.isInternetConnectionAvailable(FeedsActivity.this)){
            fetchFeedsAndDisplay(false);
        } else {
            //Show error
            showErrorMessage(true, getString(R.string.no_internet_error_message));
        }
    }

    @Override
    public void onRefresh() {
        fetchFeedsAndDisplay(true);
    }

    /**
     * Fetch feeds data and inflate in the recycler view adapter
     */
    private void fetchFeedsAndDisplay(boolean isRefresh) {
        if(!isRefresh)
            showProgressDialog(getString(R.string.loading_progress));
        else
            swipeRefreshLayoutFeeds.setRefreshing(true);
        Call<FeedResponse> call = apiService.fetchFeeds();
        call.enqueue(new Callback<FeedResponse>() {
            @Override
            public void onResponse(Call<FeedResponse> call, Response<FeedResponse> response) {
                dismissProgressDialog(progressDialog);
                if(swipeRefreshLayoutFeeds.isRefreshing()){
                    swipeRefreshLayoutFeeds.setRefreshing(false);
                }
                FeedResponse body = response.body();

                ActionBar actionBar = getSupportActionBar();
                if(actionBar!=null && body.getTitle() != null){
                    actionBar.setTitle(body.getTitle());
                }
                dataSize = body.getFeeds().size();
                FeedsAdapter navigationDrawerAdapter = new FeedsAdapter(FeedsActivity.this, body.getFeeds());
                recVwFeed.setAdapter(navigationDrawerAdapter);
                recVwFeed.setLayoutManager(new LinearLayoutManager(FeedsActivity.this));
                showErrorMessage(false, "");
            }

            @Override
            public void onFailure(Call<FeedResponse> call, Throwable t) {
                dismissProgressDialog(progressDialog);
                if(swipeRefreshLayoutFeeds.isRefreshing()){
                    swipeRefreshLayoutFeeds.setRefreshing(false);
                }
                showErrorMessage(true, getString(R.string.server_error));
            }
        });
    }

    private void showErrorMessage(boolean isShown, String message){
        txtVwError.setText(message);
        txtVwError.setVisibility(isShown ? View.VISIBLE : View.GONE);
        recVwFeed.setVisibility(isShown ? View.GONE : View.VISIBLE);
    }

    /**
     * Method to show progress alert dialog with custom message
     *
     * @param progressMessage custom message
     */
    private void showProgressDialog(String progressMessage){
        progressDialog = new ProgressDialog(FeedsActivity.this);
        progressDialog.setMessage(progressMessage);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    /**
     * Dismisses a dialog if already running
     *
     * @param dialog dialog to be dismissed
     */
    private void dismissProgressDialog(ProgressDialog dialog){
        if (dialog != null && dialog.isShowing()) { //  check for null and showing
            // dismiss the dialog
            dialog.dismiss();
        }
    }

    public int getDataSize() {
        return dataSize;
    }
}
