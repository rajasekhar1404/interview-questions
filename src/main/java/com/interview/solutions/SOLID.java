package com.interview.solutions;

public class SOLID {}

/**
 * @Single_Responsibility_principle
 */

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

/**
 * @Open/Closed_principle
 */

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

/**
 * @Liskov_Substitution_principle
 */

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