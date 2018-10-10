package zhaojing.com.android_library.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import zhaojing.com.android_library.R;
import zhaojing.com.android_library.intent_service.DownloadService;


/**
 * AUTHOR：Created by Administrator on 2018-10-10 10:40
 * EMAIL:  2910763715@qq.com
 */

public class IntentServiceActivity extends AppCompatActivity implements Handler.Callback {
    private ImageView mIvDisplay;
    private Button    mBtn_download;
    private TextView  mTextView;
    private List<String> urlList = Arrays.asList("https://ws1.sinaimg.cn/large/610dc034ly1fgepc1lpvfj20u011i0wv.jpg",
            "https://ws1.sinaimg.cn/large/d23c7564ly1fg6qckyqxkj20u00zmaf1.jpg",
            "https://ws1.sinaimg.cn/large/610dc034ly1fgchgnfn7dj20u00uvgnj.jpg");
    private int mFinishCount;


    @Override
    public boolean handleMessage(final Message msg) {
        if (msg != null) {
            switch (msg.what) {
                case DownloadService.WHAT_DOWNLOAD_STATED:
                    mTextView.setText(mTextView.getText() + (String) msg.obj);
                    break;
                case DownloadService.WHAT_DOWNLOAD_FINISHED:
                    mIvDisplay.setImageBitmap((Bitmap) msg.obj);
                    mBtn_download.setText("完成" + mFinishCount + "下载");
                    break;
            }
        }
        return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_intent_service);
        init_view();
        DownloadService.setmUIHandler(new Handler(this));
        mBtn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadImage();
            }
        });
    }

    private void downloadImage() {
        Intent it = new Intent(this, DownloadService.class);
        for (String url : urlList) {
            it.putExtra(DownloadService.DOWNLOAD_URL, url);
            startService(it);
        }
        mBtn_download.setEnabled(false);
    }

    private void init_view() {
        mIvDisplay = (ImageView) findViewById(R.id.iv_display);
        mBtn_download = (Button) findViewById(R.id.btn_download);
        mTextView = (TextView) findViewById(R.id.tv_status);
    }
}
