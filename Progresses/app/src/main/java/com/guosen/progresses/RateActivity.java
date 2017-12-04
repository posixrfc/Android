package com.guosen.progresses;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class RateActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener
{
    private RatingBar rbRating,rbRating1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate);
        rbRating = findViewById(R.id.rbRating);
        rbRating1=findViewById(R.id.rbRating1);
        //手动设置第一个RatingBar的属性值
        rbRating.setMax(100);
        rbRating.setProgress(20);
        rbRating.setOnRatingBarChangeListener(this);
        rbRating1.setOnRatingBarChangeListener(this);
    }
    @Override
    public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
        System.err.println("progress:"+ratingBar.getProgress()+" rating :"+rating);
    }
}