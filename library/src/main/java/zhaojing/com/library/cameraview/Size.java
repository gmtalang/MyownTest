package zhaojing.com.library.cameraview;

/**
 * Created by zhaojing on 2018-06-15.
 */
public class Size implements Comparable<Size> {

    private final int mWidth;
    private final int mHeight;

    public Size(int width, int height){
       mHeight=height;
        mWidth=width;
    }

    public int getmWidth() {
        return mWidth;
    }

    public int getmHeight() {
        return mHeight;
    }

    @Override
    public int compareTo(Size another) {
        return mWidth * mHeight - another.mWidth * another.mHeight;
    }

    @Override
    public String toString() {
        return mWidth + "*" + mHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof Size) {
            Size size = (Size) o;
            return mWidth == size.mWidth && mHeight == size.mHeight;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // assuming most sizes are <2^16, doing a rotate will give us perfect hashing
        return mHeight ^ ((mWidth << (Integer.SIZE / 2)) | (mWidth >>> (Integer.SIZE / 2)));
    }
}
