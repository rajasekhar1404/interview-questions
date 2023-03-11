package com.interview.solutions;

import java.io.*;

class Serialized extends NonSerialized implements Serializable {

    @Serial
    private static final long serialVersionUID = 937864110406370819L;
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "InheritanceSerialization{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}

class NonSerialized {
    String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "NonSerialized{" +
                "surname='" + surname + '\'' +
                '}';
    }
}

public class InheritanceSerialization {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream("file.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        Serialized serialized = new Serialized();
        serialized.setName("Rajasekhar");
        serialized.setSurname("Karampudi");
        System.out.println(serialized);
        objectOutputStream.writeObject(serialized);

        FileInputStream fileInputStream = new FileInputStream("file.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        Serialized nonSerialized = (Serialized) objectInputStream.readObject();
        System.out.println(nonSerialized);
    }
}