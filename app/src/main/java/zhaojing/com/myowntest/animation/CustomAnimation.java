package zhaojing.com.myowntest.animation;

import android.view.animation.Animation;
import android.view.animation.Transformation;

import java.math.BigDecimal;

/**
 * AUTHOR：Created by Administrator on 2018-07-13 16:01
 * EMAIL:  2910763715@qq.com
 */

public class CustomAnimation extends Animation{

    private float mDis;
    private float mVelocity;

    public CustomAnimation(float a,float b){
        this.mDis=a;
        this.mVelocity=b;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        //change float to double
        BigDecimal aa=new BigDecimal(String.valueOf(interpolatedTime*mVelocity));
        double dest=aa.doubleValue();

        float distance=(float) (Math.sin(dest)*mDis);
        t.getMatrix().setTranslate(distance,0);
    }
}
