package com.example.lib;



interface Sayable{
    void say();
}
public class InstanceMethodReference {
    public void saySomething(){
        System.out.println("Hello, this is non-static method.");
    }
    public static void main(String[] args) {
        InstanceMethodReference methodReference = new InstanceMethodReference(); // Creating object
        // Referring non-static method using reference
        Sayable sayable = methodReference::saySomething;
        // Calling interface method
        sayable.say();
        // Referring non-static method using anonymous object
        Sayable sayable2 = new InstanceMethodReference()::saySomething; // You can use anonymous object also
        // Calling interface method
        sayable2.say();

        Sayable sayable3 = () ->{
            System.out.println("lamda declaration");
            //if you don't want to define the fn body and use existing fn body then use :: methodref
        };

        sayable3.say();
    }
}