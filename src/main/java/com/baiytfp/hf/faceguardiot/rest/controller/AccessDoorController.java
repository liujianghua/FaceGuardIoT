package com.baiytfp.hf.faceguardiot.rest.controller;

import cn.xlink.iot.sdk.datastruct.XlinkIotPublishModel;
import cn.xlink.iot.sdk.datastruct.XlinkIotPublishRsp;
import cn.xlink.iot.sdk.operator.XlinkIotPublish;
import cn.xlink.iot.sdk.type.XlinkIotPublishOperation;
import com.alibaba.fastjson.JSONObject;
import com.baiytfp.hf.faceguardiot.rest.RestController;
import com.baiytfp.hf.faceguardiot.utils.ApiUtil;
import com.baiytfp.hf.faceguardiot.utils.XlinkIotUtil;
import org.restexpress.Request;
import org.restexpress.Response;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author JingFengQin
 * @ClassName: AccessDoorController
 * @Description: 人脸门禁
 * @date 2018年06月07日  17:11
 */
public class AccessDoorController extends RestController {

    @Override
    protected void check_get(Request request, Response response) throws Exception {
    }

    @Override
    protected Object post(Request request, Response response) throws Exception {

        Map<String, String> paramsMap = getBodyMap(request);

        if(!ApiUtil.validRequest(paramsMap)){	//请求不合法
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("state", 1001);
            jsonObject.put("message", "签名错误");
            return jsonObject;
        }

        //获取提交过来的数据
        Map data = XlinkIotUtil.getInstance().getAccessMap(paramsMap);

        System.out.println(data.toString());

        JSONObject jsonObject = XlinkIotUtil.getInstance().getXlinkIotPublish(data, XlinkIotUtil.accessDoor, XlinkIotPublishOperation.Upsert);
        return jsonObject;
    }

}
