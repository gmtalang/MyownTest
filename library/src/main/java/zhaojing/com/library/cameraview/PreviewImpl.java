package zhaojing.com.library.cameraview;

import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.View;

/**
 * Created by zhaojing on 2018-06-15.
 */
public abstract class PreviewImpl {

    public interface Callback{
        void onSurfaceChanged();
    }

    private Callback mCallback;
    private int mWidth;
    private int mHeight;

    public void setCallback(Callback callback){
        mCallback=callback;
    }

    public abstract Surface getSurface();
    public abstract View getView();
    public abstract Class getOutputClass();
    public abstract void setDisplayOrientation(int displayOrientation);
    public abstract boolean isReady();

    protected void dispatchSurfaceChanged() {
        mCallback.onSurfaceChanged();
    }

    public SurfaceHolder getSurfaceHolder() {
        return null;
    }

    public Object getSurfaceTexture() {
        return null;
    }


    public void setBufferSize(int width, int height) {
    }

    public void setSize(int width, int height) {
        mWidth = width;
        mHeight = height;
    }

    public int getWidth() {
        return mWidth;
    }

    public int getHeight() {
        return mHeight;
    }
}
