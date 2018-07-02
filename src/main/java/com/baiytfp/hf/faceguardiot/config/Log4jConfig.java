package com.baiytfp.hf.faceguardiot.config;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.baiytfp.hf.faceguardiot.utils.PropertiesLoadUtils;

public class Log4jConfig {

	public static void configLog4j(String properties_path) {
		try {
			Properties props = PropertiesLoadUtils.loadProperties(properties_path);
			PropertyConfigurator.configure(props);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(0);
		}
	}
}
