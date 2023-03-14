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
_SOLID principles are introduced by Robert C.Martin also known as Uncle bob.
- Single Responsibility - every class should have only one responsibility,
```java
// Violating the rule
class UserServiceDao {

    String getEmpNameById(int id) {
        return findEmp(id);
    }
    void insertEmployeesToDB(String name, int id) {
//        Logic to connect to db
//        Logic to store data into db
    }

    String findEmp(int id) {
//        Logic to connect to db
//        Logic to get the employee from DB with id
        return null;
    }

}

// Following the rule
class UserService {
    UserDAO dao;
    String getEmpNameById(int id) {
        return dao.findEmp(id);
    }
}

class UserDAO {
    void insertEmployeesToDB(String name, int id) {
//        Logic to connect to db
//        Logic to store data into db
    }

    String findEmp(int id) {
//        Logic to connect to db
//        Logic to get the employee from DB with id
        return null;
    }
}
```
- Open and Close principle - each class should be open to extension and closed to modification,
```java
// Violating the rule
class User {
    int id;
    String name;
//    If requirement came to add the empId to the user adding it here is not recommended
    String empId;
}

// Following the rule
class BaseEntity {
    int id;
    String name;
}

class Employee extends BaseEntity {
    String empId;
}
```
- Liskov substitute principle - Inheritance should be used only when the super class can be replaced by the subclass,
```java
// Violating the rule
class Factory {
    String mobile(String ram, String proc, String battery) {
        return ram + " | " + proc + " | " + battery;
    }
}

class MobileFactory extends Factory {
    String smartPhone(String cam) {
        return mobile("8gb", "SD Gen 2", "6000mah") + cam;
    }
}

// Following the rule
class IFactory {
    String mobile(String ram, String proc, String battery) {
        return ram + " | " + proc + " | " + battery;
    }
    String mobile(String ram, String proc, String battery, String cam) {
        return ram + " | " + proc + " | " + battery + " | " + cam;
    }
}
```
- Interface segregation principle - Should not force to implement the interfaces which we are not using, better to create a new interface,
- Dependency Inversion principle - Instead of depending on concrete classes, should depend on abstraction.

### 5. ACID principles
- Atomicity - Each transaction should execute completely, or it should not at all execute, if a transaction fails it should roll back to the previous state before the transaction.
- Consistency - A database transaction should follow all the constraints of your schema, if anything is violated it should roll back to its original state.
- Isolation - It makes the transaction execute concurrently. each transaction should execute after committing the previous transaction.
- Durability - The data saved into database should be recoverable even if a system fails or power outage.

