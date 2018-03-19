package zhaojing.com.myowntest.net;

import java.io.Serializable;

/**
 * Created by Administrator on 2018-03-19.
 * 登录用user
 */
public class User implements Serializable{


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", relation='" + relation + '\'' +
                '}';
    }

    private String name;
    private String pwd;
    private String relation;

    public User(String name, String pwd,String relation) {
        this.name = name;
        this.pwd = pwd;
        this.relation=relation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
