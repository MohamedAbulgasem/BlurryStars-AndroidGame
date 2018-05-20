package com.madosweb.blurrystars;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.madosweb.blurrystars.entities.Star;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Mohamed Abulgasem on 2018/05/08.
 */
public class PlayActivity extends BaseClass {

    @BindView(R.id.star_image_view)
    ImageView starImageView;

    @BindView(R.id.option_1)
    Button option1btn;

    @BindView(R.id.option_2)
    Button option2btn;

    @BindView(R.id.option_3)
    Button option3btn;

    private Star star;

    /**
     * @param savedInstanceState Bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        ButterKnife.bind(this);
        setActivityBase(this);
        manageGame();
    }

    /**
     *
     */
    private void manageGame() {
        setTitle("Score " + String.valueOf(DataManager.getInstance().getScore()));
        setStar();
        Star firstWrongStar = getFirstWrongStar();
        Star secondWrongStar = getSecondWrongStar(firstWrongStar);
        setOptionBtns(star.getName(), firstWrongStar.getName(), secondWrongStar.getName());
    }

    private void setStar() {
        if (DataManager.getInstance().getStarsRecord().isEmpty()) {
            int i = new Random().nextInt(DataManager.getInstance().getAllStars().size());
            star = DataManager.getInstance().getAllStars().get(i);
            DataManager.getInstance().addStarToRecord(star);
            starImageView.setImageResource(star.getBlurredImage());
        } else {
            do {
                int i = new Random().nextInt(DataManager.getInstance().getAllStars().size());
                star = DataManager.getInstance().getAllStars().get(i);
            } while (DataManager.getInstance().getStarsRecord().contains(star));
            starImageView.setImageResource(star.getBlurredImage());
            DataManager.getInstance().addStarToRecord(star);
        }
    }

    private Star getFirstWrongStar() {
        int i;
        do i = new Random().nextInt(DataManager.getInstance().getAllStars().size());
        while (i == star.getId());
        return DataManager.getInstance().getAllStars().get(i);
    }

    private Star getSecondWrongStar(Star star2) {
        int i;
        do i = new Random().nextInt(DataManager.getInstance().getAllStars().size());
        while (i == star.getId() || i == star2.getId());
        return DataManager.getInstance().getAllStars().get(i);
    }

    class SleepTask extends AsyncTask<Integer, Void, Void> {

        @Override
        protected Void doInBackground(Integer... integers) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            startActivity(new Intent(PlayActivity.this, ResultActivity.class).putExtra("Result", integers[0]));
            return null;
        }

    }

    private void setOriginalImage() {
        starImageView.setImageResource(star.getImage());
    }

    private void checkForHighestScore() {
        int currentScore = DataManager.getInstance().getScore();
        int highestScore = getSharedPref().getInt("highestScore", 0);
        if (currentScore > highestScore) {
            getSharedPrefEditor().putInt("highestScore", currentScore);
            getSharedPrefEditor().apply();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PlayActivity.this, MainActivity.class));
    }

    private void setOptionBtns(String rightOption, String option2, String option3) {
        int i = new Random().nextInt(3);
        switch (i) {
            case 0:
                setOptionBtnsVariant1(rightOption, option2, option3);
                break;
            case 1:
                setOptionBtnsVariant2(rightOption, option2, option3);
                break;
            case 2:
                setOptionBtnsVariant3(rightOption, option2, option3);
                break;
        }
    }

    private void setOptionBtnsVariant1(String rightOption, String option2, String option3) {
        option1btn.setText(option2);
        option2btn.setText(rightOption);
        option3btn.setText(option3);

        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });

        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().setScore(100);
                view.setBackgroundColor(getResources().getColor(R.color.green));
                setOriginalImage();
                checkForHighestScore();
                new SleepTask().execute(1);
            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });
    }

    private void setOptionBtnsVariant2(String rightOption, String option2, String option3) {
        option1btn.setText(option2);
        option2btn.setText(option3);
        option3btn.setText(rightOption);

        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });

        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().setScore(100);
                view.setBackgroundColor(getResources().getColor(R.color.green));
                setOriginalImage();
                checkForHighestScore();
                new SleepTask().execute(1);
            }
        });
    }

    private void setOptionBtnsVariant3(String rightOption, String option2, String option3) {
        option1btn.setText(rightOption);
        option2btn.setText(option2);
        option3btn.setText(option3);

        option1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().setScore(100);
                view.setBackgroundColor(getResources().getColor(R.color.green));
                setOriginalImage();
                checkForHighestScore();
                new SleepTask().execute(1);
            }
        });

        option2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });

        option3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataManager.getInstance().resetScore();
                view.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                setOriginalImage();
                new SleepTask().execute(0);
            }
        });
    }
}
