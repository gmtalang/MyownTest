package zhaojing.com.myowntest.activity;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.graphics.Movie;
import android.os.Bundle;
//import android.support.v7.widget.RecyclerView;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.TextureView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import zhaojing.com.myowntest.R;
import zhaojing.com.myowntest.animation.CustomAnimation;
import zhaojing.com.myowntest.common.test.CallAsynTask;
import zhaojing.com.myowntest.ndk.NDKUtils;

/**
 * Created by zhaojing on 2018-04-08.
 */
public class SurfaceActivity extends FragmentActivity implements View.OnClickListener{
    private CallAsynTask mTask;
    private TextView tx;
    private Button  btn;
    private Movie movie;// question

    private VideoView mVideoView;
    private ImageView imageView;
    private ObjectAnimator mObjectAniamtor;
    private Button mBtn;

    private CustomAnimation mCustomAnimation;
    private Thread t;
    static {
        System.loadLibrary("TestNativeLib");
    }


//    RecyclerView
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_surface);
        init();

    }

    private void init(){
//        mTask=new CallAsynTask(this);

        tx=new TextView(this);
        tx.setText("xiaohua");

        btn=new Button(this);
        btn.setText("initilaze");

        mBtn=findViewById(R.id.animation_translate);
        mBtn.setOnClickListener(this);
//        intializeAnimation();
        NDKUtils ndk = new NDKUtils();
        Log.i("NDK"," = value is :"+ndk.getStringFromNative(100,100));
        String m=ndk.getStringFromNative(100,100);
        mBtn.setText(m);


    }


    private void shuchu(){
        System.out.println(""+tx.getText().toString());
    }

    /**
     * 无论是activity or fragmentactivity,will controll onpause about asyntask
     */

    @Override
    protected void onPause() {
        super.onPause();
        //停掉 asyntask中的任务
        if((mTask!=null)&&(isFinishing())){
            mTask.cancel(false);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }



    private static final class QueryHandler extends AsyncQueryHandler{

        QueryHandler(Context cx){
            super(cx.getContentResolver());
        }
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.animation_translate:
                 beginAnimationTranslate();
                 break;
        }
    }



    private void beginAnimationTranslate(){


        mBtn.setAnimation(mCustomAnimation);
    }

    private void intializeAnimation(){
        mCustomAnimation=new CustomAnimation(30,40);
        mCustomAnimation.setDuration(3000);
        mCustomAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                Toast.makeText(SurfaceActivity.this,"animation start",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Toast.makeText(SurfaceActivity.this,"animation end",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
