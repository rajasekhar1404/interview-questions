package com.interview.solutions;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ObjectCreation implements Cloneable {
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Student {
    ObjectCreation firstWay = new ObjectCreation();
    ObjectCreation secondWay = (ObjectCreation) Class.forName("com.interview.solutions.ObjectCreation").newInstance();
    ObjectCreation thirdWay = (ObjectCreation) ObjectCreation.class.getClassLoader().loadClass("com.interview.solutions.ObjectCreation").newInstance();
    ObjectCreation fourthWay = (ObjectCreation) firstWay.clone();
    Class<ObjectCreation> cls = ObjectCreation.class;
    Constructor<?> con = cls.getDeclaredConstructor();
    ObjectCreation fifthWay = (ObjectCreation) con.newInstance();
    Student() throws ClassNotFoundException, InstantiationException, IllegalAccessException, CloneNotSupportedException, NoSuchMethodException, InvocationTargetException, IOException {
        FileOutputStream outputStream = new FileOutputStream("object.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(fifthWay);
        objectOutputStream.flush();

        FileInputStream inputStream = new FileInputStream("object.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        ObjectCreation sixthWay = (ObjectCreation) objectInputStream.readObject();
    }
}