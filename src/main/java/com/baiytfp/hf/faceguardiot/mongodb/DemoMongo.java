package com.baiytfp.hf.faceguardiot.mongodb;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoMongo extends MongoDB {
	private static final Logger logger = LoggerFactory.getLogger(DemoMongo.class);

	private static final DemoMongo singleton = new DemoMongo();

	public static final DemoMongo instance() {
		return singleton;
	}

	private DemoMongo() {
		mongoDatabase = mongoClient.getDatabase(MongoDBName.DEMO);
		logger.debug("init mongo database : " + MongoDBName.DEMO);
	}

}
