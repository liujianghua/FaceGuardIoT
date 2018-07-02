package com.baiytfp.hf.faceguardiot.utils;

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Base64编码解码处理
 * 使用org.apache.commons的Base64技术
 * @since 2.1.0
 * @author ChenJiaMing 20171226
 *
 */
public class HFBase64 {
	
	//默认编码
	private final static String DEFAULT_CHARSET = "UTF-8";
	
	private final Base64 base64 = new Base64();
	
	// 定义HFBase64工具类实例变量，采用懒汉单例模式，使用volatile保证线程可见性
	private static volatile HFBase64 inst;
	
	public static HFBase64 getInstance(){
		// 不直接使用同步加锁，先判断是否为null，不为null直接返回，以提高访问性能
		if(inst == null){
			synchronized (HFBase64.class) {
				// 因为先判断再进入同步代码块，所以进入同步代码块需要再次判断，不然同样会引发线程安全问题
				inst = inst == null 
						? new HFBase64() 
						: inst;
			}
		}
		return inst;
	}
	
	/**
	 * 
	 * 指定编码格式进行Base64编码
	 * @param text
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String encode(String text, String charset) throws UnsupportedEncodingException{
		String result = "";
		if(text != null && !"".equals(text)){
			result = new String(base64.encode(text.getBytes(charset)), charset);
		}
		return result;
	}
	
	/**
	 * 默认编码进行Base64编码
	 * @param text
	 * @return
	 */
	public String encode(String text){
		String result = "";
		try {
			result = encode(text, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
	/**
	 * 
	 * 指定编码格式进行Base64解码
	 * @param text
	 * @param charset
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String decode(String text, String charset) throws UnsupportedEncodingException{
		String result = "";
		if(text != null && !"".equals(text)){
			result = new String(base64.decode(text.getBytes(charset)), charset);
		}
		return result;
	}
	
	/**
	 * 默认编码进行Base64解码
	 * @param text
	 * @return
	 */
	public String decode(String text){
		String result = "";
		try {
			result = decode(text, DEFAULT_CHARSET);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		return result;
	}
	
}
