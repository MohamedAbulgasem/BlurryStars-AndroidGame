package com.madosweb.blurrystars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.madosweb.blurrystars.callbacks.GetUserCallback;
import com.madosweb.blurrystars.entities.User;
import com.madosweb.blurrystars.requests.UserRequest;
import com.madosweb.blurrystars.utilities.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Abulgasem on 2018/05/08.
 */
public class ProfileActivity extends BaseClass implements GetUserCallback.IGetUserResponse {

    @BindView(R.id.profile_pic)
    ImageView profilePicImageView;

    @BindView(R.id.user_name)
    TextView userNameTextView;

    @BindView(R.id.highest_score)
    TextView highestScoreTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setActivityBase(this);
        setHighestScoreTextView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setTitle("Profile");
        DataManager.getInstance().resetScore();
        if (DataManager.getInstance().getUser() == null) {
            UserRequest.makeUserRequest(new GetUserCallback(ProfileActivity.this).getCallback());
        } else {
            GlideApp.with(this)
                    .load(DataManager.getInstance().getUser().getPicture())
                    .placeholder(R.drawable.user_placeholder)
                    .into(profilePicImageView);
            userNameTextView.setText(DataManager.getInstance().getUser().getName());
        }
    }


    @Override
    public void onCompleted(User user) {
        DataManager.getInstance().setUser(user);
        onResume();
    }

    private void setHighestScoreTextView() {
        highestScoreTextView.setText(String.valueOf(getSharedPref().getInt("highestScore", 0)));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
    }

}
