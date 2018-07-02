package com.baiytfp.hf.faceguardiot.mongodb;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import com.baiytfp.hf.faceguardiot.config.MongoConfig;
import com.baiytfp.hf.faceguardiot.utils.ContainerGetter;
import com.baiytfp.hf.faceguardiot.utils.StringTools;
import com.baiytfp.hf.faceguardiot.utils.tuple.TowTuple;

/**
 * MongoDB操作类
 * 
 */
public abstract class MongoDB {
	private static final Logger logger = LoggerFactory.getLogger(MongoDB.class);
	protected MongoDatabase mongoDatabase;
	protected static MongoClient mongoClient;

	/**
	 * 重复索引主键错误码
	 */
	private static final int ERROR_DUPLICATE_KEY = 11000;

	public static final void init() {
		MongoClientOptions.Builder opt_builder = MongoClientOptions.builder();
		opt_builder.connectionsPerHost(MongoConfig.connectionsPerHost);
		opt_builder.threadsAllowedToBlockForConnectionMultiplier(MongoConfig.threadsAllowedToBlockForConnectionMultiplier);
		opt_builder.connectTimeout(MongoConfig.connectTimeout);
		opt_builder.maxWaitTime(MongoConfig.maxWaitTime);
		opt_builder.socketKeepAlive(MongoConfig.socketKeepAlive);
		opt_builder.socketTimeout(MongoConfig.socketTimeout);
		if (MongoConfig.replicaSet != null) {
			opt_builder.requiredReplicaSetName(MongoConfig.replicaSet);
		}
		List<ServerAddress> seeds = ContainerGetter.arrayList();
		List<MongoCredential> credentials = Arrays.asList(MongoCredential.createScramSha1Credential(MongoConfig.username, "admin",
				MongoConfig.password.toCharArray()));
		List<TowTuple<String, Integer>> hosts = MongoConfig.hosts;
		for (TowTuple<String, Integer> towTuple : hosts) {
			seeds.add(new ServerAddress(towTuple.first, towTuple.second));
		}
		try {
			mongoClient = new MongoClient(seeds, credentials, opt_builder.build());
		} catch (Exception e) {
			logger.error("", e);
			System.exit(-1);
		}
	}

	public MongoDB() {

	}

	public MongoClient getMongoClinet() {
		return mongoClient;
	}

	public MongoDatabase getDatabase() {
		return mongoDatabase;
	}

	/**
	 * 插入一个数据
	 * 
	 * @param collectionName
	 * @param object
	 */
	public void insertIngore(String collectionName, Document document) {
		try {
			MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
			collection.insertOne(document);
		} catch (com.mongodb.MongoWriteException e) {
			// 忽略重复主键
			if (e.getCode() == ERROR_DUPLICATE_KEY) {
				return;
			}
			throw e;
		}
	}

	/**
	 * 指定region插入
	 * 
	 * @param collectionName
	 * @param region
	 * @param document
	 */
	public void insertIngore(int region, String collectionName, Document document) {
		try {
			MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
			collection.insertOne(document);
		} catch (com.mongodb.MongoWriteException e) {
			// 忽略重复主键
			if (e.getCode() == ERROR_DUPLICATE_KEY) {
				return;
			}
			throw e;
		}
	}

