package zhaojing.com.library.cameraview;


import android.support.v4.util.ArrayMap;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by zhaojing on 2018-06-15.
 */
public class SizeMap {

    private final ArrayMap<AspectRatio, SortedSet<Size>> mRatios = new ArrayMap<>();


    public boolean add(Size size) {
        for (AspectRatio ratio : mRatios.keySet()) {
            if (ratio.matches(size)) {
                final SortedSet<Size> sizes = mRatios.get(ratio);
                if (sizes.contains(size)) {
                    return false;
                } else {
                    sizes.add(size);
                    return true;
                }
            }
        }
        // None of the existing ratio matches the provided size; add a new key
        SortedSet<Size> sizes = new TreeSet<>();
        sizes.add(size);
        mRatios.put(AspectRatio.of(size.getmWidth(), size.getmHeight()), sizes);
        return true;
    }


    public void remove(AspectRatio ratio) {
        mRatios.remove(ratio);
    }

    public Set<AspectRatio> ratios() {
        return mRatios.keySet();
    }

    public SortedSet<Size> sizes(AspectRatio ratio) {
        return mRatios.get(ratio);
    }

    public void clear() {
        mRatios.clear();
    }

    public boolean isEmpty() {
        return mRatios.isEmpty();
    }
}
