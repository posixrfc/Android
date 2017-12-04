package com.guosen.progresses;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener
{
    private TextView textview1, textview2;
    private SeekBar seekbar1, seekbar2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seek);
        textview1 = findViewById(R.id.textview1);
        textview2 = findViewById(R.id.textview2);
        seekbar1 = findViewById(R.id.seekbar1);
        seekbar2 = findViewById(R.id.seekbar2);
        seekbar1.setOnSeekBarChangeListener(this);
        seekbar2.setOnSeekBarChangeListener(this);
    }
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (seekBar.getId() == R.id.seekbar1) {
            textview2.setText("seekbar1的当前位置是：" + progress);
        } else {
            textview2.setText("seekbar2的当前位置是：" + progress);
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar1) {
            textview1.setText("seekbar1开始拖动");
        } else {
            textview1.setText("seekbar2开始拖动");
        }
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (seekBar.getId() == R.id.seekbar1) {
            textview1.setText("seekbar1停止拖动");
        } else {
            textview1.setText("seekbar2停止拖动");
        }
    }
}