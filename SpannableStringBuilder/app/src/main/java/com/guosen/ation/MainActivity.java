package com.guosen.ation;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    TextView etv;
    SpannableStringBuilder ssb;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etv = findViewById(R.id.tv);
        etv.setMovementMethod(LinkMovementMethod.getInstance());
        new EditText(this);
        ssb = new SpannableStringBuilder("AndroidLinux_iOSUNIXOracleAppleMicrosoft");
        setStyle0();
        etv.setText(ssb);
    }
    protected void setStyle0()
    {
        ForegroundColorSpan fore = new ForegroundColorSpan(Color.BLUE);
        ssb.setSpan(fore, 0, 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        BackgroundColorSpan back = new BackgroundColorSpan(Color.LTGRAY);
        ssb.setSpan(back, 2, 4, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        AbsoluteSizeSpan abs = new AbsoluteSizeSpan(72);
        ssb.setSpan(abs, 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        StyleSpan style = new StyleSpan(Typeface.BOLD_ITALIC);
        ssb.setSpan(style, 6, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        UnderlineSpan under = new UnderlineSpan();
        ssb.setSpan(under, 8, 10, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        StrikethroughSpan strike = new StrikethroughSpan();
        ssb.setSpan(strike, 10, 12, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ClickableSpan click = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.e("ClickableSpan1", "onClick");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(0xff38bdfe);
            }
        };
        ssb.setSpan(click, 12, 17, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        click = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Log.e("ClickableSpan2", "onClick");
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                ds.setColor(Color.GREEN);
            }
        };
        ssb.setSpan(click, 23, ssb.length() - 5, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
    }
}