	/**
	 * 插入多个数据带region
	 * 
	 * @param collectionName
	 * @param object
	 */
	public void insertMany(String collectionName, List<Document> documentList) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.insertMany(documentList);
	}

	/**
	 * 批量插入
	 * 
	 * @param collectionName
	 * @param documentList
	 */
	public void insertManyIngore(String collectionName, List<Document> documentList) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		try {
			collection.insertMany(documentList);
		} catch (com.mongodb.MongoWriteException e) {
			// 忽略重复主键
			if (e.getCode() == ERROR_DUPLICATE_KEY) {
				return;
			}
			throw e;
		}catch(Exception e ){
			logger.error("", e);
		}
	}

	/**
	 * 指定region插入
	 * 
	 * @param region
	 * @param collectionName
	 * @param documentList
	 */
	public void insertManyIngore(int region, String collectionName, List<Document> documentList) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		try {
			collection.insertMany(documentList);
		} catch (com.mongodb.MongoWriteException e) {
			// 忽略重复主键
			if (e.getCode() == ERROR_DUPLICATE_KEY) {
				return;
			}
			throw e;
		}catch(Exception e ){
			logger.error("", e);
		}
	}

	/**
	 * 
	 * @param collectionName
	 * @param filter
	 * @param update
	 */
	public void update(String collectionName, Document filter, Document update) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.updateOne(filter, new Document().append("$set", update));
	}

	/**
	 * 
	 * @param clazz
	 * @param queryDBObject
	 * @param updateObject
	 *            UpdateObject/BasicDBObject
	 */
	public void findAndModify(String collectionName, Document filter, Document update) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.findOneAndUpdate(filter, new Document().append("$set", update));
	}

	/**
	 * 
	 * @param collectionName
	 * @param queryMap
	 * @param queryField
	 * @return
	 */
	public Document queryOne(String collectionName, Bson filter) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		Document document = collection.find(filter).first();
		if (document == null) {
			return new Document();
		}
		return document;
	}

	/**
	 * 
	 * @param collectionName
	 * @param queryMap
	 * @param queryField
	 * @return
	 */
	public List<Document> queryAll(String collectionName, Bson filter) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		MongoCursor<Document> cursor = null;
		if (null != filter) {
			cursor = collection.find(filter).iterator();
		} else {
			cursor = collection.find().iterator();
		}
		List<Document> list = ContainerGetter.arrayList();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	/**
	 * 查询数据
	 * 
	 * @param collectionName
	 * @param queryMap
	 * @param queryField
	 * @param querySort
	 * @param start
	 * @param limit
	 * @return
	 */

	public List<Document> queryAll(String collectionName, Document filter, int start, int limit) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find(filter).skip(start).limit(limit).iterator();
		List<Document> list = ContainerGetter.arrayList();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public List<Document> queryAll(String collectionName, int start, int limit) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find().skip(start).limit(limit).iterator();
		List<Document> list = ContainerGetter.arrayList();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public List<Document> queryAll(String collectionName, Bson filter, Document sort, int start, int limit) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find(filter).sort(sort).skip(start).limit(limit).iterator();
		List<Document> list = ContainerGetter.arrayList();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public List<Document> queryAll(String collectionName, Bson filter, Bson sort, int start, int limit) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		MongoCursor<Document> cursor = collection.find(filter).sort(sort).skip(start).limit(limit).iterator();
		List<Document> list = ContainerGetter.arrayList();
		while (cursor.hasNext()) {
			list.add(cursor.next());
		}
		return list;
	}

	public long count(String collectionName) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		return collection.count();
	}

	public long count(String collectionName, Bson filter) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		return collection.count(filter);
	}

	public void updateMulti(String collectionName, Bson filter, Document update) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.updateMany(filter, new Document().append("$set", update));
	}

	public void deleteById(String collectionName, Object id) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.deleteOne(Filters.eq("_id", id));
	}

	public void deleteByIDs(String collectionName, List<String> ids) {

		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.deleteMany(Filters.in("_id", ids.toArray()));
	}

	public void deleteFieldFirst(String collectionName, Document filter, Document update) {
		MongoCollection<Document> collection = getDatabase().getCollection(collectionName);
		collection.updateOne(filter, new Document().append("$unset", update));
	}

	public Long getTotalSize(Set<String> collectionNameSet) {
		Long totalSize = 0L;
		if (null != collectionNameSet && !collectionNameSet.isEmpty()) {

			Set<String> allCollectionNameSet = ContainerGetter.hashSet();
			MongoCursor<String> cursor = mongoDatabase.listCollectionNames().iterator();
			while (cursor.hasNext()) {
				allCollectionNameSet.add(cursor.next());
			}
			cursor.close();

			for (String collectionName : collectionNameSet) {
				if (!allCollectionNameSet.contains(collectionName)) {
					continue;
				}

				BsonDocument command = new BsonDocument("collStats", new BsonString(collectionName));
				Document doc = mongoDatabase.runCommand(command, mongoDatabase.getReadPreference());
				if (null != doc) {
					totalSize += StringTools.getLong(doc.get("size"));
				}
			}
		}
		return totalSize;
	}

}
