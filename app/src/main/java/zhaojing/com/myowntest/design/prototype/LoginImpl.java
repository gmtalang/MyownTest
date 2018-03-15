package zhaojing.com.myowntest.design.prototype;

/**
 * Created by gmtalang on 2018-03-15.
 * 登录实现
 */
public class LoginImpl implements Login {


    @Override
    public void login() {
        //登录到服务器，获取到用户信息
        User logineduser = new User();
        logineduser.age = 22;
        logineduser.name = "xiaxia";
        logineduser.address = new Address("青岛", "山东", "街道");
        //登陆完之后

        //保存本地信息
        try {
            LoginSession.getInstance().setLogineduser(logineduser);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } finally {
        }
    }
}
