package com.guosen.application;

import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity implements Button.OnClickListener
{
    TextView textView = null;
    Button btnDownload = null;

    @Override
    public void onClick(View view)
    {
        view.setVisibility(View.GONE);
        String[] urls = {
                "http://blog.csdn.net/iispring/article/details/47115879",
                "http://blog.csdn.net/iispring/article/details/47180325",
                "http://blog.csdn.net/iispring/article/details/47300819",
                "http://blog.csdn.net/iispring/article/details/47320407",
                "http://blog.csdn.net/iispring/article/details/47622705"
        };
        DownloadTask task = new DownloadTask("task-1");
        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urls);
        new DownloadTask("task-0").executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, urls);
        task.cancel(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        btnDownload = findViewById(R.id.btnDownload);
    }

    public final class DownloadTask extends AsyncTask<String, Object, Long>
    {
        String name;
        DownloadTask(String name) {
            this.name = name;
        }
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            btnDownload.setEnabled(false);
            textView.setText("开始下载...");
        }

        @Override
        protected Long doInBackground(String... strings)
        {
            long totalByte = 0;
            for(String url: strings)
            {
                Object[] result = downloadSingleFile(url);
                int byteCount = (int)result[0];
                totalByte += byteCount;
                publishProgress(result);
                if(isCancelled()){
                    break;
                }
            }
            return totalByte;
        }
        private Object[] downloadSingleFile(String str)
        {
            Object[] result = new Object[2];
            int byteCount = 0;
            String blogName = "";
            HttpURLConnection conn = null;
            try{
                URL url = new URL(str);
                conn = (HttpURLConnection)url.openConnection();
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buf = new byte[1024];
                int length = -1;
                while ((length = is.read(buf)) != -1) {
                    baos.write(buf, 0, length);
                    byteCount += length;
                }
                String respone = new String(baos.toByteArray(), "utf-8");
                int startIndex = respone.indexOf("<title>");
                if(startIndex > 0)
                {
                    startIndex += 7;
                    int endIndex = respone.indexOf("</title>");
                    if(endIndex > startIndex) {
                        blogName = respone.substring(startIndex, endIndex);
                    }
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            finally
            {
                if(conn != null) {
                    conn.disconnect();
                }
            }
            result[0] = byteCount;
            result[1] = blogName;
            return result;
        }

        @Override
        protected void onProgressUpdate(Object... values)
        {
            Log.e("onProgressUpdate", name);
            super.onProgressUpdate(values);
            int byteCount = (int)values[0];
            String blogName = (String)values[1];
            String text = textView.getText().toString();
            text += "\n\n" + blogName + '\n' + byteCount + "字节";
            textView.setText(text);
        }

        @Override
        protected void onPostExecute(Long aLong)
        {
            super.onPostExecute(aLong);
            String text = textView.getText().toString();
            text += "\n\n全部下载完成，总共下载了" + aLong + "个字节";
            textView.setText(text);
            btnDownload.setEnabled(true);
        }

        @Override
        protected void onCancelled()
        {
            super.onCancelled();
            textView.setText("取消下载");
            btnDownload.setEnabled(true);
        }

        @Override
        protected void onCancelled(Long aLong)
        {
            super.onCancelled(aLong);
            textView.setText("取消下载" + aLong);
            btnDownload.setEnabled(true);
        }
    }

    static {
        System.loadLibrary("native-lib");
    }

    public native String stringFromJNI();
}
