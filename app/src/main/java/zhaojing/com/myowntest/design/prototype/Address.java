package zhaojing.com.myowntest.design.prototype;

/**
 * Created by gmtalang on 2018-03-15.
 */
public class Address implements Cloneable{
    public String city;
    public String distinct;
    public String street;

    public Address(String city,String distinct,String street){
        this.city=city;
        this.distinct=distinct;
        this.street=street;
    }

    @Override
    protected Address clone(){
        Address address=null;

        try {
            address=(Address)super.clone();
            address.city=this.city;
            address.distinct=this.distinct;
            address.street=this.street;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return address;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
