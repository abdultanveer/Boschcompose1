// IMyAidlInterface.aidl
package com.example.marsphotos;


interface IMyAidlInterface { //aidl-2

           int add(int fno, int sno);

}

//to consume an aidl service
//1. in app.gradle enable aidl
//2. right click on package- select new-aidl-aidl file
//3. in aidl file add  a method add(a,b)
//4 change the package  name to com.example.marsphotos
//4. rebuild app
//5. onclick of the button innvoke bindservice the intent("ineed.addition.bosch)
//6. in serviceconnection object's -- onserviceconnected method receive the result