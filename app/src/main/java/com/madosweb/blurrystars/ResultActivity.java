package com.madosweb.blurrystars;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifTextView;

public class ResultActivity extends BaseClass {

    @BindView(R.id.result_btn)
    Button resultBtn;

    @BindView(R.id.result_gif_image)
    GifTextView resultGifImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);
        setActivityBase(this);
        handleResultBtn();
    }

    /**
     *
     */
    private void handleResultBtn() {
        setTitle("Well Done!");
        if (getIntent().getIntExtra("Result", 0) != 1) {
            setTitle("Opps! Got It Wrong!");
            resultBtn.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            resultBtn.setText("TRY AGAIN!");
            resultGifImage.setBackgroundResource(R.drawable.wrong_answer_gif);
        }
        resultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ResultActivity.this, PlayActivity.class));
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(ResultActivity.this, MainActivity.class));
    }
}
