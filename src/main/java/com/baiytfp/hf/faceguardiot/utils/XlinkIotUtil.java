package com.baiytfp.hf.faceguardiot.utils;

import cn.xlink.iot.sdk.XlinkIot;
import cn.xlink.iot.sdk.XlinkIotBuilder;
import cn.xlink.iot.sdk.datastruct.XlinkIotPublishModel;
import cn.xlink.iot.sdk.datastruct.XlinkIotPublishRsp;
import cn.xlink.iot.sdk.exception.XlinkIotException;
import cn.xlink.iot.sdk.operator.XlinkIotPublish;
import cn.xlink.iot.sdk.type.XlinkIotPublishOperation;
import com.alibaba.fastjson.JSONObject;
import org.restexpress.Request;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JingFengQin
 * @ClassName: XlinkIotUtil
 * @Description:
 * @date 2018年06月07日  17:25
 */
public class XlinkIotUtil {
    public static String appId = "3207d4b609952000";
    public static String appSecret = "249c808fd1c8f05ee73485ecc2c4f39e";
    public static String endpoint = "iotapitest.bgycc.com:80";
    public static String serviceId = "face_recognition";
    public static String productId = "1607d2b6099800011607d2b609980201";

    public static String accessDoor = "access_door";//人脸门禁
    public static String deviceInit = "device_initialize";//设备上传（设备初始化，非必要接口）
    public static String detectEven = "detect_event";//人脸识别事件

    private static XlinkIotUtil xlinkIotUtil;

    private XlinkIotUtil(){

    }

    public static synchronized XlinkIotUtil getInstance(){
        if(xlinkIotUtil == null){
            xlinkIotUtil = new XlinkIotUtil();
        }
        return xlinkIotUtil;
    }

    /*public static void main(String[] args) throws Exception {
        Map<String,Object> userMap = new HashMap();
        userMap.put("userName","测试员");
        userMap.put("userCode", "0001");

        //具体上报的数字
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("event_id","00001");
        data.put("user_id", "test01");
        data.put("user_info", userMap);
        data.put("camera_id", "camera01");
        data.put("id", "000134");
        data.put("event_time", "2014-10-09T08:15:40.843Z");
        String jsonString = HFJson.getInstance().toJsonString(data);
        String encode = HFBase64.getInstance().encode(jsonString, "UTF-8");
        System.out.println(encode);
    }*/

    public JSONObject getXlinkIotPublish(Map data, String objName,XlinkIotPublishOperation operation) throws Exception {
        //创建一个客户端构造器
        XlinkIotBuilder builder = new XlinkIotBuilder();
        //设置凭证和地址
        builder.setAppId(appId).setAppSecret(appSecret).setEndPoint(endpoint);
        //创建一个http连接的客户端
        XlinkIot xlinkIot = builder.buildXlinkIotHttpClient();

        //1. 创建一个publish实例
        XlinkIotPublish xlinkIotPublish = new XlinkIotPublish(xlinkIot);

        XlinkIotPublishModel upsModel = createUpsModel(objName, operation, data);

        XlinkIotPublishRsp publishResponse = xlinkIotPublish.publishToXlinkIot(upsModel);
        String resultJSON = publishResponse.getContent();
        JSONObject jsonObject = JSONObject.parseObject(resultJSON);
        return jsonObject;
    }

    private static XlinkIotPublishModel createUpsModel(String objName, XlinkIotPublishOperation operation, Map data) {
        XlinkIotPublishModel publishModel = new XlinkIotPublishModel();
        publishModel.setServiceId(serviceId);
        publishModel.setObjectName(objName);
        publishModel.setOperation(operation);
        //可选，设置产品ID，由服务端提供，用于关联物联平台的相关信息
        publishModel.setProductId(productId);
        publishModel.setData(data);
        return publishModel;
    }

    public Map getRequestData(Request request) throws Exception{
        String base64data = request.getHeader("base64data");
        base64data = base64data.replaceAll(" ", "+");
        String jsonStr =  HFBase64.getInstance().decode(base64data, "UTF-8");
        JSONObject jsonBody = JSONObject.parseObject(jsonStr);
        Map data = jsonBody.toJavaObject(Map.class);
        return data;
    }

    public Map getAccessMap(Map<String, String> paramsMap) throws Exception{
        Map result = new HashMap();

        result.put("id", paramsMap.get("id"));
        result.put("online_state", paramsMap.get("online_state"));
        result.put("device_state", paramsMap.get("device_state"));
        result.put("open_type", paramsMap.get("open_type"));
        result.put("open_desc", paramsMap.get("open_desc"));
        result.put("open_time", paramsMap.get("open_time"));
        result.put("user_picture", paramsMap.get("user_picture"));
        result.put("user_id", paramsMap.get("user_id"));

        Map userInfo = new HashMap();
        userInfo.put("user_name", paramsMap.get("user_name"));
        result.put("user_info", userInfo);
        return result;
    }

    public Map getDeviceMap(Map<String, String> paramsMap) throws Exception{
        Map result = new HashMap();

        result.put("project_id", paramsMap.get("project_id"));
        result.put("project_name", paramsMap.get("project_name"));
        result.put("id", paramsMap.get("id"));
        result.put("device_type", paramsMap.get("device_type"));
        return result;
    }

    public Map getDetectEventMap(Map<String, String> paramsMap) throws Exception{
        Map result = new HashMap();

        result.put("id", paramsMap.get("id"));
        result.put("event_id", paramsMap.get("event_id"));
        result.put("event_time", paramsMap.get("event_time"));
        result.put("user_id", paramsMap.get("user_id"));
        result.put("user_picture", paramsMap.get("user_picture"));
        result.put("camera_id", paramsMap.get("camera_id"));
        Map userInfo = new HashMap();
        userInfo.put("user_name", paramsMap.get("user_name"));
        userInfo.put("user_id", paramsMap.get("user_id"));
        result.put("user_info", userInfo);
        return result;
    }

}
