### 1. What is covariant return type ?
- It was introduced after java 5,
- We can use the return type of the override method same as the parent class method, or it allows to use the child class of the parent class method return type.
```java
public class Laptop {}
class Lenovo extends Laptop {}
class BuyLaptop {
    public Laptop getLaptop() {
        return new Laptop();
    }
}
class BuyLenovoLaptop extends BuyLaptop {
    @Override
    public Lenovo getLaptop() {
        return new Lenovo();
    }
}
```

### 2.Inheritance vs Association vs Aggregation vs Composition
- Inheritance is extending the properties of another class, which will create the tight coupling.
  - This relationship is called IS-A relation, coz Cricket IS-A Game
```java
class Game {
    void play() {}
}
class Cricket extends Game {
    void play() {}
}
```
- Association is having another class object inside a class,
  - This relation is called as HAS-A relation
  - It is divided into two types: 1. Aggregation, 2. Composition.
    - Aggregation
      - It is HAS-A relation, which consists of one way relation ( A { B } ), means it is not mandatory to have the B class in A class, this is week relation.
```java
class Mobile {
    private FingerPrint fingerPrint;
}
// Here a mobile can have FingerPrint sensor but it is not mandatory to have it.
class FingerPrint {}
```
- Composition
  - It is HAS-A relation, which consists of two way relation ( A { B } ), where A class can not exists without B and B class not exists without A, this is a strong relation.
```java
class Mobile {
    private Battery battery;
}
// Here a mobile must have a battery and a battery must have a mobile.
class Battery {}
```
### 3. Different ways of creating an object.
  - Using new keyword and calling the constructor.
  - Using the reflection mechanism with Class.forName();
  - Using the reflection mechanism with classLoader
  - Using clone method from Cloneable interface.
  - Using constructor class and Class<T> class
  - Using FileInput and FileOutput stream
```java
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
```
### 4. SOLID principles
- Single Responsibility - every class should have only one responsibility,
- Open and Close principle - each class should be open to open to extension and closed to modification,
- Liskov substitute principle - Inheritance should be used only when the super class can be replaced by the subclass,
- Interface segregation principle - Should not force to implement the interfaces which we are not using, better to create a new interface,
- Dependency Inversion principle - Instead of depending on concrete classes, should depend on abstraction.

### 9, 10. Memory in java
- We have a class called Runtime in lang package which will gives the freeMemory(), totalMemory(), etc.. methods to find the memory of the java.
```java
public class Memory {
  public static void main(String[] args) {
    Runtime runtime = Runtime.getRuntime();
    System.out.println(runtime.freeMemory() + ", total memory: " + runtime.totalMemory());
  }
}
```