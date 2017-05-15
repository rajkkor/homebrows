package com.example.vincentg.project;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by vince on 2017-03-03.
 */

public class animations{
    private View ContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    public void crossfade(final View ContentView,final View LoadingView) {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        ContentView.setAlpha(0f);
        ContentView.setVisibility(View.VISIBLE);

        // Animates the content view to 100% opacity, and clear any animation
        // listener set on the view.
        ContentView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        LoadingView.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        LoadingView.setVisibility(View.GONE);
                    }
                });
    }

    //if user doesn't want to use Default values for animation
    public void crossfade(final View ContentView,final View LoadingView,int mShortAnimationDuration) {

        // Set the content view to 0% opacity but visible, so that it is visible
        // (but fully transparent) during the animation.
        ContentView.setAlpha(0f);
        ContentView.setVisibility(View.VISIBLE);

        // Animates the content view to 100% opacity, and clear any animation
        // listener set on the view.
        ContentView.animate().alpha(1f).setDuration(mShortAnimationDuration).setListener(null);

        // Animate the loading view to 0% opacity. After the animation ends,
        // set its visibility to GONE as an optimization step (it won't
        // participate in layout passes, etc.)
        LoadingView.animate().alpha(0f).setDuration(mShortAnimationDuration).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                LoadingView.setVisibility(View.GONE);
            }
        });
    }
}
