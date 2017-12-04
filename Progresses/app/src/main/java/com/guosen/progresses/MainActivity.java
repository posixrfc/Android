package com.guosen.progresses;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private Button btnAdd, btnReduce, btnVisible;
    private ProgressBar pbHor, pbLarge;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_press);
        btnAdd = findViewById(R.id.btnAdd);
        btnReduce = findViewById(R.id.btnReduce);
        btnVisible = findViewById(R.id.btnVisible);
        pbHor = findViewById(R.id.pbHor);
        pbLarge = findViewById(R.id.pbLarge);
        btnAdd.setOnClickListener(mathClick);
        btnReduce.setOnClickListener(mathClick);
        btnVisible.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 判断Large进度条是否显示，显示则隐藏，隐藏则显示
                if (pbLarge.getVisibility() == View.VISIBLE) {
                    pbLarge.setVisibility(View.GONE);
                } else {
                    pbLarge.setVisibility(View.VISIBLE);
                }
            }
        });
//        TextView textView = findViewById(R.id.sample_text);
//        textView.setText(stringFromJNI());
    }

    private View.OnClickListener mathClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnAdd:
                    if (pbHor.getProgress() < 90) {
                        pbHor.setProgress((int) (pbHor.getProgress() * 1.2));
                    }
                    if (pbHor.getSecondaryProgress() < 100) {
                        pbHor.setSecondaryProgress((int) (pbHor.getSecondaryProgress() * 1.2));
                    }
                    break;
                case R.id.btnReduce:
                    if (pbHor.getProgress() > 10) {
                        pbHor.incrementProgressBy(-10);
                    }
                    if (pbHor.getSecondaryProgress() > 20) {
                        pbHor.incrementSecondaryProgressBy(-10);
                    }
                    break;
            }
        }
    };

    static {
        System.loadLibrary("native-lib");
    }
    protected final native String stringFromJNI();
}