package com.interview.solutions;

public class Memory {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(runtime.freeMemory() + ", total memory: " + runtime.totalMemory());
    }
}
