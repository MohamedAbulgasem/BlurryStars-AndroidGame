package com.madosweb.censoredstars;

import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * Created by Mohamed Abulgasem on 2018/05/08.
 */
public class PlayActivity extends BaseClass {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        setActivityBase(this);
    }
}
