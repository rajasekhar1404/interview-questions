package com.interview.solutions;

import java.lang.instrument.Instrumentation;

public class ObjectSize {
    public static void main(String[] args) {
        InstrumentationObject.printSize("Rajasekhar");
    }
}
class InstrumentationObject {
    private static Instrumentation instrumentation;
    public static void premain(String args, Instrumentation instrumentation) {
        InstrumentationObject.instrumentation = instrumentation;
    }

    public static void printSize(Object object) {
        System.out.println(instrumentation.getObjectSize(object));
    }
}
