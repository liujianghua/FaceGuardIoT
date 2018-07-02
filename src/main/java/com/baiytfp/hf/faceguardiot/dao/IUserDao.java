package com.baiytfp.hf.faceguardiot.dao;

import com.baiytfp.hf.faceguardiot.datastruct.DUser;

public interface IUserDao {

	DUser findById(String id)throws Exception;
	
	void saveUser(DUser user)throws Exception;
}
