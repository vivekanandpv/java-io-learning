package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        List<Employee> employeeList = new ArrayList<>();

        Car car = new Car();
        car.id = 1234;
        car.model = "Toyota Etios";

        employeeList.add(new Employee(1, "Suresh", new Date(), "Mr", car));
        employeeList.add(new Employee(5, "Ramesh", new Date(), "Dr", car));

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("employees.dat"))) {
            objectOutputStream.writeObject(employeeList);
        }

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("employees.dat"))) {
            List<Employee> employeeList1 = (List<Employee>) objectInputStream.readObject();

            for (Employee e: employeeList1) {
                System.out.println("Employee > " + e.getTitle() +" " + e.getName() +"; " + e.getDob() + "; CAR > " + e.getCar());
            }
        }
    }
}

class Employee implements Serializable {
    private int id;
    private String name;
    private Date dob;
    public String title;
    private Car car;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee(int id, String name, Date dob, String title, Car car) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.title = title;
        this.car = car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

class Car implements Serializable {
    public int id;
    public String model;
}