### 6. Fail fast, fail safe iterators
- Fail fast - When a Collection is getting iterated using the iterator method, if any structural modifications happened by any other threads, it will immediately throw ConcurrentModificationException, it is Fail-fast iterator, it will internally work by keeping a modified count, once a thread realizes the change in modified count, it will throw the exception. 
- Fail-safe - Fail safe iterator will take the copy of the collection and do the iteration on it, if any modifications happened to the collection it will not give the guaranty of reflecting those changes, this iterator is available in java.util.concurrency package, it is a weekly consistent iterator.
### 7. How internally fail-fast checks and throw ConcurrentModificationException?
- Internally it checks the expectedModCount and Modified count(any change in the structure of collection) in checkForCoModification() whenever iterator using the next()
### 8, 9. Memory in java
- We have a class called Runtime in lang package which will gives the freeMemory(), totalMemory(), etc.. methods to find the memory of the java.
```java
public class Memory {
  public static void main(String[] args) {
    Runtime runtime = Runtime.getRuntime();
    System.out.println(runtime.freeMemory() + ", total memory: " + runtime.totalMemory());
  }
}
```
### 10. How to get the size of the java object ?
- We can use the instrumentation from the java to find the minimum size of the object.
- Create a java class file.
```java
public class Student {
	int roll;
}
```
- Create a Main class file with the main method and premain method as below.
```java
import java.lang.instrument.*;

public class College {
	
	public static void main(String[] args) {
		System.out.println("main...");
	}

	public static void premain(String s, Instrumentation inst) {
		System.out.println("premain...");
		System.out.println(inst.getObjectSize(new Student()));
	}
	
}
```
- Create a manifest file with .mf extension, give an enter after first line.
```
Premain-Class: College

```
- Compile the main java file.
```
javac College.java
```
- Create the jar file
```
jar cvf college.jar College.class
```
- Create the manifest file
```
jar cmf my.mf college.jar College.class
```
- Execute the jar with the java agent
```
java -javaagent:college.jar College
```
### 11. Object Serialization with inheritance in java
- Serialization is the process of storing the state of an object
- By inheriting the serializable interface we can make a class as Serializable.
- By deserializing the stream we can get the state of the object back.
- If parent class is implementing the Serializable, and its subclass is not implementing the Serializable, then we can not get the state of it's child class members.
- While deserialization, it will not call the default constructor, but if the child class not serialized it will again call the constructor.
```java
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
```
### 12. There are three statements in a try block - statement1, statement2 and statement3, After that there is a catch block to catch the exceptions occurred in the try block. Assume that exception has occurred in statement2. Does statement3 get executed or not ?
- If the exception is occurred in the Statement2, and it is handled in the catch block it will not execute the Statement3.
- When an exception occurred, the execution flow will go to its relevant catch block, but it will never come back to try block.
```java
public class Main {
    void test() {
        try {
          System.out.println("Statement1");
          System.out.println("Statement2" + (3/0));
          System.out.println("Statement3");
        } catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }
}
```
### 13. What is unreachable catch block error ?
- Catch blocks should be created by keeping the Top level classes on the bottom, else if we keep the Exception class as the first catch block, because it is a parent for all the exception, each exception will be caught by this block and the remaining blocks will give unreachable catch block exception.
```java
public class Main {
    void test() {
        try {
          System.out.println("Statement1");
          System.out.println("Statement2" + (3/0));
          System.out.println("Statement3");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        catch (ArithmeticException ex) {
            ex.printStackTrace();
        }
    }
}
```
### 14. What is the difference between ClassNotFoundException and NoClassDefFoundError in java ?
- ClassNotFoundException - If a class is not found while loading using Class.forName() or ClassLoader.loadClass() then it will throw ClassNotFoundException;
- NoClassDefFoundError - If a class is found while compile time but not found while run time it will throw NoClassDefFoundError.
```java
public class NoClassFoundVsDef {
    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("something");
        System.out.println(new TempClass());
    }
}

class TempClass {}
```
### 15. Custom immutable class
- Make the class as final
- Make all the fields as private and final
- Don't provide any methods which will set or modify the variables.
- Create a Constructor for assigning the values.
- Provide only the getters.
- If any mutable object is used as reference in your class, instead of directly returning that object in get method, create a new object of it using the initialized object and return the new one.
```java
final class Immutable {
    private final String name;
    private final Date date;
    private final List<String> strings;
    private final Employee employee;

    Immutable(String name, Date date, List<String> strings, Employee employee) {
        this.name = name;
        this.date = date;
        this.strings = strings;
        this.employee = employee;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return new Date(date.getTime());
    }

    public List<String> getStrings() {
        return new ArrayList<>(strings);
    }

    public Employee getEmployee() {
        Employee employee1 = new Employee();
        employee1.empId = employee.empId;
        return employee1;
    }
}
```

### 16. Why java 8 is more popular than java 9 and other versions?
- Java 8 is released in March 2014, it is a major updated which introduced lambda, static and default methods, functional interface, streams, and it is a LTS(long term support) version.
- Java 9 has released in Sep 2017 with minimum updates like modules, JShell, reactive streams, and it introduced some internal code errors too, it is also not an LTS version.

### 17. Interface and why it is? Why interfaces widely used than abstract classes?
- Interface is a reference type in java, which will contain only constants, abstract methods(without body), static and default methods(with body),
- Interface can be used to achieve multiple inheritance in java, implements keyword is used to inherit the properties of interface to class.
- all the variables of interface are public static final, and methods are public by default.
- The main difference b/w interfaces and abstract classes are:
- Interfaces are used to create the contracts
- Abstract classes are used to provide the partial implementation.

### 18. Is Functional interface there before java8? What are they ?
- Functional interfaces are there before java 8, but they are not explicitly marked as @FunctionalInterface
- Runnable is one of the functional interface with only run() method.

### 19. Custom functional interface creation
- We can create a custom functional interface by using @FunctionalInterface annotation, and adding only one abstract method and any number of static and default methods.
```java
@FunctionalInterface
interface FuncInterface {
    void print();
}
```

