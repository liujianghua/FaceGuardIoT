package com.baiytfp.hf.faceguardiot.utils;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {
	
	//统一使用 UTF-8 进行 base64编码，保证与js的编码生成一致；
	public static String CHARSET = "UTF-8";

	//普通base64
	public static String encode(String str) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();
		return new String(base64.encodeBase64(str.getBytes(CHARSET)), CHARSET);
	}

	//URL base64 (因为在url传输中，+号代码空格，导致传输会出问题)
	public static String encodeURI(String str) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();	//url safe
		return new String(base64.encodeBase64URLSafe(str.getBytes(CHARSET)), CHARSET);
	}
	public static String encodeURI(byte[] byteArr) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();	//url safe
		return new String(base64.encodeBase64URLSafe(byteArr), CHARSET);
	}

	// 无论是普通base64还是url base64，解密过程是一样的；
	public static String decode(String str) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();
		return new String(base64.decodeBase64(str.getBytes(CHARSET)), CHARSET);
	}

	public static String encode(byte[] byteArr) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();
		return new String(base64.encodeBase64(byteArr), CHARSET);
	}

	public static byte[] decodeToByte(String str) throws Exception {
		org.apache.commons.codec.binary.Base64 base64 = new Base64();
		return base64.decodeBase64(str);
	}
}
