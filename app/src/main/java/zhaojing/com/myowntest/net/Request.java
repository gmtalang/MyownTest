package zhaojing.com.myowntest.net;

import android.content.Entity;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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
               if(!response.equals(false)) {
                   User user = new Gson().fromJson(response, User.class);
                   //写一个回调函数
                   call.callback(user.getName(), user.getPwd());
               }else{
                   call.backfalse();//错误的密码
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
