package com.example.demo;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunctionController {
    TreeMap<Long,HashMap<String, Integer>> paymentMap = new TreeMap<Long, HashMap<String,Integer>>();
    HashMap<String, Integer> totalMap = new HashMap<String, Integer>();
	long total = 0;

	@GetMapping("/functions")
	public String functions(@RequestParam(value = "operation", defaultValue = "World") String operation, @RequestParam(value = "payer", defaultValue = "World") String payer,
     @RequestParam(value = "points", defaultValue = "0") String points, @RequestParam(value = "timeStamp", defaultValue = "World") String timeStamp) {
        operation = operation.toLowerCase();
        operation = operation.replaceAll(" ", "");
        operation = operation.replaceAll("[0-9]", "");
        payer = payer.toLowerCase();
        try {
            Integer.parseInt(points);
        }
        catch (NumberFormatException e) {
            return "Make sure you put a number in for points!";
        }
        Functions function = new Functions(payer, points, timeStamp);
        if (operation.equals("add")) {
            if (payer.equals("World")) {
                return "Make sure you add a payer!";
            }
            if (points.equals("0")) {
                return "Make sure you specify the points!";
            }
            if (timeStamp.equals("World")) {
                return "Make sure you add the time stamp!";
            }
            total += Integer.valueOf(points);
            function.add(payer, points, timeStamp, paymentMap, totalMap);
            return "Add complete\n";
        }
        if (operation.equals("generalspend")) {
            if (Integer.valueOf(points) > total) {
                return "Don't have enough points";
            }
            if (points.equals("0")) {
                return "Make sure to add the points!";
            }
            total -= Integer.valueOf(points);
            return function.generalSpend(points, paymentMap, totalMap);
        }
        if (operation.equals("balance")) {
            if (totalMap.size() == 0) {
                return "No balance to return, add payer first";
            }
            return function.pointBalance(totalMap);
        }
        if (operation.equals("companyspend")) {
            if (!totalMap.containsKey(payer)) {
                return "That payer hasn't made any transactions yet!";
            }
            if (Integer.valueOf(points) > totalMap.get(payer)) {
                return "Not enough points for that transaction!";
            }
            if (points.equals("0")) {
                return "Make sure to add points";
            }
            
            total -= Integer.valueOf(points);
            return function.companySpend(payer, points, paymentMap, totalMap);
        }
        return "Operation not found! Try again";
	}
}
