package zhaojing.com.myowntest.common.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;

import zhaojing.com.myowntest.R;

/**
 * Created by Administrator on 2018-03-20.
 */
public class TestMainActivity extends FragmentActivity{

    private ViewPager mViewPager;
    private MyViewPagerAdapter mPagerAdapter;
    private ArrayList<View> mPagerViewList;

    private Handler mHandler;
    private void initView() {
        mViewPager = (ViewPager) findViewById(R.id.viewpager);

    }

    private void initData() {

        mPagerViewList = new ArrayList<>();
        View tab_one = LayoutInflater.from(this).inflate(R.layout.tab_news, null);
        mPagerViewList.add(tab_one);
        mPagerViewList.add(tab_one);
        mPagerAdapter=new MyViewPagerAdapter(getApplicationContext(),mPagerViewList);
        mViewPager.setAdapter(mPagerAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pager_main);
        initView();
        initData();
    }


    /***
     * 开启一个子线程
     */
    public void beginSubThread(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                Looper.prepare();
                mHandler=new Handler(){
                    @Override
                    public void handleMessage(Message msg) {
                        super.handleMessage(msg);
                        System.out.println(" sub thread Click name is "+Thread.currentThread().getName()+" msg is"+msg.obj.toString());
                    }
                };
                Looper.loop();

            }
        }).start();
    }

    /***
     * 点击按钮开启一个子线程
     * @param view
     */
    public void onSubThreadClick(View view){
        new Thread(new Runnable() {
            @Override
            public void run() {
                Message msg=mHandler.obtainMessage();
                msg.obj="sub thread send message to sub extra thread !";
                mHandler.sendMessage(msg);
            }
        }).start();
    }
    /**
     * 实现主线程发送消息给子线程
     * @param view
     */
    public void onHandlerClick(View view){

        HandlerThread thread=new HandlerThread("handlerThread");
        thread.start();
        mHandler=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                System.out.println("current thread name is "+Thread.currentThread().getName()+" msg is "+msg.obj.toString());
            }
        };
        Message msg=mHandler.obtainMessage();
        msg.obj="123";
        mHandler.sendMessage(msg);

    }
}
