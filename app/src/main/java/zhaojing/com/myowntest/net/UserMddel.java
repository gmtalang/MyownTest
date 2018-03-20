package zhaojing.com.myowntest.net;

/**
 * Created by Administrator on 2018-03-19.
 * 返回的userModle
 */
public class UserMddel extends Response<UserMddel>{
    public String name;
    public String relation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }
}
