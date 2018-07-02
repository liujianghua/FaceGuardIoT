package com.baiytfp.hf.faceguardiot;

import com.baiytfp.hf.faceguardiot.config.AppConfig;
import com.baiytfp.hf.faceguardiot.rest.RestURLMappingManager;
import com.baiytfp.hf.faceguardiot.rest.SerializationProvider;
import org.restexpress.RestExpress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baiytfp.hf.faceguardiot.args.DemoArgs;
import com.baiytfp.hf.faceguardiot.config.Log4jConfig;
import com.baiytfp.hf.faceguardiot.config.MongoConfig;
import com.baiytfp.hf.faceguardiot.mongodb.MongoDB;
import com.baiytfp.hf.faceguardiot.rest.RestObserver;
import com.baiytfp.hf.faceguardiot.rest.RestPostprecessor;
import com.baiytfp.hf.faceguardiot.rest.RestPreprocessor;

public class AppServer extends Thread {
	private static final Logger logger = LoggerFactory.getLogger(AppServer.class);

	private final int port;

	public AppServer(int port) {
		super();
		this.port = port;
	}

	@Override
	public void run() {
		RestExpress server = new RestExpress();
		RestExpress.setDefaultSerializationProvider(new SerializationProvider());
		server.setMaxContentSize(1024 * 1024 * 8);
		server.setKeepAlive(true);
		server.setUseTcpNoDelay(true);
		server.setIoThreadCount(Runtime.getRuntime().availableProcessors() * 2);
		server.setExecutorThreadCount(Runtime.getRuntime().availableProcessors() * 4);
		server.addMessageObserver(new RestObserver());
		server.addPreprocessor(new RestPreprocessor());
		server.addPostprocessor(new RestPostprecessor());
		RestURLMappingManager.instance().initMapping(server);
		server.bind(port);
		server.awaitShutdown();
	}
	  
	/**
	 * nohup java -jar FaceGuardIoT.jar -id=1000 -port=13000 -config=/config.properties -log4j=/log4j.properties > /dev/null 2>&1 &
	 * 启动参数为
	 * -id=1000 -port=13000 -config=/config.properties -log4j=/log4j.properties
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("应用服务启动...");

		DemoArgs.load(args);

		Log4jConfig.configLog4j(DemoArgs.log4jPath);
		AppConfig.initConfig(DemoArgs.configPath);
		logger.info("system init.....");
		
		MongoConfig.initConfig(DemoArgs.configPath);
		MongoDB.init();
		logger.info("mongodb init.....");
		AppServer server = new AppServer(DemoArgs.port);
		server.start();
		logger.info("demo server start up......");
	}
}
