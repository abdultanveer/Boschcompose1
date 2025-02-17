package com.example.boschcompose1.kotlinexs

//https://play.kotlinlang.org/byExample/01_introduction/02_Functions
fun main() {
printMessage("welcome to android at bosch")
}


fun printMessage(message: String): Unit {                               // 1
    println(message)
}

fun sum(x: Int, y: Int) = x + y


fun multiply(x: Int, y: Int) = x * y