package zhaojing.com.myowntest.net;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class WrapperGson {
	
	private static Gson wrapper;
	private WrapperGson(){}
	
	static{
		if(wrapper==null){
			wrapper=new Gson();
		}
	}
	/*
	 * 转成json字符串
	 * **/
	public static String gsonToString(Object object){
		String gsonString=null;
		if(wrapper!=null)
			gsonString=wrapper.toJson(object);
		return gsonString;
		
	}
	/*
	 * 转成bean
	 * **/
	public static <T> T gsonTobean(String json,Class<T> clazz){
		T t =null;
		if(wrapper!=null)
				t=wrapper.fromJson(json, clazz);
		return t;
	}
	/*
	 * 转成List
	 * **/
	public static <T> List<T> gsonToList(String json,Class<T> clazz){
		List<T> t=null;
		if(wrapper!=null)
				t=wrapper.fromJson(json,new TypeToken<List<T>>(){}.getType());
		return t;
	}

	/*
	 * 转成 Map
	 * **/
	public static <T> Map<String,T> gsonToMap(String json){
		Map<String,T> t=null;
		if(wrapper!=null)
			t=wrapper.fromJson(json, new TypeToken<Map<String,T>>(){}.getType());
			
			return t;
	}

	/*
	 * 转成List Map
	 * **/
	public static <T> List<Map<String,T>> gsonToListMap(String json){
		List<Map<String,T>> t =null;
		if(wrapper!=null)
			t=wrapper.fromJson(json, new TypeToken<List<Map<String,T>>>(){}.getType());
		return t;
		}
}
