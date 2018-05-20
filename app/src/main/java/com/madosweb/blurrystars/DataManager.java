package com.madosweb.blurrystars;

import com.madosweb.blurrystars.entities.Star;
import com.madosweb.blurrystars.entities.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohamed Abulgasem on 2018/05/09.
 */
public class DataManager {

    private static DataManager instance = null;
    private int index = 0;
    private int score = 0;

    private User user;
    private List<Star> stars;
    private List<Star> starsRecord;

    private DataManager() {
        user = null;
        starsRecord = new ArrayList<>();
        buildAllStars();
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

    private void buildAllStars() {
        stars = new ArrayList<>();
        stars.add(new Star(index++, "Leonardo Dicaprio", R.mipmap.leonardo_dicaprio_original, R.mipmap.leonardo_dicaprio_blurred));
        stars.add(new Star(index++, "Cristiano Ronaldo", R.mipmap.cristiano_ronaldo_original, R.mipmap.cristiano_ronaldo_blurred));
        stars.add(new Star(index++, "Paul Walker", R.mipmap.paul_walker_original, R.mipmap.paul_walker_blurred));
        stars.add(new Star(index++, "Denzel Washington", R.mipmap.denzel_washington_original, R.mipmap.denzel_washington_blurred));
        stars.add(new Star(index++, "Selena Gomez", R.mipmap.selena_gomez_original, R.mipmap.selena_gomez_blurred));
        stars.add(new Star(index++, "Pink", R.mipmap.pink_original, R.mipmap.pink_blurred));
        stars.add(new Star(index++, "Beyonce", R.mipmap.beyonce_original, R.mipmap.beyonce_blurred));
        stars.add(new Star(index++, "Jim Carrey", R.mipmap.jim_carrey_original, R.mipmap.jim_carrey_blurred));
        stars.add(new Star(index++, "Bonucci", R.mipmap.bonucci_original, R.mipmap.bonucci_blurred));
        stars.add(new Star(index++, "Will Smith", R.mipmap.will_smith_original, R.mipmap.will_smith_blurred));
        stars.add(new Star(index++, "Tupac", R.mipmap.tupac_original, R.mipmap.tupac_blurred));
        stars.add(new Star(index++, "Tina Turner", R.mipmap.tina_turner_original, R.mipmap.tina_turner_blurred));
        stars.add(new Star(index++, "Thiago Silva", R.mipmap.thiago_silva_original, R.mipmap.thiago_silva_blurred));
        stars.add(new Star(index++, "Dele Allie", R.mipmap.dele_allie_original, R.mipmap.dele_allie_blurred));
        stars.add(new Star(index++, "Gareth Bale", R.mipmap.gareth_bale_original, R.mipmap.gareth_bale_blurred));
        stars.add(new Star(index++, "Griezmann", R.mipmap.griezmann_original, R.mipmap.griezmann_blurred));
        stars.add(new Star(index++, "Johnny Depp", R.mipmap.johnny_depp_original, R.mipmap.johnny_depp_blurred));
        stars.add(new Star(index++, "Lionel Messi", R.mipmap.lionel_messi_original, R.mipmap.lionel_messi_blurred));
        stars.add(new Star(index++, "Manuel Neuer", R.mipmap.manuel_neuer_original, R.mipmap.manuel_neuer_blurred));
        stars.add(new Star(index++, "Neymar", R.mipmap.neymar_original, R.mipmap.neymar_blurred));
        stars.add(new Star(index++, "Penelope Cruz", R.mipmap.penelope_cruz_original, R.mipmap.penelope_cruz_blurred));
        stars.add(new Star(index++, "Rowan Sebastian Atkinson", R.mipmap.rowan_sebastian_atkinson_original, R.mipmap.rowan_sebastian_atkinson_blurred));
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Star findStarById(int id) {
        for (Star star : stars){
            if (star.getId() == id){
                return star;
            }
        }
        return null;
    }

    public List<Star> getAllStars() {
        return stars;
    }

    public void addStarToRecord(Star star) {
        if (starsRecord.size() == (stars.size()-1)) {
            starsRecord.clear();
        }
        starsRecord.add(star);
    }

    public List<Star> getStarsRecord() {
        return starsRecord;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score += score;
    }

    public void resetScore() {
        this.score = 0;
    }

}
