package com.mohitum.androidpoc.view;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mohitum.androidpoc.R;
import com.mohitum.androidpoc.model.Feed;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * This class will be used as an adapter to inflate views in feeds list
 * Created by mohitum on 23-11-2017.
 *
 * @version 1.0
 */
public class FeedsAdapter extends RecyclerView.Adapter<FeedsAdapter.FeedViewHolder> {

    /**
     * List of adapter data
     */
    private List<Feed> feeds = Collections.emptyList();

    /**
     * Layout Inflater instance to inflate adapter views
     */
    private LayoutInflater inflater;

    /**
     * Calling activity context
     */
    private Activity activityContext;

    /**
     * Adapter constructor
     *
     * @param activityContext Activity calling context
     * @param feeds feeds list data
     */
    public FeedsAdapter(Activity activityContext, List<Feed> feeds) {
        inflater = LayoutInflater.from(activityContext);
        this.activityContext = activityContext;
        this.feeds = feeds;
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.feed_row, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        Feed feed = feeds.get(position);
        holder.txtVwFeedTitle.setText(feed.getTitle());
        holder.txtVwFeedDescription.setText(feed.getDescription());
        Glide.with(activityContext).load(feed.getImageHref()).into(holder.imgVwFeed);
    }

    @Override
    public int getItemCount() {
        return feeds.size();
    }

    /**
     * This class will be used as a view holder representing single view for the adapter
     * Created by mohitum on 23-11-2017.
     *
     * @version 1.0
     */
    class FeedViewHolder extends RecyclerView.ViewHolder {

        /**
         * UI references.
         */
        @BindView(R.id.txt_vw_feed_title) TextView txtVwFeedTitle;
        @BindView(R.id.txt_vw_feed_description) TextView txtVwFeedDescription;
        @BindView(R.id.img_vw_feed) ImageView imgVwFeed;

        /**
         * Feed View holder constructor
         *
         * @param itemView item layout view
         */
        private FeedViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}