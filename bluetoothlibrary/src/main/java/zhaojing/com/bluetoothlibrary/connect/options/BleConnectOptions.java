package zhaojing.com.bluetoothlibrary.connect.options;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * AUTHOR：Created by Administrator on 2018-06-22 11:27
 * EMAIL:  2910763715@qq.com
 */

public class BleConnectOptions implements Parcelable{

    private int connectRetry;
    private int serviceDiscoverRetry;
    private int connectTimeout;
    private int serviceDiscoveryTimeout;

    public static class Builder {
        private static final int DEFAULT_CONNECT_RETRY = 0;
        private static final int DEFAULT_SERVICE_DISCOVER_RETRY = 0;
        private static final int DEFAULT_CONNECT_TIMEOUT = 30000;
        private static final int DEFAULT_SERVICE_DISCOVER_TIMEOUT = 30000;
        private int connectRetry = DEFAULT_CONNECT_RETRY;

        private int serviceDiscoverRetry = DEFAULT_SERVICE_DISCOVER_RETRY;

        private int connectTimeout = DEFAULT_CONNECT_TIMEOUT;

        private int serviceDiscoverTimeout = DEFAULT_SERVICE_DISCOVER_TIMEOUT;

        public Builder setConnectRetry(int retry) {
            connectRetry = retry;
            return this;
        }

        public Builder setServiceDiscoverRetry(int retry) {
            serviceDiscoverRetry = retry;
            return this;
        }

        public Builder setConnectTimeout(int timeout) {
            connectTimeout = timeout;
            return this;
        }

        public Builder setServiceDiscoverTimeout(int timeout) {
            serviceDiscoverTimeout = timeout;
            return this;
        }

        public BleConnectOptions build() {
            return new BleConnectOptions(this);
        }
    }



    public BleConnectOptions(Builder builder) {
        this.connectRetry = builder.connectRetry;
        this.serviceDiscoverRetry = builder.serviceDiscoverRetry;
        this.connectTimeout = builder.connectTimeout;
        this.serviceDiscoveryTimeout = builder.serviceDiscoverTimeout;
    }


    protected BleConnectOptions(Parcel in){

        this.connectRetry=in.readInt();
        this.serviceDiscoverRetry=in.readInt();
        this.connectTimeout=in.readInt();
        this.serviceDiscoveryTimeout=in.readInt();
    }

    public static final Creator<BleConnectOptions> CREATOR=new Creator<BleConnectOptions>() {
        @Override
        public BleConnectOptions createFromParcel(Parcel source) {
            return new BleConnectOptions(source);
        }

        @Override
        public BleConnectOptions[] newArray(int size) {
            return new BleConnectOptions[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(connectRetry);
        dest.writeInt(serviceDiscoverRetry);
        dest.writeInt(connectTimeout);
        dest.writeInt(serviceDiscoveryTimeout);
    }

    @Override
    public String toString() {
        return "BleConnectOptions{" +
                "connectRetry=" + connectRetry +
                ", serviceDiscoverRetry=" + serviceDiscoverRetry +
                ", connectTimeout=" + connectTimeout +
                ", serviceDiscoverTimeout=" + serviceDiscoveryTimeout +
                '}';
    }

    public int getConnectRetry() {
        return connectRetry;
    }

    public void setConnectRetry(int connectRetry) {
        this.connectRetry = connectRetry;
    }

    public int getServiceDiscoverRetry() {
        return serviceDiscoverRetry;
    }

    public void setServiceDiscoverRetry(int serviceDiscoverRetry) {
        this.serviceDiscoverRetry = serviceDiscoverRetry;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getServiceDiscoverTimeout() {
        return this.serviceDiscoveryTimeout;
    }

    public void setServiceDiscoverTimeout(int serviceDiscoverTimeout) {
        this.serviceDiscoveryTimeout = serviceDiscoverTimeout;
    }
}
