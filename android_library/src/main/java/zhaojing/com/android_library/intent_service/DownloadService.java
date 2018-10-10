package zhaojing.com.android_library.intent_service;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.text.format.DateUtils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * AUTHOR：Created by Administrator on 2018-10-10 09:59
 * EMAIL:  2910763715@qq.com
 * 一句话总结 IntentService：
 * 优先级比较高的、用于串行执行异步任务、会自尽的 Service。
 */

public class DownloadService extends IntentService {
    private static final String TAG = "DownloadService";
    public static final String DOWNLOAD_URL="down_load_url";
    public static final int WHAT_DOWNLOAD_STATED=2;
    public static final int WHAT_DOWNLOAD_FINISHED=1;

    public DownloadService(){
        super(TAG);
    }
    private static Handler mUIHandler;
    public static void setmUIHandler(final Handler uihandler){
        mUIHandler=uihandler;
    }

    /**
     * 这个方法运行在子线程
      * @param intent
     */
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String url=intent.getStringExtra(DOWNLOAD_URL);
        if(!TextUtils.isEmpty(url)){
            sendMessageToMainThread(WHAT_DOWNLOAD_STATED, "\n "  + " 开始下载任务：\n" + url);
            try{
                Bitmap bitmap = downloadUrlToBitmap(url);
                SystemClock.sleep(1000);//
                sendMessageToMainThread(WHAT_DOWNLOAD_FINISHED,bitmap);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void sendMessageToMainThread(final int id,final Object o){
        if(mUIHandler!=null){
            mUIHandler.sendMessage(mUIHandler.obtainMessage(id,o));
        }
    }

    /**
     * 下载图片
     * @param url
     * @return
     * @throws Exception
     */
    private Bitmap downloadUrlToBitmap(String url) throws Exception{
        HttpURLConnection urlConnection = (HttpURLConnection) new URL(url).openConnection();
        BufferedInputStream in =new BufferedInputStream(urlConnection.getInputStream(),8*1024);
        Bitmap bitmap = BitmapFactory.decodeStream(in);
        urlConnection.disconnect();
        in.close();
        return bitmap;
    }
}
