package com.baiytfp.hf.faceguardiot.service.impl;

import com.baiytfp.hf.faceguardiot.dao.impl.MUserDaoImpl;
import com.baiytfp.hf.faceguardiot.dao.impl.RUserDaoImpl;
import com.baiytfp.hf.faceguardiot.datastruct.DUser;
import com.baiytfp.hf.faceguardiot.service.IUserService;

public class UserServiceImpl implements IUserService {

	private static final IUserService SINGLETON = new UserServiceImpl();
	
	private UserServiceImpl(){
	}
	
	public static final IUserService INSTACNE(){
		return SINGLETON;
	}
	
	@Override
	public DUser findById(String id) throws Exception {
		DUser user = RUserDaoImpl.INSTACNE().findById(id);
		if(null != user){
			return user;
		}
		
		return MUserDaoImpl.INSTACNE().findById(id);
	}

	@Override
	public void saveUser(DUser user) throws Exception {
		RUserDaoImpl.INSTACNE().saveUser(user);
		MUserDaoImpl.INSTACNE().saveUser(user);
	}

}
