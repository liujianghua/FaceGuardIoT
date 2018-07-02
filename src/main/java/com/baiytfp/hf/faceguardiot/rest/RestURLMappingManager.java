package com.baiytfp.hf.faceguardiot.rest;

import com.baiytfp.hf.faceguardiot.rest.controller.*;

public class RestURLMappingManager extends SuperURLMappingManager {
	private static final RestURLMappingManager singleton = new RestURLMappingManager();

	public static final RestURLMappingManager instance() {
		return singleton;
	}

	private RestURLMappingManager() {

	}

	protected void init() throws Exception {
		register(REST_URL.USER_ADD, UserAddController.class, RestPermissionType.ANYBODY);
		register(REST_URL.USER, UserController.class, RestPermissionType.ANYBODY);
		register(REST_URL.ACCESS_DOOR, AccessDoorController.class, RestPermissionType.ANYBODY);
		register(REST_URL.DEVICE_INIT, DeviceInitController.class, RestPermissionType.ANYBODY);
		register(REST_URL.DETECT_EVENT, DetectEventController.class, RestPermissionType.ANYBODY);
	}

}
