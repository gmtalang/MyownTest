package zhaojing.com.library.cameraview.api9;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import zhaojing.com.library.R;
import zhaojing.com.library.cameraview.PreviewImpl;

/**
 * http://www.jcodecraeer.com/a/anzhuokaifa/androidkaifa/2012/1201/656.html  surfaceView的经典解释
 *
 * Created by zhaojing on 2018-06-15.
 * SURFACE_TYPE_NORMAL：用RAM缓存原生数据的普通Surface
 * SURFACE_TYPE_HARDWARE：适用于DMA(Direct memory access )引擎和硬件加速的Surface
 * SURFACE_TYPE_GPU：适用于GPU加速的Surface
 * SURFACE_TYPE_PUSH_BUFFERS：表明该Surface不包含原生数据，Surface用到的数据由其他对象提供，
 * 在Camera图像预览中就使用该类型的Surface，有Camera负责提供给预览Surface数据，这样图像预览会比较流畅。
 * 如果设置这种类型则就不能调用lockCanvas来获取Canvas对象了。
 *
 */
public class SurfaceViewPreview extends PreviewImpl{

    final SurfaceView mSurfaceView;


    public SurfaceViewPreview(Context context,ViewGroup parent){
        final View view = View.inflate(context, R.layout.surface_view,parent);
        mSurfaceView=(SurfaceView)view.findViewById(R.id.surface_view);
        final SurfaceHolder holder=mSurfaceView.getHolder();

//        SURFACE_TYPE_PUSH_BUFFERS 不合法会有问题
//        在绘制线程中必须先合法的获取Surface才能开始绘制内容，在SurfaceHolder.Callback.surfaceCreated() 和SurfaceHolder.Callback.surfaceDestroyed()之间的状态为合法的，另外在Surface类型为SURFACE_TYPE_PUSH_BUFFERS时候是不合法的。
//        额外的绘制线程会消耗系统的资源，在使用SurfaceView的时候要注意这点。
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                setSize(width,height);
                if (!ViewCompat.isInLayout(mSurfaceView)) {
                    dispatchSurfaceChanged();
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                setSize(0,0);
            }
        });
    }


    @Override
    public SurfaceHolder getSurfaceHolder() {
        return mSurfaceView.getHolder();
    }

    @Override
    public Object getSurfaceTexture() {
        return super.getSurfaceTexture();
    }

//    为什么buffer没用
//
//    @Override
//    public void setBufferSize(int width, int height) {
//        super.setBufferSize(width, height);
//    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
    }


    /////////////////////////////////////实现抽象函数////////////////////


    @Override
    public Surface getSurface() {
        return getSurfaceHolder().getSurface();
    }

    @Override
    public View getView() {
        return mSurfaceView;
    }

    @Override
    public Class getOutputClass() {
        return SurfaceHolder.class;
    }

    @Override
    public void setDisplayOrientation(int displayOrientation) {

    }

    @Override
    public boolean isReady() {
        return getWidth() != 0 && getHeight() != 0;
    }
}
