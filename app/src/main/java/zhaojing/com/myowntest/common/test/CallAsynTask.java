package zhaojing.com.myowntest.common.test;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.net.URL;

/**
 * AUTHOR：Created by zhaojing on 2018-07-08 11:26
 * EMAIL:  2910763715@qq.com
 */

public class CallAsynTask extends AsyncTask<URL,Integer,Result<Bitmap>>{

    private final WeakReference<Context> mContext;


    public CallAsynTask(Context cx) {
        this.mContext=new WeakReference<Context>(cx);
//        super();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Result<Bitmap> bitmap) {
        super.onPostExecute(bitmap);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onCancelled(Result<Bitmap> bitmap) {
        super.onCancelled(bitmap);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Result<Bitmap> doInBackground(URL... urls) {

        Result<Bitmap> result=new Result<Bitmap>();

        try{
            Bitmap bitmap=null;

            result.result=bitmap;
            return result;
        }catch (Throwable e){
            result.error=e;
        }

        return result;
    }
}
