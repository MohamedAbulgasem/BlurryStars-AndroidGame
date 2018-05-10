package com.madosweb.censoredstars;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;

import java.util.Objects;

/**
 * Created by Mohamed Abulgasem on 2018/05/09.
 */
public abstract class BaseClass extends AppCompatActivity {

    Context context;

    void setActivityBase(Context context) {
        this.context = context;
        validateAccessToken(context);
        if (!(context instanceof MainActivity))
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
    }

    /**
     * @param context Context
     */
    void validateAccessToken(Context context){
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isNotLoggedIn = accessToken == null || accessToken.isExpired();
        // If not logged in, redirect to LoginActivity
        if (isNotLoggedIn)
            startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_profile:
                if (!(context instanceof ProfileActivity))
                startActivity(new Intent(context, ProfileActivity.class));
                break;
            case R.id.action_logout:
                showLogoutAlertDialog(context);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    /**
     * This method creates a logout Alert Dialog
     * from main menu and handles the logout process
     * When logged out, remove username reference
     * from sharedPreference and intent to LoginActivity
     */
    private void showLogoutAlertDialog(final Context context) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setIcon(R.drawable.ic_action_name)
                .setTitle("Logout")
                .setMessage("Are you sure you want to log out?")
                .setPositiveButton("Log out", (new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        LoginManager.getInstance().logOut();
                        startActivity(new Intent(context, LoginActivity.class));
                    }
                }))
                .setNegativeButton("Cancel", null)
                .show();
    }


    /**
     * Every activity and fragment that you integrate with the FacebookSDK
     * Login or Share should forward onActivityResult to the callbackManager.
     *
     * @param requestCode int
     * @param resultCode  int
     * @param data        Intent
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

}
