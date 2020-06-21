package com.example.myredditapiapp.presentation.main.category.detail;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.Nullable;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.databinding.DataBindingUtil;

import com.example.myredditapiapp.Constants;
import com.example.myredditapiapp.R;
import com.example.myredditapiapp.base.BaseActivity;
import com.example.myredditapiapp.databinding.ActivityCategoryDetailBinding;
import com.example.myredditapiapp.di.modules.GlideApp;
import com.example.myredditapiapp.utils.GlideUtil;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class CategoryDetailActivity extends BaseActivity {

    private SimpleExoPlayer simpleExoPlayer;

    private String title, detailUrl = "";
    private boolean isVideo, isImage = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCategoryDetailBinding activityCategoryDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_category_detail);

        if (getIntent() != null && getIntent().getExtras() != null) {
            title = getIntent().getExtras().getString(Constants.EXTRA_CATEGORY_TITLE);
            detailUrl = getIntent().getExtras().getString(Constants.EXTRA_CATEGORY_DETAIL_URL);
            isVideo = getIntent().getExtras().getBoolean(Constants.EXTRA_CATEGORY_IS_VIDEO);
            isImage = getIntent().getExtras().getBoolean(Constants.EXTRA_CATEGORY_IS_IMAGE);
        }

        setUpToolBar(activityCategoryDetailBinding.lytToolbarCategoryDetail.toolbar, title);

        if (detailUrl != null && !detailUrl.isEmpty()) {
            if (isVideo && !isImage) {
                setUpExoPlayer(activityCategoryDetailBinding.playerView,
                        activityCategoryDetailBinding.lytCircularProgressBar.circularProgressBar,
                        detailUrl);
                activityCategoryDetailBinding.playerView.setVisibility(VISIBLE);
                activityCategoryDetailBinding.imgThumbnail.setVisibility(GONE);
                activityCategoryDetailBinding.webView.setVisibility(GONE);
            } else if (!isVideo && isImage) {
                GlideUtil.setUpImageWithPlaceHolder(GlideApp.with(this), detailUrl, activityCategoryDetailBinding.imgThumbnail);
                activityCategoryDetailBinding.imgThumbnail.setVisibility(VISIBLE);
                activityCategoryDetailBinding.webView.setVisibility(GONE);
                activityCategoryDetailBinding.playerView.setVisibility(GONE);
            } else {
                setUpWebView(activityCategoryDetailBinding.webView,
                        activityCategoryDetailBinding.lytCircularProgressBar.circularProgressBar,
                        detailUrl);
                activityCategoryDetailBinding.webView.setVisibility(VISIBLE);
                activityCategoryDetailBinding.imgThumbnail.setVisibility(GONE);
                activityCategoryDetailBinding.playerView.setVisibility(GONE);
            }
        }
    }

    private void setUpExoPlayer(PlayerView playerView,
                                ContentLoadingProgressBar circularProgressBar,
                                String detailUrl) {
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer);
        // Produces DataSource instances through which media data is loaded.
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                Util.getUserAgent(this, getString(R.string.app_name)));
        // This is the MediaSource representing the media to be played.
        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(detailUrl));
        // Prepare the player with the source.
        simpleExoPlayer.prepare(videoSource);
        simpleExoPlayer.setPlayWhenReady(true);
        simpleExoPlayer.addListener(new Player.EventListener() {
            @Override
            public void onTimelineChanged(Timeline timeline, int reason) {

            }

            @Override
            public void onTimelineChanged(Timeline timeline, @Nullable Object manifest, int reason) {

            }

            @Override
            public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

            }

            @Override
            public void onLoadingChanged(boolean isLoading) {

            }

            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                switch (playbackState) {
                    case Player.STATE_BUFFERING:
                        circularProgressBar.setVisibility(VISIBLE);
                        break;
                    case Player.STATE_ENDED:
                        simpleExoPlayer.seekTo(0);
                        break;
                    case Player.STATE_IDLE:
                        break;
                    case Player.STATE_READY:
                        circularProgressBar.setVisibility(GONE);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPlaybackSuppressionReasonChanged(int playbackSuppressionReason) {

            }

            @Override
            public void onIsPlayingChanged(boolean isPlaying) {

            }

            @Override
            public void onRepeatModeChanged(int repeatMode) {

            }

            @Override
            public void onShuffleModeEnabledChanged(boolean shuffleModeEnabled) {

            }

            @Override
            public void onPlayerError(ExoPlaybackException error) {

            }

            @Override
            public void onPositionDiscontinuity(int reason) {

            }

            @Override
            public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

            }

            @Override
            public void onSeekProcessed() {

            }
        });
    }

    private void setUpWebView(WebView webView,
                              ContentLoadingProgressBar circularProgressBar,
                              String detailUrl) {
        // Configure related browser settings
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        // Load the initial URL
        // Enable responsive layout
        webView.getSettings().setUseWideViewPort(true);
        // Zoom out if the content width is greater than the width of the viewport
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setBuiltInZoomControls(true); // allow pinch to zooom
        webView.getSettings().setDisplayZoomControls(false); // disable the default zoom controls on the page

        webView.loadUrl(detailUrl);

        // Configure the client to use when opening URLs
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                circularProgressBar.setVisibility(VISIBLE);
            }

            @Override
            public void onPageCommitVisible(WebView view, String url) {
                super.onPageCommitVisible(view, url);
                circularProgressBar.setVisibility(GONE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        if (simpleExoPlayer != null) {
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
        super.onDestroy();
    }
}