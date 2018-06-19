package zhaojing.com.library.cameraview;

import android.view.View;

import java.util.Set;

/**
 * Created by zhaojing  on 2018-06-15.
 */
public abstract class CameraViewImpl {

    public interface Callback{
        void onCameraOpened();

        void onCameraClosed();

        void onPictureTaken(byte[] data);
    }

    protected final Callback mCallback;
    protected final PreviewImpl mPreviewImpl;

    public CameraViewImpl(Callback callback,PreviewImpl preview){

        mCallback=callback;
        mPreviewImpl=preview;

    }

    public View getView() {
        return mPreviewImpl.getView();
    }

    //////////////////////////////////////////////////
    public abstract void setDisplayOrientation(int displayOrientation);
    public abstract void takePicture();
    public abstract void setFlash(int flash);
    public abstract int getFlash();
    public abstract void setAutoFocus(boolean autoFocus);
    public abstract boolean getAutoFocus();
    public abstract boolean setAspectRatio(AspectRatio ratio);
    public abstract AspectRatio getAspectRatio();
    public abstract boolean start();
    public abstract void stop();
    public abstract boolean isCameraOpened();
    public abstract void setFacing(int facing);
    public abstract int getFacing();
    public abstract Set<AspectRatio> getSupportedAspectRatios();
    //////////////////////////////////////////////////

}
