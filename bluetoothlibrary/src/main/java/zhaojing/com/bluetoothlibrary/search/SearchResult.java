package zhaojing.com.bluetoothlibrary.search;
import android.bluetooth.BluetoothDevice;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

/**
 * Created by zhaojing on 2018-06-21.
 */

public class SearchResult implements Parcelable{
    public BluetoothDevice device;
    public int rssi;
    public byte[] scanRecord;

    public SearchResult(BluetoothDevice device, int rssi, byte[] scanRecord) {
        this.device = device;
        this.rssi = rssi;
        this.scanRecord = scanRecord;
    }

    public SearchResult(Parcel in){
        this.device=in.readParcelable(BluetoothDevice.class.getClassLoader());
        this.rssi=in.readInt();
        this.scanRecord=in.createByteArray();

    }

    public SearchResult(BluetoothDevice device){

        this(device,0,null);

    }

    public String getName(){
        String name = device.getName();
        return TextUtils.isEmpty(name)?"NULL":name;
    }

    public String getAddress(){
        return device!=null?device.getAddress():"";
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.device,0);
        dest.writeInt(this.rssi);
        dest.writeByteArray(this.scanRecord);
    }

    @Override
    public String toString() {
        StringBuilder sb=new StringBuilder();
        sb.append(",mac="+device.getAddress());
        return sb.toString();
    }

    public static final Parcelable.Creator<SearchResult> CREATOR=new Parcelable.Creator<SearchResult>() {
        @Override
        public SearchResult createFromParcel(Parcel source) {
            return new SearchResult(source);
        }

        @Override
        public SearchResult[] newArray(int size) {
            return new SearchResult[size];
        }
    };

    @Override
    public int hashCode() {
        return device.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        SearchResult that = (SearchResult) obj;

        return device.equals(that.device);
    }
}
