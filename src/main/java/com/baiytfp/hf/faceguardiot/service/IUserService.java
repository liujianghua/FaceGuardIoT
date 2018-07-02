package com.baiytfp.hf.faceguardiot.service;

import com.baiytfp.hf.faceguardiot.datastruct.DUser;

public interface IUserService {

	DUser findById(String id)throws Exception;
	
	void saveUser(DUser user)throws Exception;
	
}
