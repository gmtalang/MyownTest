package zhaojing.com.myowntest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Administrator on 2018-04-03.
 */
public class VideoSurfaceView extends SurfaceView implements Runnable{

    private SurfaceHolder mSurfaceholder;
    private Canvas canvas;
    private boolean mDrawing;
    private Path mPath;
    public VideoSurfaceView(Context context) {
        super(context);
        init();
    }

    public VideoSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public VideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public VideoSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }


    private void init(){

        mPath=new Path();

        mSurfaceholder= this.getHolder();
        mSurfaceholder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                mDrawing=true;
                //start another thread
              new Thread(VideoSurfaceView.this).start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mDrawing=false;
            }
        });

    }

    public void draw(Path path) {
        canvas=null;

       try {
           canvas = mSurfaceholder.lockCanvas();
           canvas.drawColor(Color.BLUE);
       }catch (Exception o){
           o.printStackTrace();
       }finally {
           if(canvas!=null)
               mSurfaceholder.unlockCanvasAndPost(canvas);
       }

    }

    @Override
    public void run() {
//      注意Path
        while(mDrawing) {
            this.draw(mPath);
        }
    }
}
