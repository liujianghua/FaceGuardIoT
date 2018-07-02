package com.baiytfp.hf.faceguardiot.dao.impl;

import com.baiytfp.hf.faceguardiot.dao.IUserDao;
import com.baiytfp.hf.faceguardiot.datastruct.DUser;

public class RUserDaoImpl implements IUserDao {

	private static final IUserDao SINGLETON = new RUserDaoImpl();
	
	private RUserDaoImpl(){
	}
	
	public static final IUserDao INSTACNE(){
		return SINGLETON;
	}
	
	@Override
	public DUser findById(String id) throws Exception {
		return null;
	}

	@Override
	public void saveUser(DUser user) throws Exception {
	}

}
