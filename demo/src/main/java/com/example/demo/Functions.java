package com.example.demo;
import java.util.*;
public class Functions {
    
    String payer;
    String points; 
    String timestamp;

//int count2 = 1;
public Functions(String payer, String points, String timestamp) {
    this.points = points;
    this.payer = payer; 
    this.timestamp = timestamp;
    
}

public void add(String payer, String points, String timestamp, TreeMap<Long,HashMap<String, Integer>> paymentMap,  HashMap<String, Integer> totalMap) {
    timestamp = timestamp.replaceAll("-","");
    timestamp = timestamp.replaceAll(":","");
    timestamp = timestamp.replaceAll("[a-zA-Z]","");
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    Integer placeHolder = Integer.valueOf(points);
    map.put(payer, placeHolder);
    if (totalMap.containsKey(payer)) {
        Integer temp = totalMap.get(payer);
        temp += Integer.valueOf(points);
        totalMap.put(payer, temp);
    }
    else {
        Integer temp = Integer.valueOf(points);
        totalMap.put(payer, temp);
    }
    Long time = Long.valueOf(timestamp);
    paymentMap.put(time, map);
    return;
    
}

public String generalSpend(String points, TreeMap<Long,HashMap<String, Integer>> paymentMap,  HashMap<String, Integer> totalMap) {
    String ret = "";
    Integer remain = Integer.valueOf(points);
    for (Map.Entry<Long, HashMap<String, Integer>> innerMap : paymentMap.entrySet()) {
        if (remain > 0) {
            HashMap<String, Integer> map = innerMap.getValue();
            for (Map.Entry<String, Integer> company : map.entrySet()) {
                if (company.getValue() >= remain) {
                    ret = ret.concat(company.getKey() + ": " + Integer.toString(remain) + ": ");
                    Integer temp = company.getValue() - remain;
                    map.put(company.getKey(), temp);
                    paymentMap.replace(innerMap.getKey(), map);
                    Integer totalTemp = totalMap.get(company.getKey()) - remain;
                    totalMap.replace(company.getKey(), totalTemp);
                    remain = 0;
                    break;
                }
                else if (company.getValue() != 0){
                    ret = ret.concat(company.getKey() + ", " + Integer.toString(company.getValue()) + "\n");
                    remain -= company.getValue();
                    Integer totalTemp = totalMap.get(company.getKey()) - company.getValue();
                    totalMap.replace(company.getKey(), totalTemp);
                    map.put(company.getKey(), 0);
                    paymentMap.replace(innerMap.getKey(), map);
                    break;
                }
            }
        }
        else {
            break;
        }
    }
    
    return ret;
}

public String pointBalance(HashMap<String, Integer> totalMap) {
    String ret = "";
    for (Map.Entry<String, Integer> company : totalMap.entrySet()) {
        ret = ret.concat(company.getKey() + "," + company.getValue() + "\n");
        
    }
    return ret;
}

public String companySpend(String payer, String points, TreeMap<Long,HashMap<String, Integer>> paymentMap,  HashMap<String, Integer> totalMap) {
    String ret = payer + ", " + points;
    Integer remain = Integer.valueOf(points);
    for (Map.Entry<Long, HashMap<String, Integer>> innerMap : paymentMap.entrySet()) {
        if (remain > 0) {
            HashMap<String, Integer> map = innerMap.getValue();
            for (Map.Entry<String, Integer> company : map.entrySet()) {
                if (company.getKey().equals(payer)) {
                    if (company.getValue() >= remain) {
                        Integer temp = company.getValue() - remain;
                        map.put(company.getKey(), temp);
                        paymentMap.replace(innerMap.getKey(), map);
                        Integer totalTemp = totalMap.get(company.getKey()) - remain;
                        totalMap.replace(company.getKey(), totalTemp);
                        remain = 0;
                    }
                    else if (company.getValue() != 0) {
                        int temp = company.getValue();
                        Integer totalTemp = totalMap.get(company.getKey()) - company.getValue();
                        totalMap.replace(company.getKey(), totalTemp);
                        map.put(company.getKey(), 0);
                        paymentMap.replace(innerMap.getKey(), map);
                        remain -= temp;
                    }
            }
        }
        }
        else {
            break;
        }
    
}
    return ret;
}
}