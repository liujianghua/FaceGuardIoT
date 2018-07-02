package com.baiytfp.hf.faceguardiot.utils.map;

import java.util.Comparator;
import java.util.Map.Entry;

public class MapValueComparator implements Comparator<Entry<String, String>> {
    public int compare(Entry<String, String> me1, Entry<String, String> me2) {
 
        return me1.getValue().compareTo(me2.getValue());
    }
}
