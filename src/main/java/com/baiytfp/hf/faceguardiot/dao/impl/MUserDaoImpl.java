package com.baiytfp.hf.faceguardiot.dao.impl;

import com.baiytfp.hf.faceguardiot.dao.IUserDao;
import com.baiytfp.hf.faceguardiot.datastruct.DUser;
import com.baiytfp.hf.faceguardiot.mongodb.DemoMongo;
import com.baiytfp.hf.faceguardiot.mongodb.MongoCollectionName;
import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;

public class MUserDaoImpl implements IUserDao {

	private static final IUserDao SINGLETON = new MUserDaoImpl();
	
	private MUserDaoImpl(){
	}
	
	public static final IUserDao INSTACNE(){
		return SINGLETON;
	}
	
	@Override
	public DUser findById(String id) throws Exception {
		MongoCollection<Document> collection = DemoMongo.instance().getDatabase().getCollection(MongoCollectionName.USER);
		Bson filter = Filters.eq("_id", id);
		Document doc = collection.find(filter).first();
		if(null != doc){
			String name = doc.getString("name");
			return new DUser(id, name);
		}
		return null;
	}

	@Override
	public void saveUser(DUser user) throws Exception {
		MongoCollection<Document> collection = DemoMongo.instance().getDatabase().getCollection(MongoCollectionName.USER);
		Document doc = new Document();
		doc.append("_id", user.getId());
		doc.append("name", user.getName());
		collection.insertOne(doc);
	}

}
