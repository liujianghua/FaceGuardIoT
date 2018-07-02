package com.baiytfp.hf.faceguardiot.rest.controller;

import org.restexpress.Request;
import org.restexpress.Response;

import com.alibaba.fastjson.JSONObject;

import com.baiytfp.hf.faceguardiot.rest.RestController;

public class UserController extends RestController {
	
	@Override
	protected void check_get(Request request, Response response) throws Exception {
	}
	
	@Override
	protected Object get(Request request, Response response) throws Exception {
		String id = request.getHeader("id");
		String name =  request.getHeader("name");
		System.out.println("id:"+id);
		System.out.println("name:"+name);
		/*DUser user = UserServiceImpl.INSTACNE().findById(id);
		if(null == user){
			throw new Rest403StatusException(ERROR_CODE.USER_NOT_EXIST, "user not exist");
		}*//*

		retJson.put("id", user.getId());
		retJson.put("name", user.getName());*/

		JSONObject retJson = new JSONObject();
		
		return retJson;
	}

	
	
}
