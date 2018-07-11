package zhaojing.com.bluetoothlibrary.search;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhaojing on 2018-06-21.
 */

public class SearchTask implements Parcelable{

    private int searchType;
    private int searchDuration;

    public SearchTask() {
    }

    protected SearchTask(Parcel in){
        this.searchType=in.readInt();
        this.searchDuration=in.readInt();
    }
    public static final Creator<SearchTask> CREATOR=new Creator<SearchTask>() {
        @Override
        public SearchTask createFromParcel(Parcel source) {
            return new SearchTask(source);
        }

        @Override
        public SearchTask[] newArray(int size) {
            return new SearchTask[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.searchType);
        dest.writeInt(this.searchDuration);
    }


    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    public int getSearchDuration() {
        return searchDuration;
    }

    public void setSearchDuration(int searchDuration) {
        this.searchDuration = searchDuration;
    }
}
