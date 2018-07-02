package com.baiytfp.hf.faceguardiot;

import com.alibaba.fastjson.JSONObject;
import com.baiytfp.hf.faceguardiot.utils.ApiUtil;
import com.baiytfp.hf.faceguardiot.utils.Base64Util;
import com.baiytfp.hf.faceguardiot.utils.HMACSHA1;
import com.baiytfp.hf.faceguardiot.utils.HttpUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class IoTTest {

	public static void main(String[] args) throws Exception {

		//门禁上传
		pushAccessDoor();

		//设备上传
		pushDevice();

		//安防上传
		pushDetectEvent();
	}


	//推送识别记录
	public static void pushAccessDoor() throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		//人脸门禁测试数据-------------------start----------------------------
		data.put("id","0009");
		data.put("access_key_id","faceguard");
		data.put("online_state","1");
		data.put("device_state", "2");
		data.put("open_type", "1");
		data.put("open_desc", "人脸开门");
		data.put("open_time", "2018-10-09T08:15:40.843Z");
		data.put("user_id", "00005");
		data.put("user_name", "测试1");
		data.put("user_picture", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3282403731,2457621927&fm=27&gp=0.jpg");

		data.put("signature", generateSignature(data));


		System.out.println(getQueryParams(data) + "&signature=" + generateSignature(data));

		JSONObject json = new JSONObject();
		json.putAll(data);
		String rs = HttpUtils.postJson("http://47.97.112.224:13000/access", json);
//		String rs = HttpUtils.postJson("http://127.0.0.1:8887/access", json);


		System.out.println(rs);
	}


	//上传设备信息
	public static void pushDevice() throws Exception {

		Map<String,Object> data = new HashMap<String,Object>();
		//人脸门禁测试数据-------------------start----------------------------
		data.put("id","0006");
		data.put("access_key_id","faceguard");
		data.put("project_id","test123");
		data.put("project_name", "测试的门禁设备1");
		data.put("device_type", "3");

//		data.put("user_picture", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3282403731,2457621927&fm=27&gp=0.jpg");

		data.put("signature", generateSignature(data));

		System.out.println(getQueryParams(data) + "&signature=" + generateSignature(data));

		JSONObject json = new JSONObject();
		json.putAll(data);
		String rs = HttpUtils.postJson("http://47.97.112.224:13000/deviceinit", json);
//		String rs = HttpUtils.postJson("http://127.0.0.1:8887/deviceinit", json);


		System.out.println(rs);
	}

	//推送安防信息
	public static void pushDetectEvent() throws Exception {
		

		Map<String, Object> data = new HashMap<String, Object>();
		//人脸门禁测试数据-------------------start----------------------------
		data.put("id", "00011");
		data.put("access_key_id", "faceguard");
		data.put("event_id", "00011");
		data.put("event_time", "2018-10-09T08:15:40.843Z");
		data.put("user_picture", "xxx1");
		data.put("camera_id", "00011");
		data.put("user_id", "00021");
		data.put("user_name", "测试11");
//		data.put("user_picture", "https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=3282403731,2457621927&fm=27&gp=0.jpg");

		data.put("signature", generateSignature(data));

		System.out.println(getQueryParams(data) + "&signature=" + generateSignature(data));

		JSONObject json = new JSONObject();
		json.putAll(data);
		String rs = HttpUtils.postJson("http://47.97.112.224:13000/detectevent", json);
//		String rs = HttpUtils.postJson("http://127.0.0.1:8887/detectevent", json);

		System.out.println(rs);
	}

	private static String getQueryParams(Map dataMap) throws Exception {
		Map map = ApiUtil.sortMapByKey(dataMap);

		// 组装成 a=xxx&b=xxx&c=xxx 格式
		StringBuffer queryString = new StringBuffer();
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) entries.next();
			if (!"signature".equals(ApiUtil.encodeUrl(entry.getKey())) && entry.getValue() != null) {
				queryString.append("&").append(ApiUtil.encodeUrl(entry.getKey())).append("=")
						.append(ApiUtil.encodeUrl(entry.getValue()));
			}
		}

		return queryString.deleteCharAt(0).toString();
	}

	private static String generateSignature(Map dataMap) throws Exception {

		boolean result = false;

		// 对接收到的所有参数进行排序
		Map map = ApiUtil.sortMapByKey(dataMap);

		// 组装成 a=xxx&b=xxx&c=xxx 格式
		StringBuffer queryString = new StringBuffer();
		Iterator entries = map.entrySet().iterator();
		while (entries.hasNext()) {
			Map.Entry<String, String> entry = (Map.Entry<String, String>) entries.next();
			if (!"signature".equals(ApiUtil.encodeUrl(entry.getKey())) && entry.getValue() != null) {
				queryString.append("&").append(ApiUtil.encodeUrl(entry.getKey())).append("=")
						.append(ApiUtil.encodeUrl(entry.getValue()));
			}
		}

		if (queryString.length() > 0) {
			// 使用 HMAC-SHA1算法进行签名 ，并转化 base64 字符串
			String signature = Base64Util.encodeURI(HMACSHA1.HmacSHA1Encrypt(queryString.deleteCharAt(0).toString(), "kyjkHHTKLKUKa2Hk"));


			return signature;
		}
		return "";
	}

}
