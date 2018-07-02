package com.baiytfp.hf.faceguardiot.utils;

import com.alibaba.fastjson.JSONObject;
import okhttp3.*;

import java.io.IOException;

public class HttpUtils {
	
	public static Client client = new Client();
	
	public static String postJson(String url , JSONObject json) throws IOException {
		return client.postJson(url, json);
	}
	
	public static String putJson(String url , JSONObject json) throws IOException {
		return client.putJson(url, json);
	}
	
	public static void useDebug(boolean debug) {
		client.isDebug = debug;
	}
	
	
	private static class Client{
		
		private OkHttpClient client = new OkHttpClient();
		
		private boolean isDebug = false;
		
		public Client() {
			client = client.newBuilder()
					.followRedirects(true).followSslRedirects(true)
					.build();
		}
		
		
		private  void debug(String url , String paramStr , int code, String rs) {
			System.out.println( !isDebug ? "" : "url ====> "+url);
			System.out.println( !isDebug ? "" : "params ====> "+paramStr);
			System.out.println( !isDebug ? "" : "statusCode ===> "+ code );
			System.out.println( !isDebug ? "" : " returnRs ====> " + rs );
		}
		
		
		public String postJson(String url , JSONObject json) throws IOException {
			
			if( null == json ) return null; 
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, json.toJSONString());
			
			Request request = new Request.Builder()
			  .url(url).post(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "f2acaaf5-fe78-474d-9fbc-64ada9abbff7")
			  .build();
			Response response = client.newCall(request).execute();
			String text = response.body().string();
			debug(url, json.toJSONString(), response.code(), text);
			if( response.isSuccessful() ) {
				return text;
			}
			return null;
		}
		
		public String putJson(String url , JSONObject json) throws IOException {
			
			if( null == json ) return null; 
			
			OkHttpClient client = new OkHttpClient();
			
			MediaType mediaType = MediaType.parse("application/json");
			RequestBody body = RequestBody.create(mediaType, json.toJSONString());
			
			Request request = new Request.Builder()
			  .url(url).put(body)
			  .addHeader("Content-Type", "application/json")
			  .addHeader("Cache-Control", "no-cache")
			  .addHeader("Postman-Token", "f2acaaf5-fe78-474d-9fbc-64ada9abbff7")
			  .build();
			Response response = client.newCall(request).execute();
			String text = response.body().string();
			debug(url, json.toJSONString(), response.code(), text);
			if( response.isSuccessful() ) {
				return  text;
			}
			return null;
		}
		
	}

}
