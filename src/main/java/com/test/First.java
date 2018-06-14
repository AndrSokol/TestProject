package com.test;

import java.util.HashMap;
import java.util.Map;

public class First {
    public static void main(String[] args) {
        int[] arr = {1,4,3,7,2,6,11};
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        Map<Integer, Integer> results = new HashMap<Integer, Integer>();

        for (int i = 0; i < arr.length; i++) {
            map.put(i, 5 - i);
        }

        for (Map.Entry<Integer, Integer> item : map.entrySet())
        {
            for (int i = 0; i < arr.length - 1; i++) {
                if(item.getValue() == arr[i]) {
                    //System.out.println(item.getKey() + " and " + arr[i]);
                    if(! results.containsKey(arr[i]))
                        results.put(item.getKey(), arr[i]);
                }
            }
        }

        System.out.println(results);

    }
}
