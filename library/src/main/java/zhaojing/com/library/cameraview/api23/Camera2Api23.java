package zhaojing.com.library.cameraview.api23;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.ImageFormat;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.util.Log;

import zhaojing.com.library.cameraview.PreviewImpl;
import zhaojing.com.library.cameraview.Size;
import zhaojing.com.library.cameraview.SizeMap;
import zhaojing.com.library.cameraview.api21.Camera2;

/**
 * Created by zhaojing on 2018-06-16.
 */
@TargetApi(23)
public class Camera2Api23 extends Camera2{


    public Camera2Api23(Callback callback, PreviewImpl preview, Context context) {
        super(callback, preview, context);
    }

    @Override
    protected void collectPictureSizes(SizeMap sizes, StreamConfigurationMap map) {
        // Try to get hi-res output sizes
        Log.i("Camera2Api23","collectPictureSizes");
        android.util.Size[] outputSizes = map.getHighResolutionOutputSizes(ImageFormat.JPEG);
        if (outputSizes != null) {
            for (android.util.Size size : map.getHighResolutionOutputSizes(ImageFormat.JPEG)) {
                sizes.add(new Size(size.getWidth(), size.getHeight()));
            }
        }
        if (sizes.isEmpty()) {
            super.collectPictureSizes(sizes, map);
        }
    }
}
