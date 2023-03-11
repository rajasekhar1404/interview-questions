package com.interview.solutions;

public class NoClassFoundVsDef {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("something");
        System.out.println(new TempClass());
    }
}

class TempClass {}