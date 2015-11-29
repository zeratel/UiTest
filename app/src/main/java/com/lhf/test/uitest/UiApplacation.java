package com.lhf.test.uitest;

import android.app.Application;
import android.view.Gravity;

import com.codemonkeylabs.fpslibrary.FrameDataCallback;
import com.codemonkeylabs.fpslibrary.TinyDancer;

/**
 * com.lhf.test.uitest
 * Created by zeratel3000
 * on 2015 11 15/11/29 23 44
 * description
 */
public class UiApplacation extends Application {

    @Override
    public void onCreate() {
        TinyDancer.create()
                .show(this);

        //alternatively
        TinyDancer.create()
                .redFlagPercentage(.1f) // set red indicator for 10%
                .startingGravity(Gravity.TOP)
                .startingXPosition(200)
                .startingYPosition(600)
                .show(this);

        //you can add a callback to get frame times and the calculated
        //number of dropped frames within that window
        TinyDancer.create()
                .addFrameDataCallback(new FrameDataCallback() {
                    @Override
                    public void doFrame(long previousFrameNS, long currentFrameNS, int droppedFrames) {
                        //collect your stats here
                    }
                })
                .show(this);
    }
}
