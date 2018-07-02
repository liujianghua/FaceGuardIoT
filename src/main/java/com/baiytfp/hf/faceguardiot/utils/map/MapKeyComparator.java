package com.baiytfp.hf.faceguardiot.utils.map;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String>{
    public int compare(String str1, String str2) {
        
    	//升序
        return str1.compareTo(str2);
        
        //降序
//        return str2.compareTo(str1);
    }
}
