package com.madosweb.censoredstars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;

/**
 * Created by Mohamed Abulgasem on 2018/05/08.
 */
public class MainActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setActivityBase(this);
    }

    /**
     * @param view View
     */
    public void onStartGame(View view) {
        startActivity(new Intent(MainActivity.this, PlayActivity.class));
    }

    /**
     * @param view View
     */
    public void onViewProfile(View view) {
        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
    }

    /**
     * @param view View
     */
    public void onExit(View view) {
        moveTaskToBack(true);
    }

    /**
     * This method handles the back button interaction
     * Exit the app and move it on the backstack
     */
    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}