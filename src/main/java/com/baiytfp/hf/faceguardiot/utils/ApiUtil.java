package com.baiytfp.hf.faceguardiot.utils;


import com.baiytfp.hf.faceguardiot.config.AppConfig;
import com.baiytfp.hf.faceguardiot.utils.map.MapKeyComparator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.restexpress.Request;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

public class ApiUtil {
	
	private static String CHARSET = "UTF-8";
	
	private static HashMap ACCESS_KEY_MAP = null;
	
	static {
		ACCESS_KEY_MAP = new HashMap();
		ACCESS_KEY_MAP.put("faceguard", "kyjkHHTKLKUKa2Hk");	//
	}
	
	public static String getAccess_key_secret(String access_key_id) throws Exception {
		return StringUtil.getMapString(ACCESS_KEY_MAP, access_key_id);
	}
	
	
	//判断进来的请求是否合法
	public static boolean validRequest(Map<String, String> paramsMap) throws Exception {

		boolean result = false;

		//对接收到的所有参数进行排序
		Map map = sortMapByKey(paramsMap);

//		Map map = request.getParameterMap();

		//组装成 a=xxx&b=xxx&c=xxx 格式
		StringBuffer queryString = new StringBuffer();
		Iterator entries = map.entrySet().iterator();
	    while (entries.hasNext()){
	    	Map.Entry<String, String> entry = (Map.Entry<String, String>)entries.next();

	    	if(!"signature".equals(encodeUrl(entry.getKey())) && entry.getValue() != null){
	    		queryString.append("&").append(encodeUrl(entry.getKey())).append("=").append(encodeUrl(entry.getValue()));
	    	}
//	    	System.out.println("接收 :" + entry.getKey() + " : " + entry.getValue()[0]);

		}
//		System.out.println("接收 :" + queryString.toString());

//		System.out.println(DateUtil.format(DateUtil.getCurrentDT()) + " 请求 : " + request.getRequestURL() + " ; 参数 : " + queryString +  "&signature=" + RequestUtil.getString(request, "signature"));

	    if(queryString.length() > 0){

	    	 //使用 HMAC-SHA1算法进行签名 ，并转化 base64 字符串
		    String signature = Base64Util.encodeURI(HMACSHA1.HmacSHA1Encrypt(queryString.deleteCharAt(0).toString(), getAccess_key_secret(paramsMap.get("access_key_id"))));

//		    LogUtil.debug(signature + " # " + queryString.toString() + " # " + getAccess_key_secret(RequestUtil.getString(request, "access_key_id", "")));

			System.out.println("签名 :" + signature + " ## " + paramsMap.get("signature"));

		    //判断签名结果是否与传送过来的签名一致，若一致则为合法请求
		    if(signature.equals(paramsMap.get("signature"))){
		    	result = true;
		    }
	    }

		return result;
	}

	//对URL 进行编码
	public static String encodeUrl(String str) throws Exception {
		return URLEncoder.encode(str, CHARSET).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }

	public static Map<String, String> sortMapByKey(Map treeMap) {
		if(treeMap != null && !treeMap.isEmpty()) {
			TreeMap sortMap = new TreeMap(new MapKeyComparator());
			sortMap.putAll(treeMap);
			return sortMap;
		} else {
			return null;
		}
	}

	public static void main(String[] args) {
		try {
			String str = "access_key_id=faceguard&device_state=2&id=0005&online_state=1&open_desc=" + encodeUrl(encodeUrl("测试")) + "&open_time=" + encodeUrl("2014-10-09T08:15:40.843Z") + "&open_type=1&user_id=00002&user_name=" + encodeUrl(encodeUrl("测试员")) + "&user_picture=xxx";
//			String str = "access_key_id=faceguard&device_type=1&id=0003&project_id=test030232&project_name=" + encodeUrl(encodeUrl("测试的门禁设备"));
//			String str = "access_key_id=faceguard&camera_id=camera01&event_id=000012&event_time=" + encodeUrl("2014-10-09T08:15:40.843Z") + "&id=00034&user_id=test003&user_name=" + encodeUrl(encodeUrl("测试员"));


//			System.out.println(str);

//			String str = "access_key_id=faceguard&device_state=2&id=0003&online_state=1&open_desc=%25E6%25B5%258B%25E8%25AF%2595&open_time=2014-10-09T08%253A15%253A40.843Z&open_type=1&user_id=00002&user_name=%25E6%25B5%258B%25E8%25AF%2595%25E5%2591%2598";
//			String str = "access_key_id=faceguard&device_state=2&id=0003&online_state=1&open_desc=%25E6%25B5%258B%25E8%25AF%2595&open_time=2014-10-09T08%3A15%3A40.843Z&open_type=1&user_id=00002&user_name=%25E6%25B5%258B%25E8%25AF%2595%25E5%2591%2598";
			String signature = Base64Util.encodeURI(HMACSHA1.HmacSHA1Encrypt(str, "kyjkHHTKLKUKa2Hk"));


			System.out.println("签名 : " + signature);

//			System.out.println("http://47.97.112.224:13000/access?access_key_id=faceguard&id=0003&online_state=1&device_state=2&open_type=1&open_desc=测试&open_time=2014-10-09T08:15:40.843Z&user_id=00002&user_name=测试员&signature=" + signature);

		}catch (Exception e){
			e.printStackTrace();
		}
	}

}
