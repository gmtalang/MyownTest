package zhaojing.com.myowntest.common;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gmtalang on 2018-03-20.
 */
public class MyViewPagerAdapter extends PagerAdapter{

    private Context mContext;
    private List< View>  mPagerList;
    public MyViewPagerAdapter(Context cx,ArrayList< View> pagerList){
        this.mContext=cx;
        this.mPagerList=pagerList;
    }
    @Override
    public int getCount() {
        return this.mPagerList.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
        ((ViewPager)container).removeView(mPagerList.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager)container).addView(mPagerList.get(position));
        return mPagerList.get(position);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return false;
    }
}
