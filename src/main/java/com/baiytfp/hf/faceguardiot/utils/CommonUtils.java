package com.baiytfp.hf.faceguardiot.utils;

import com.alibaba.fastjson.JSONObject;
import org.restexpress.Request;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

public class CommonUtils {
	
	
	
	
	public static final String RANDOM_CODE_KEY="$_RANDOM_CODE";
	
	public static boolean isEmpty(Object obj) {
		return null == obj;
	}
	
	public static boolean isEmpty(String str ) {
		return isEmpty( str ,true);
	}
	
	public static boolean isEmpty(String str ,boolean noBlank) {
		if( null == str ) return true;
		if( noBlank && 1 > str.trim().length() ) return true;
		return  str.length() == 0 ;
	}

	public static boolean isEmpty(Object[] array) {
		if( null == array ||  1 > array.length ) return true;
		return false;
	}

	public static boolean isEmpty(List<?> list) {
		if( null == list ||  1 > list.size() ) return true;
		return false;
	}
	
	public static boolean isEmpty(Map<?,?> map) {
		if( null == map || null == map.keySet() || 1 > map.keySet().size() ) return true;
		return false;
	}
	
	public static String[] split(String str, String separateStr) {
		if(null == str) return null;
		if(!isEmpty(separateStr) && str.contains(separateStr)) {
			return str.split(separateStr);
		}
		return new String[] {str};
	}
	
	
	public static JSONObject paseJson(Request request) throws Exception {
		BufferedReader br = null;
		StringBuffer json = new StringBuffer();
		try {
			br = new BufferedReader(new InputStreamReader(request.getBodyAsStream(), "utf-8"));
			String tmp = null;
			while(null != (tmp=br.readLine())) {
				json.append(tmp);
			}
		}catch (Exception e) {
			throw e;
		}finally {
			if(br!=null) br.close();
		}
		return JSONObject.parseObject(json.toString());
	}

	

}
