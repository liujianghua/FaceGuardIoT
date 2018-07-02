package com.baiytfp.hf.faceguardiot.utils.map;

import java.util.*;


public class MapUtil {
	
	//打印 Map 信息
	public static void printMap(Map<String, Object> map) {
		if(map != null){
			Iterator entries = map.entrySet().iterator();
		    while (entries.hasNext()){
		    	Map.Entry entry = (Map.Entry)entries.next();
			    Object k = entry.getKey();
			    Object value = entry.getValue();
			    
			    if (k == null) {
		          System.err.println("keys cannot be null.");
		        }
		        if (!(k instanceof String)) {
		        	System.err.println("keys must be strings.");
		        }
		        String key = String.valueOf(k);
		        if ("null".equals(key)) {
		        	System.err.println("keys must not be null nor the 'null' string.");
		        }
		        
		        System.out.println(key + " : " + value);
			}
		}else{
			System.out.println("此 Map 为空");
		}
	}
	
	//把Map中的值为 Null 的设置为 ""
	public static Map filerMapNullValue(Map<String, Object> map) {
		Map resultMap = new HashMap();
	    Iterator entries = map.entrySet().iterator();
	    while (entries.hasNext()){
	    	Map.Entry entry = (Map.Entry)entries.next();
		    Object k = entry.getKey();
		    Object value = entry.getValue();
		    
		    if (k == null) {
	          System.err.println("keys cannot be null.");
	        }
	        if (!(k instanceof String)) {
	        	System.err.println("keys must be strings.");
	        }
	        String key = String.valueOf(k);
	        if ("null".equals(key)) {
	        	System.err.println("keys must not be null nor the 'null' string.");
	        }
	        
	        if(value == null){
	        	resultMap.put(key, "");
	        }else{
	        	resultMap.put(key, value);
	        }
		}
	    
	    return resultMap;
	}
	
	//把Map中的值为 Null 的设置为 ""
	public static List filerListMapNullValue(List<Map> list) {
		List resultList = new ArrayList();
		
		Iterator elements = list.iterator();
		while(elements.hasNext()){
			Object obj = elements.next();
			if (obj instanceof Map) {
				resultList.add(filerMapNullValue((Map)obj));
			}else{
				resultList = list;
				break;
			}
		}
		
		return resultList;
	}
	
	/**
     * 使用 Map按key进行排序 (需要是 TreeMap)
     * param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> treeMap) {
        if (treeMap == null || treeMap.isEmpty()) {
            return null;
        }
 
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());
 
        sortMap.putAll(treeMap);
 
        return sortMap;
    }
    
    /**
     * 使用 Map按value进行排序 (需要是TreeMap)
     * param map
     * @return
     */
    public static Map<String, String> sortMapByValue(Map<String, String> treeMap) {
        if (treeMap == null || treeMap.isEmpty()) {
            return null;
        }
        Map<String, String> sortedMap = new LinkedHashMap<String, String>();
        List<Map.Entry<String, String>> entryList = new ArrayList<Map.Entry<String, String>>(
        		treeMap.entrySet());
        Collections.sort(entryList, new MapValueComparator());
 
        Iterator<Map.Entry<String, String>> iter = entryList.iterator();
        Map.Entry<String, String> tmpEntry = null;
        while (iter.hasNext()) {
            tmpEntry = iter.next();
            sortedMap.put(tmpEntry.getKey(), tmpEntry.getValue());
        }
        return sortedMap;
    }
    
}
