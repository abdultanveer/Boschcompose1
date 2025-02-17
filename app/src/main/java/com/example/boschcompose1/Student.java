package com.example.boschcompose1;


public class Student {

    String name;
    int age;
    Address sAddress;

    public Student(String name, int age, Address sAddress) {
        this.name = name;
        this.age = age;
        this.sAddress = sAddress;
        String brake;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getsAddress() {
        return sAddress;
    }

    public void setsAddress(Address sAddress) {
        this.sAddress = sAddress;
    }
}
