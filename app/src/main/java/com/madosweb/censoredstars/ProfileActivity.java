package com.madosweb.censoredstars;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.madosweb.censoredstars.callbacks.GetUserCallback;
import com.madosweb.censoredstars.entities.User;
import com.madosweb.censoredstars.requests.UserRequest;
import com.madosweb.censoredstars.utilities.GlideApp;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ButterKnife.bind(this);
        setActivityBase(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

}
