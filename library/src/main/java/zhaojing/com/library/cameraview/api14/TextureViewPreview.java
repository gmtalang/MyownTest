package zhaojing.com.library.cameraview.api14;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.graphics.SurfaceTexture;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import zhaojing.com.library.R;
import zhaojing.com.library.cameraview.PreviewImpl;

/**
 * Created by zhaojing on 2018-06-15.
 */
@TargetApi(14)
public class TextureViewPreview extends PreviewImpl {

    private final TextureView mTextureView;
    private int mDisplayOrientation;

    public TextureViewPreview(Context context,ViewGroup parent){
        final View view=View.inflate(context, R.layout.texture_view,parent);
        mTextureView=(TextureView)view.findViewById(R.id.texture_view);
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener(){

            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {

                setSize(width,height);
                configureTransform();
                dispatchSurfaceChanged();
            }

            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

                setSize(width,height);
                configureTransform();
                dispatchSurfaceChanged();
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                setSize(0,0);
                return true;//注意究竟是true还是false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surface) {

            }
        });
    }

    @Override
    public void setBufferSize(int width, int height) {
        mTextureView.getSurfaceTexture().setDefaultBufferSize(width,height);
    }

    @Override
    public Surface getSurface() {
        return new Surface(mTextureView.getSurfaceTexture());
    }

    @Override
    public View getView() {
        return mTextureView;
    }

    @Override
    public Class getOutputClass() {
        return SurfaceTexture.class;
    }

    @Override
    public void setDisplayOrientation(int displayOrientation) {
        mDisplayOrientation=displayOrientation;
        configureTransform();
    }

    @Override
    public boolean isReady() {
        return mTextureView.getSurfaceTexture() != null;
    }


    void configureTransform() {
        Matrix matrix = new Matrix();
        if (mDisplayOrientation % 180 == 90) {
            final int width = getWidth();
            final int height = getHeight();
            // Rotate the camera preview when the screen is landscape.
            matrix.setPolyToPoly(
                    new float[]{
                            0.f, 0.f, // top left
                            width, 0.f, // top right
                            0.f, height, // bottom left
                            width, height, // bottom right
                    }, 0,
                    mDisplayOrientation == 90 ?
                            // Clockwise
                            new float[]{
                                    0.f, height, // top left
                                    0.f, 0.f, // top right
                                    width, height, // bottom left
                                    width, 0.f, // bottom right
                            } : // mDisplayOrientation == 270
                            // Counter-clockwise
                            new float[]{
                                    width, 0.f, // top left
                                    width, height, // top right
                                    0.f, 0.f, // bottom left
                                    0.f, height, // bottom right
                            }, 0,
                    4);
        } else if (mDisplayOrientation == 180) {
            matrix.postRotate(180, getWidth() / 2, getHeight() / 2);
        }
        mTextureView.setTransform(matrix);
    }
}