### 20. Other than public what ar the access modifier for interface
- public: for accessing it anywhere in the project
```java
public interface Pub {
    
}
```
- default: to access it in the same package
```java
interface Def {
    
}
```
### 21. Timed out exception
### 22. TreeMap?
### 23. Where we are LinkedHashMap?
### 24. SQL injection
### 25. How we know a thread is completed ?
### 26. How we execute multiple methods parallel and aggregate?
### 27. HashSet of employees bases on name, how we can get unique employees based on email id.
### 28. How do you scale your application?
### 29. Transaction propagation
### 30. Transactional isolation
### 31. String Builder vs String Buffer
### 32. @Service vs @Repository, Is spring container throws any exception while using @Repository in Service interface
### 33. Service interface
### 34. Spring AOP
### 35. How we use cross-cutting concerns
### 36. Interceptor and filters
### 37. Composite key in jpa
### 38. Fetch - early fetch, lazy fetch
### 39. Stupid bean in java
### 40. Comparable() vs comparator()

### 41. To avoid clone() - (Implement Cloneable interface and override clone() and throw cloneNotSupported)
- Cloning is achieved by implementing the Cloneable interface and by overriding the clone method from the Object class.
- If Cloneable is used with a singleton class, it's rule of singleton will break,
- To avoid it we can throw CloneNotSupported exception from the clone method.
```java
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
```
### 42. Merge vs pull
- git merge command is used merge the changes happened in the remote repository to local repository.
- git merge should be used along with git fetch, which will download the changes and stores them until you merge it.
- git pull will do both the action at a time, it will download the changes from the repo and merge with the local code, if any merge conflicts occurs, we have to fix them.

### 43. Git conflicts resolve
- git conflicts will occur when the same file and same line is having different content in remote repo and local repo or two different branches, and we try to merge them.
- if we use any IDE, it will give some options to accept the current changes for keeping your content, and accept incoming changes to receive the remote changes, and accept both the changes so that it will add both the content, if non of them works for you, then you can compare both the changes and modify it yourself.
- once the modifications are done, we have to add the resolved files and needs to be committed.

### 44. Git rollback
- git rollback is the process of reverting back to the original state where we are in a particular commit.
- If we accidentally committed any changes to wrong branch, any wrong information is committed, then you can do revert back to previous stage using git revert <versionid>
- by executing this command all the latest changes will be removed and it will return back the previous stage.

### 45. Virtualization or containerization
- Virtualization is running multiple machines on the same server.
- With the help of hypervisor we can install vmware or virtual box etc. to run the virtual machines in a single server.
- It can run multiples complete Operating systems without affecting other machines and physical machine.
- The issue with virtualization is it will take a lot of memory for adding a new virtual machine, and it will take time to boot to load all the vm's.
- Containerization is similar to virtualization but instead of creating a new OS for every application, it will share the server operating system.
- Containers are lightweight and easily portable.
- Containers are dependent on Operating system so if we move to another OS it will not work, this is not an issue in the virtualization.

### 46. Character occurrence using HashMap, put() method returns ? - initially it returns null, later it returns previous value(V)
```java
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
```
### 47. How many tables will create in one-to-many and many-to-many hibernate

### 48. Spring actuator
- Spring actuator is used to monitor and manage the health of application.
- By default, it will give 2 endpoints to access the health, /actuator, /actuator/health.
- We can create custom endpoints by using @Endpoint annotation and providing the id as the endpoint name.
- it should map to a method by using @ReadOperation annotation, it can also take Request params.
- We can also change the default /actuator endpoint from the properties file by using management.endpoints.web.base-path="custom"

### 49. Spring mvc flow
- Spring MVC(model control view)
- When a request sent from the client to the server, it will goto web.xml file then a front controller which is Dispatcher servlet will take that request and it's responsibility is to map the request to the relevant controller.
- Then the request will be mapped with the controller, from there it will go throw service and dao layers,
- Once it gets the data from the database, it will return back to service and controller,
- Then the jasper will map the relevant model by using the view resolver,
- It will be returned to the client.

### 50. Spring jpa

