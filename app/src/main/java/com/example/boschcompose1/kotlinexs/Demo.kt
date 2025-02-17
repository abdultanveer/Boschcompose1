package com.example.boschcompose1.kotlinexs

//https://play.kotlinlang.org/byExample/01_introduction/02_Functions
fun main() {
printMessage("welcome to android at bosch")
    var valreturned = printMsg("ansari")
    println(valreturned)
}


fun printMessage(message: String): String {                               // 1
    println(message)

    return "abdul"
}

var printMsg: (String)->String = {
    str -> println(str)
     "abdul" }

fun sum(x: Int, y: Int) = x + y


fun multiply(x: Int, y: Int) = x * y