package zhaojing.com.myowntest.design.prototype;

/**
 * Created by gmtalang on 2018-03-15.
 */
public class LoginSession {
    private User logineduser;//有可能会出现一个线程不安全的情况
    public static volatile LoginSession sLoginSession;
    private LoginSession(){}
    public static LoginSession getInstance(){
        if(sLoginSession==null){
            synchronized (LoginSession.class){
                if(sLoginSession==null){
                   sLoginSession = new LoginSession();
                }
            }
        }

        return sLoginSession;
    }

    /**
     * 对外开放的是原型设计模式，仅仅可以读取，但是不能改写，改写的也只是副本
     * @return
     * @throws CloneNotSupportedException
     */
    public User getLogineduser(){
        User user=null;
        try {
            user= logineduser.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        } return user;
    }

    /**
     * 用户已经登录的信息，不对外开放
     * @param logineduser
     * @throws CloneNotSupportedException
     */
     void setLogineduser(User logineduser) throws CloneNotSupportedException{
        this.logineduser =logineduser;
    }

   public User getUser(){
       return new User();
   }
}
