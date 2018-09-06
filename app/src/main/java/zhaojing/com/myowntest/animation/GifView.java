package zhaojing.com.myowntest.animation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import java.io.InputStream;

import zhaojing.com.myowntest.R;


/**
 * AUTHOR：Created by zhaojing on 2018-07-13 17:14
 * EMAIL:  2910763715@qq.com
 *
 * could play gif by ImageView
 */

public class GifView extends AppCompatImageView{

    private Movie movie;
    private long mMovieStart;

    public GifView(Context context) {
        super(context);
    }

    public GifView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GifView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        movie=null;
        mMovieStart=0;

        TypedArray a=context.obtainStyledAttributes(attrs, R.styleable.GifView,defStyleAttr,0);
        int srcID=a.getResourceId(R.styleable.GifView_gif,0);
        if(srcID>0){
            InputStream is=context.getResources().openRawResource(srcID);
            movie=Movie.decodeStream(is);
        }
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        long now = android.os.SystemClock.uptimeMillis();
        if(mMovieStart==0){
            mMovieStart=now;
        }
        if(movie!=null){
            int dur=movie.duration();
            if(dur==0){
                dur=1000;
            }

            int relTime=(int) ((now-mMovieStart)%dur);
            movie.setTime(relTime);
            movie.draw(canvas,0,0);
            invalidate();
        }
    }
}
