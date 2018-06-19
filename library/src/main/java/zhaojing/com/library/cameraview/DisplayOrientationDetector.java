package zhaojing.com.library.cameraview;

import android.content.Context;
import android.util.SparseIntArray;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;

/**
 * Created by zhaojing on 2018-06-15.
 */
public abstract class DisplayOrientationDetector {

    private final OrientationEventListener mOrientationEventListener;
    //用sparseIntArray提高内存的效率
    static final SparseIntArray DISPALY_ORIENTATION = new SparseIntArray();//初始的时候是10个

    static {
        DISPALY_ORIENTATION.put(Surface.ROTATION_0, 0);
        DISPALY_ORIENTATION.put(Surface.ROTATION_90, 90);
        DISPALY_ORIENTATION.put(Surface.ROTATION_180, 180);
        DISPALY_ORIENTATION.put(Surface.ROTATION_270, 270);
    }

    Display mDisplay;
    private int mLastKnowDisplayOrientation = 0;

    public DisplayOrientationDetector(Context context) {
        mOrientationEventListener = new OrientationEventListener(context) {
            // -1(invalid)
            private int mLastKnownRotation = -1;

            @Override
            public void onOrientationChanged(int orientation) {

                if (orientation == OrientationEventListener.ORIENTATION_UNKNOWN ||
                        mDisplay == null) {
                    return;
                }
                final int rotation = mDisplay.getRotation();
                if (mLastKnownRotation != rotation) {
                    mLastKnownRotation = rotation;
                    dispatchOnDisplayOrientationChanged(DISPALY_ORIENTATION.get(rotation));
                }

            }
        };
    }

    /**
     * 使能旋转监听
     *
     * @param display
     */
    public void enable(Display display) {
        mDisplay = display;
        mOrientationEventListener.enable();
        dispatchOnDisplayOrientationChanged(DISPALY_ORIENTATION.get(display.getRotation()));
    }

    /**
     * 关闭旋转监听
     */
    public void disable() {
        mOrientationEventListener.disable();
        mDisplay = null;
    }

    /**
     * 分发屏幕旋转处理事件
     *
     * @param displayOrientation
     */
    public void dispatchOnDisplayOrientationChanged(int displayOrientation) {

        mLastKnowDisplayOrientation = displayOrientation;
        onDisplayOrientationChanged(mLastKnowDisplayOrientation);
    }

    /**
     * 当屏幕发生旋转的时候自行处理
     *
     * @param displayOrientation
     */
    public abstract void onDisplayOrientationChanged(int displayOrientation);

    public int getLastKnownDisplayOrientation() {
        return mLastKnowDisplayOrientation;
    }
}
