package com.interview.solutions;

import java.util.HashMap;

public class OccurrenceWithHashMap {
    public static void main(String[] args) {
        HashMap<Character, Integer> map = new HashMap<>();
        String name = "Rajasekhar karampudi";
        char[] chars = name.toCharArray();
        for(char c : chars) {
            if(c == ' ') {}
            else if(map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        System.out.println(map);
    }
}
