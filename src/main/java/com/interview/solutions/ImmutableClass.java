package com.interview.solutions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ImmutableClass {

}

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