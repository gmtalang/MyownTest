package zhaojing.com.library.cameraview;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.util.SparseArrayCompat;

/**
 * Created by zhaojing on 2018-06-15.
 *
 *场景的比率
 */
public class AspectRatio implements Comparable<AspectRatio>,Parcelable{
    private final int mX;
    private final int mY;

    private static final SparseArrayCompat<SparseArrayCompat<AspectRatio>> sCache=
            new SparseArrayCompat<>(16);

    private AspectRatio(int x, int y){
        mX=x;
        mY=y;
    }


    public int getmX() {
        return mX;
    }

    public int getmY() {
        return mY;
    }

    public boolean matches(Size size) {
        int gcd = gcd(size.getmWidth(), size.getmHeight());
        int x = size.getmWidth() / gcd;
        int y = size.getmHeight() / gcd;
        return mX == x && mY == y;
    }

    public static AspectRatio of(int x, int y){

        int gcd = gcd(x, y);
        x /= gcd;
        y /= gcd;
        SparseArrayCompat<AspectRatio> arrayX = sCache.get(x);
        if (arrayX == null) {
            AspectRatio ratio = new AspectRatio(x, y);
            arrayX = new SparseArrayCompat<>();
            arrayX.put(y, ratio);
            sCache.put(x, arrayX);
            return ratio;
        } else {
            AspectRatio ratio = arrayX.get(y);
            if (ratio == null) {
                ratio = new AspectRatio(x, y);
                arrayX.put(y, ratio);
            }
            return ratio;
        }
    }

    /**
     * Parse an {@link AspectRatio} from a {@link String} formatted like "4:3".
     *
     * @param s The string representation of the aspect ratio
     * @return The aspect ratio
     * @throws IllegalArgumentException when the format is incorrect.
     */
    public static AspectRatio parse(String s) {
        int position = s.indexOf(':');
        if (position == -1) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + s);
        }
        try {
            int x = Integer.parseInt(s.substring(0, position));
            int y = Integer.parseInt(s.substring(position + 1));
            return AspectRatio.of(x, y);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Malformed aspect ratio: " + s, e);
        }
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int c = b;
            b = a % b;
            a = c;
        }
        return a;
    }

    public AspectRatio inverse() {
        //noinspection SuspiciousNameCombination
        return AspectRatio.of(mY, mX);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (o instanceof AspectRatio) {
            AspectRatio ratio = (AspectRatio) o;
            return mX == ratio.mX && mY == ratio.mY;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // assuming most sizes are <2^16, doing a rotate will give us perfect hashing
        return mY ^ ((mX << (Integer.SIZE / 2)) | (mX >>> (Integer.SIZE / 2)));
    }

    @Override
    public int compareTo(AspectRatio another) {
        if (equals(another)) {
            return 0;
        } else if (toFloat() - another.toFloat() > 0) {
            return 1;
        }
        return -1;
    }


    public float toFloat() {
        return (float) mX / mY;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mX);
        dest.writeInt(mY);
    }

    public static final Parcelable.Creator<AspectRatio> CREATOR=
            new Parcelable.Creator<AspectRatio>(){
                @Override
                public AspectRatio createFromParcel(Parcel source) {
                    int x=source.readInt();
                    int y=source.readInt();
                    return AspectRatio.of(x,y);
                }

                @Override
                public AspectRatio[] newArray(int size) {
                    return new AspectRatio[size];
                }
            };

    @Override
    public String toString() {
        return mX + ":" + mY;
    }
}
