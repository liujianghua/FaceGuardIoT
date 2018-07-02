package com.baiytfp.hf.faceguardiot.config;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baiytfp.hf.faceguardiot.utils.PropertiesLoadUtils;

public class AppConfig extends BaseConfig {
	private static final Logger logger = LoggerFactory.getLogger(AppConfig.class);

	/**
	 * Web Api 服务器地址
	 */
	public static String apiHost;
	/**
	 * Web Api访问的access token
	 */
	public static String apiAccessToken;
	/**
	 * 配置文件路径,保存起来
	 */
	public static String configPath;

	//接口APP_ID
//	public static String access_key_id;
//	public static String access_key_secret;
	
	public static void initConfig(String initConfigPath) {
		try {
			Properties properties = PropertiesLoadUtils.loadProperties(initConfigPath);
			apiHost = getProperties(properties, "api.access.host");
			apiAccessToken = getProperties(properties, "api.access.token");
			configPath = initConfigPath;

//			access_key_id = getProperties(properties, "api.access_key_id");
//			access_key_secret = getProperties(properties, "api.access_key_secret");

		} catch (Exception e) {
			logger.error("config init failed ..", e);
			System.exit(-1);
		}
	}
}
