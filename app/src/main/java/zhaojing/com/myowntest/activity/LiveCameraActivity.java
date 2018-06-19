package zhaojing.com.myowntest.activity;

import android.app.Activity;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.TextureView;

import java.io.IOException;

/**
 * Created by Administrator on 2018-04-08.
 */
public class LiveCameraActivity extends Activity implements TextureView.SurfaceTextureListener {

    private TextureView textureView;
    private Camera camera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textureView=new TextureView(this);
        textureView.setSurfaceTextureListener(this);
        textureView.setAlpha(0.5f);
        setContentView(textureView);
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }

    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        camera=Camera.open();

        try {
            camera.setPreviewTexture(surface);
            camera.startPreview();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {

        camera.stopPreview();
        camera.release();
        return true;
    }
}
