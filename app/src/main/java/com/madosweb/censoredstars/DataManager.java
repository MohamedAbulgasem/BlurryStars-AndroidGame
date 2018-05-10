package com.madosweb.censoredstars;

import com.madosweb.censoredstars.entities.User;

/**
 * Created by Mohamed Abulgasem on 2018/05/09.
 */
public class DataManager {

    private static DataManager instance = null;

    private User user;

    private DataManager() {
        user = null;
    }

    /**
     * @return DataManager
     */
    public static DataManager getInstance() {
        if (instance == null) {
            instance = new DataManager();
        }
        return instance;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

}
