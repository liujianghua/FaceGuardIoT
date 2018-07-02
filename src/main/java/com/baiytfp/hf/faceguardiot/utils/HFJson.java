package com.baiytfp.hf.faceguardiot.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Json格式数据序列化与反序列化处理
 * 使用阿里的fastjson技术
 * @since 2.0.9
 * @author ChenJiaMing 20171226
 *
 */
public class HFJson {
	
	// 定义HFJson工具类实例变量，采用懒汉单例模式，使用volatile保证线程可见性
	private static volatile HFJson inst;
	
	// 默认的日期格式
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	private HFJson(){}
	
	public static HFJson getInstance(){
		// 不直接使用同步加锁，先判断是否为null，不为null直接返回，以提高访问性能
		if(inst == null){
			synchronized (HFJson.class) {
				// 因为先判断再进入同步代码块，所以进入同步代码块需要再次判断，不然同样会引发线程安全问题
				inst = inst == null 
						? new HFJson() 
						: inst;
			}
		}
		return inst;
	}
	
	/**
	 * 将对象序列化为字符串，调用者指定日期格式
	 * @param o
	 * @return
	 */
	public String toJsonString(Object o, String dateFormat){
		String result = "";
		if(o != null){
			result = dateFormat != null
					? JSON.toJSONStringWithDateFormat(o, dateFormat)
					: JSON.toJSONStringWithDateFormat(o, HFJson.DATE_FORMAT);
		}
		return result;
	}
	
	/**
	 * 将对象序列化为字符串，默认日期格式为“yyyy-MM-dd HH:mm:ss”
	 * @param o
	 * @return
	 */
	public String toJsonString(Object o){
		String result = "";
		if(o != null){
			result = toJsonString(o, HFJson.DATE_FORMAT);
		}
		return result;
	}
	
	/**
	 * 将字符串或JavaBean或Map转化为JSONObject
	 * @param o
	 * @return
	 */
	public JSONObject parseJsonObject(Object o){
		JSONObject result = null;
		result = o instanceof String
				? JSON.parseObject(String.class.cast(o))
				: JSON.parseObject(toJsonString(o));
		return result;
	}
	
	/**
	 * 从JSONObject中获取值
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public <T> T getValue(JSONObject jsonObject, String key, Class<T> clazz){
		return jsonObject != null
				? jsonObject.getObject(key, clazz)
				: null;
	}
	
	/**
	 * 从JSONObject中获取值（字符串），指定默认值
	 * @param jsonObject
	 * @param key
	 * @param deft
	 * @return
	 */
	public String getString(JSONObject jsonObject, String key, String deft){
		String result = null;
		if(jsonObject == null){
			return result;
		}
		return (result = jsonObject.getString(key)) != null
				? result
				: deft;
	}
	
	/**
	 * 从JSONObject中获取值（字符串）
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public String getString(JSONObject jsonObject, String key){
		return getString(jsonObject, key, null);
	}
	
	/**
	 * 从JSONObject中获取值（int）
	 * @param jsonObject
	 * @param key
	 * @param deft
	 * @return
	 */
	public int getInt(JSONObject jsonObject, String key, int deft){
		if(jsonObject == null){
			return deft;
		}
		int result = 0;
		return (result = jsonObject.getIntValue(key)) != 0
				? result
				: deft;
	}
	
	/**
	 * 从JSONObject中获取值（double）
	 * @param jsonObject
	 * @param key
	 * @param deft
	 * @return
	 */
	public double getDouble(JSONObject jsonObject, String key, double deft){
		if(jsonObject == null){
			return deft;
		}
		double result = 0D;
		return (result = jsonObject.getDoubleValue(key)) != 0D
				? result
				: deft;
	}
	
	/**
	 * 从JSONObject中获取值（float）
	 * @param jsonObject
	 * @param key
	 * @param deft
	 * @return
	 */
	public float getFloat(JSONObject jsonObject, String key, float deft){
		if(jsonObject == null){
			return deft;
		}
		float result = 0F;
		return (result = jsonObject.getFloatValue(key)) != 0F
				? result
				: deft;
	}
	
	/**
	 * 从JSONObject中获取值（Date）
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public Date getDate(JSONObject jsonObject, String key){
		return jsonObject != null
				? jsonObject.getDate(key)
				: null;
	}
	
	/**
	 * 从JSONObject中获取值（Timestamp）
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public Timestamp getTimestamp(JSONObject jsonObject, String key){
		return jsonObject != null
				? jsonObject.getTimestamp(key)
				: null;
	}
	
	/**
	 * 从JSONObject中获取值（Timestamp）
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public JSONObject getJSONObject(JSONObject jsonObject, String key){
		return jsonObject != null
				? jsonObject.getJSONObject(key)
				: null;
	}
	
	/**
	 * 从JSONObject中获取值（JSONArray）
	 * @param jsonObject
	 * @param key
	 * @return
	 */
	public JSONArray getJSONArray(JSONObject jsonObject, String key){
		return jsonObject != null
				? jsonObject.getJSONArray(key)
				: null;
	}
	
	/**
	 * 将字符串或数组或List转化为JSONArray
	 * @param o
	 * @return
	 */
	public JSONArray parseJsonArray(Object o){
		JSONArray result = null;
		result = o instanceof String
				? JSON.parseArray(String.class.cast(o))
				: JSON.parseArray(toJsonString(o));
		return result;
	}
	
	/**
	 * 字符串、Map、JavaBean转化为Map、JavaBean
	 * @param o
	 * @param clazz
	 * @return
	 */
	public <T> T parseObject(Object o, Class<T> clazz){
		T result = null;
		result = o instanceof String
				? JSON.parseObject(String.class.cast(o), clazz)
				: JSON.parseObject(toJsonString(o), clazz);
		return result;
	}
	
	/**
	 * 将字符串、数组转换为List
	 * @param o
	 * @param clazz
	 * @return
	 */
	public <T> List<T> parseList(Object o, Class<T> clazz){
		List<T> result = null;
		result = o instanceof String
				? JSON.parseArray(String.class.cast(o), clazz)
				: JSON.parseArray(toJsonString(o), clazz);
		return result;
	}
	
	/**
	 * 将字符串、数组转换为List
	 * @param o
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> parseList(Object o){
		return parseList(o, Map.class);
	}
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "123");
		map.put("date", new Date());
		System.out.println(HFJson.getInstance().toJsonString(map));
		System.out.println(HFJson.getInstance().toJsonString(map, "yyyy-MM-dd"));
		JSONObject jsonObject = HFJson.getInstance().parseJsonObject(map);
		System.out.println(HFJson.getInstance().getString(jsonObject, "name"));
	}
	
}
