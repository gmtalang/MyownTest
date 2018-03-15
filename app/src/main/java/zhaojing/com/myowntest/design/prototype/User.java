package zhaojing.com.myowntest.design.prototype;

/**
 * Created by gmtalang on 2018-03-15.
 * 原型设计模式
 */
public class User implements Cloneable{
    public int age;
    public String name;
    public Address address;

    @Override
    protected User clone() throws CloneNotSupportedException {
        User user;
        user=(User)super.clone();
        user.age=this.age;
        user.name=this.name;
        user.address=this.address.clone();
        return user;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
