package zhaojing.com.myowntest;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.util.ArrayMap;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;

import zhaojing.com.myowntest.common.adapter.MyViewPagerAdapter;
import zhaojing.com.myowntest.net.Request;
import zhaojing.com.myowntest.net.RequestCallback;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static String path="http://192.168.1.11:8080/web/servlets/servlet/FileUploadServlet";
    private static Context mContext;
    private MyHandler handler;

    private ViewPager mViewPager;
    private MyViewPagerAdapter mPagerAdapter;
    private ArrayList<View> mPagerViewList;

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
        setContentView(R.layout.activity_main);
        mContext=this;
        initView();
        initData();
        handler=new MyHandler();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }else if (id==R.id.nav_get){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Request request=new Request(callback);
                    String[] params={"zhaojing","123456"};
                    request.get(path,params);
                }
            }).start();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private static class MyHandler extends Handler{

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:

                case 0:
                    Toast.makeText(mContext,msg.obj.toString(),Toast.LENGTH_SHORT).show();
            }
        }
    }

     RequestCallback callback =new RequestCallback() {
         @Override
         public void callback(String name, String pwd,String message) {

             System.out.println("thread------------"+Thread.currentThread().getName());
             Message msg=Message.obtain();
             msg.what=1;
             msg.obj=name;
             handler.sendMessage(msg);
         }

         @Override
         public void backfalse() {
             Message msg=Message.obtain();
             msg.what=0;
             msg.obj="flase";
             handler.sendMessage(msg);
         }
     };


}
