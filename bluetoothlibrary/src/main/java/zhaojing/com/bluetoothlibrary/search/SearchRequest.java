package zhaojing.com.bluetoothlibrary.search;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import zhaojing.com.bluetoothlibrary.utils.BluetoothUtils;

import static zhaojing.com.bluetoothlibrary.Constants.SEARCH_TYPE_BLE;
import static zhaojing.com.bluetoothlibrary.Constants.SEARCH_TYPE_CLASSIC;

/**
 * Created by zhaojing on 2018-06-21.
 */

public class SearchRequest implements Parcelable {

    private List<SearchTask> tasks;
    public SearchRequest(){}

    protected SearchRequest(Parcel in){
        this.tasks=new ArrayList<SearchTask>();
        in.readTypedList(this.tasks,SearchTask.CREATOR);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(tasks);
    }

    public static final Creator<SearchRequest> CREATOR=new Creator<SearchRequest>() {
        @Override
        public SearchRequest createFromParcel(Parcel source) {
            return new SearchRequest();
        }

        @Override
        public SearchRequest[] newArray(int size) {
            return new SearchRequest[size];
        }
    };

    public List<SearchTask> getTasks() {
        return tasks;
    }

    public void setTasks(List<SearchTask> tasks) {
        this.tasks = tasks;
    }

    public static class Builder{
        private List<SearchTask> tasks;

        public Builder(){
            tasks=new ArrayList<SearchTask>();
        }

        public Builder searchBluetoothLeDevice(int duration){
            if(BluetoothUtils.isBleSupported()){
                SearchTask search=new SearchTask();
                search.setSearchType(SEARCH_TYPE_BLE);
                search.setSearchDuration(duration);
                tasks.add(search);
            }
            return this;
        }

        public Builder searchBluetoothLeDevice(int duration,int times){
            for(int i=0;i<times;i++){
                searchBluetoothLeDevice(duration);
            }
            return this;
        }

        public Builder searchBluetoothClassicDevice(int duration){
            SearchTask search = new SearchTask();
            search.setSearchType(SEARCH_TYPE_CLASSIC);
            search.setSearchDuration(duration);
            tasks.add(search);
            return this;
        }

        public Builder searchBluetoothClassicDevice(int duration,int times){

            for(int i=0;i<times;i++){
                searchBluetoothClassicDevice(duration);
            }
            return this;
        }

        public SearchRequest build(){
            SearchRequest group=new SearchRequest();
            group.setTasks(tasks);
            return group;
        }
    }
}
