package com.baiytfp.hf.faceguardiot.rest.controller;

import org.restexpress.Request;
import org.restexpress.Response;

import com.alibaba.fastjson.JSONObject;

import com.baiytfp.hf.faceguardiot.rest.RestController;

public class UserAddController extends RestController {
	
	/*@Override
	protected void check_post(Request request, Response response) throws Exception {
	}*/
	

	@Override
	protected Object post(Request request, Response response) throws Exception {
		JSONObject jsonBody = getBodyJson(request);
		String name = jsonBody.getString("name");
		System.out.println(name);
		/*String id = new ObjectId().toHexString();
		DUser user = new DUser(id, name);
		
		UserServiceImpl.INSTACNE().saveUser(user);*/
		
		JSONObject retJson = new JSONObject();
		/*retJson.put("id", user.getId());
		retJson.put("name", user.getName());*/
		return retJson;
	}
	
}
