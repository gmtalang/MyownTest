package zhaojing.com.myowntest.net;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
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
	public static <T> List<T> gsonToList(String json){
		List<T> t=null;
		new JsonParser().parse(json).getAsJsonArray();
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


	/***
	 * 复杂响应模型（Map嵌套）
	 * @param json
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> gsonToResponse(String json){
		Response<T> t=null;

		if(wrapper!=null)
			t=wrapper.fromJson(json,new TypeToken<Response<T>>(){}.getType());
		return t;
	}

/////////////////////////////////////////////////////////////////////////////////////////
	//请使用下面的方式进行解析
	////////////////////////////////////////////////////////////////////////////////////
	/***
	 * 解决泛型类型擦除的复杂类型解析（迎合标准的返回json格式）
	 * 这是data生成object的情况
	 * @param json
	 * @param typeof
	 * @param <T>
	 * @return
	 */
	public static <T> Response<T> gsonToResponseEraseObject(String json, final Type typeof){
		Type resultType=new ParameterizedType(){

			@Override
			public Type[] getActualTypeArguments() {
				return new Type[]{typeof};
			}

			@Override
			public Type getOwnerType() {
				return null;
			}

			@Override
			public Type getRawType() {
				return Response.class;
			}
		};

		return wrapper.fromJson(json,resultType);
	}


	/***
	 * 解决类型擦除复杂问题
	 * data为array的情况
	 * @param json
	 * @param typeof
	 * @param <T>
	 * @return
	 */
	public static <T> Response<List<T>> gsonToResponseEraseList(String json,final Type typeof){
		final Type listType=new ParameterizedType() {
			@Override
			public Type[] getActualTypeArguments() {
				return new Type[]{typeof};
			}

			@Override
			public Type getRawType() {
				return List.class;
			}

			@Override
			public Type getOwnerType() {
				return null;
			}
		};
		Type resultType=new ParameterizedType() {
			@Override
			public Type[] getActualTypeArguments() {
				return new Type[]{listType};
			}

			@Override
			public Type getRawType() {
				return Response.class;
			}

			@Override
			public Type getOwnerType() {
				return null;
			}
		};

		return wrapper.fromJson(json,resultType);
	}
}
