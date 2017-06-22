package com.itheima.getsdavail;

import java.io.File;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StatFs;
import android.app.Activity;
import android.text.format.Formatter;
import android.widget.TextView;

public class MainActivity extends Activity
{
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		File path = Environment.getExternalStorageDirectory();
        StatFs stat = new StatFs(path.getPath());
        long blockSize;
        long totalBlocks;
        long availableBlocks;
        
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2)
        {
        	 blockSize = stat.getBlockSizeLong();
             totalBlocks = stat.getBlockCountLong();
             availableBlocks = stat.getAvailableBlocksLong();
        }
        else
        {
        	blockSize = stat.getBlockSize();
            totalBlocks = stat.getBlockCount();
            availableBlocks = stat.getAvailableBlocks();
        }
        TextView tv = (TextView) findViewById(R.id.tv);
        tv.setText(formatSize(availableBlocks * blockSize));
	}
	private String formatSize(long size) {
        return Formatter.formatFileSize(this, size);
    }
}