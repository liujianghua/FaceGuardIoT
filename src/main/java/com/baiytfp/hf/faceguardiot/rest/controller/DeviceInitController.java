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

import java.util.Map;

/**
 * @author JingFengQin
 * @ClassName: AccessDoorController
 * @Description:  设备上传（设备初始化）
 * @date 2018年06月07日  17:11
 */
public class DeviceInitController extends RestController {

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

        //具体上报的数据
        Map data = XlinkIotUtil.getInstance().getDeviceMap(paramsMap);

        JSONObject jsonObject = XlinkIotUtil.getInstance().getXlinkIotPublish(data, XlinkIotUtil.deviceInit, XlinkIotPublishOperation.Upsert);
        return jsonObject;
    }

}