### 51. Get() vs load()
- get and load methods are there in the hibernate session object, which are used to retrieve the entity.
- get method will call the db and returns the object.
- load method will not give the original object, instead it will give a proxy object, once we try to use the object then it will give the original object.
- if not object found get will give null, load will give ObjectNotFoundException.

### 52. Hibernate caches and configuration for second level cache
### 53. Spring vs spring boot
### 54. Print middle number in given 3 numbers
### 55. == and .equals()
### 56. Object class
### 57. Second max highest salary in employee table
### 58. Why ArrayList is fast
### 59. List vs Set
### 60. Compare vs compareTo
### 61. Fork in git.
### 61. TreeMap/TreeSet internal working
### 62. Handling Cache in spring boot
### 63. String - intern() method
### 64. Context-path vs request-path
### 65. String internal working
### 66. Try with resources.
### 67. Spring profiles
### 68. Serialization vs deserialization - implementation
### 69. Serialization and it's child classes need to be serialized or not ?
### 70. Diamond problem
### 71. Volatile keyword-not required to serialization
### 72. Fork in java
### 73. Marker interface
### 74. Thread-safe, non-thread-safe
### 75. Bean ambiguity -@Primary
### 76. JWT - to rest endpoints secure. Spring security.
### 77. Spring boot - default servers-tomcat, undertow, jetty
### 78. Exception handling in spring boot
### 79. wait(), notify() and notifyAll() are methods in Object class
### 80. Custom annotations
### 81. how to exclude multiple dependencies in maven?
### 82. default Log4j level
### 83. HashMap, HashSet internal working
### 84. What is Optional
### 85. Optional chaining
### 86. Handling null pointers using Optional class
### 87. Functional interface, lambda, streams.
### 88. Map, filter, grouping by
### 89. Map vs flat map
### 90. Sorting
### 91. Method reference - can we call static / noo-static methods ?
### 92. List of employees group by designation and sorting.
### 93. Segment locking or bucket locking
### 94. ArrayList vs CopyOnWriteArrayList
### 95. Difference between Session get() and load() method?
### 96. Mapping in Hibernate
### 97. Composite Primary keys in JPA
### 98. Find second-highest salary of an employee?
### 99. Spring boot annotations
### 100. How annotation works in java?
### 101. Transaction rollback
### 102. Spring boot Actuator
### 103. @Controller vs @RestController
### 104. findById() and getOne()
### 105. Current projects and tech stack
### 106. How will you design the microservices based on the MVC pattern?
### 107. How will you implement a global exception handling
### 108. What is the advantage of this approach compared to just handling it with try catch blocks?
### 109. How annotations work behind the scene technically?
### 110. How to handle request parameter validation in SpringBoot?
### 111. How to write custom validation annotations?
### 112. What is transaction in an application, and why do we need transactions?
### 113. Explain how security tokens
### 114. How will you handle storing of sensitive data like password
### 115. How is a NoSql database different from an RDBMS?
### 116. What is generics in java? Can you explain with an example?
### 117. Explain the concepts related to lambda expressions?
### 118. Differentiate between List and Set in Java.
### 119. Java program to find Second-Highest Element in an array
### 120. Count strings whose length is greater than 3 in List using streams
### 121. Find duplicate elements in a given integers list in java using stream functions
### 122. Find the first element of the list using the stream functions
### 123. Find the first non-repeated character in it using Stream functions
### 124. Find out all the numbers starting with 1 using stream functions
### 125. Find repeated char using stream
### 126. Iterate a stream using the forEach method
### 127. remove the duplicate element from the list
### 128. sort the given list in ascending and descending order
### 129. get the sum of all numbers present in a list using streams
### 130. Filtering location: [[Pune, Hyd], [Chennai, Hyd], [Chennai, Hyd, Pune]] get unique cities from employee Object as [Hyd, Chennai, Pune] using flatMap
### 131. Unique array elements using streams
### 132. Character occurrence in a string using stream
### 133. Count of words or letters in a string using streams
```
        String name = "Karampudi Rajasekhar";

        List<Character> characters = new ArrayList<>();

        for(char c : name.toCharArray()) {
            characters.add(c);
        }

        long count = characters.stream().count();
        System.out.println(count);
```
### 134. HashMap sort based on value using streams: [fifteen=15, eleven=11, eight=8, one=1]
### 135. Sort of employee by salary using streams
### 136. Separate vowels and consonants in string using filter
