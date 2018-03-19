package zhaojing.com.myowntest.net;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * Created by zhaojing on 2018-03-19.
 *各类请求
 */
public class Request {



    static RequestCallback call;

    public  Request(RequestCallback call){
        this.call=call;
    }

    public  void get(String path,String... params){

        path=path+"?"+"name="+params[0]+"&&"+"pwd="+params[1];

        try {
            URL url= new URL(path);
            HttpURLConnection con= (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("GET");
            int statusCode=con.getResponseCode();
            if(statusCode==200){
                String response=streamToString(con.getInputStream());
                ////////////////////////////////////////////////////////////////
//               if(!response.equals(false)) {
//                   User user = new Gson().fromJson(response, User.class);
//                   //写一个回调函数
//                   call.callback(user.getName(), user.getPwd());
//               }else{
//                   call.backfalse();//错误的密码
//               }
                ///////////////////////////////////////////////////////////////Map版本
//                if(!response.equals(false)){
//                    Map<String,String> result=null;
//                    result=WrapperGson.gsonToMap(response);
//                    call.callback(result.get("name"),result.get("relation"));
//                }else{
//                    call.backfalse();
//                }
                ////////////////////////////////////////////////////////////////////Map嵌套
                if(!response.equals(false)){
                   Map<String,Object> model=WrapperGson.gsonToMap(response);
                    User user=WrapperGson.gsonTobean(model.get("data").toString(),User.class);

                    call.callback(user.getName(),user.getPwd(),"xiami");
                }else{
                    call.backfalse();
                }
            } else{
                //其他的问题
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }


    }

    private String streamToString(InputStream stream)throws IOException{
        ByteArrayOutputStream output=new ByteArrayOutputStream();

        int i=-1;
        while((i=stream.read())!=-1){
            output.write(i);
        }
        return output.toString();
    }

}
