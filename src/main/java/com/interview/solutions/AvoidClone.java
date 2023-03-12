package com.interview.solutions;

class AvoidClone1 implements Cloneable {
    private static AvoidClone1 avoidClone = null;

    private AvoidClone1() {}
    public static AvoidClone1 getInstance() {
        if(avoidClone == null ) {
            avoidClone = new AvoidClone1();
        }
        return avoidClone;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Cloning is not allowed for singleton classes.");
    }
}

public class AvoidClone {
    public static void main(String[] args) throws CloneNotSupportedException {
        System.out.println(AvoidClone1.getInstance().hashCode());
        System.out.println(AvoidClone1.getInstance().clone().hashCode());
    }
}