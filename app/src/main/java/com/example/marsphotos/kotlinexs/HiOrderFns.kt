package com.example.marsphotos.kotlinexs

fun encodeMsg(msg: String,
              encode: (String) -> String): String {
    return encode(msg)
}

fun enc2(input:String): String = input.reversed()


fun main() {
    val enc1: (String) -> String = { input -> input.toUpperCase() }
    println(encodeMsg("abc", enc1))
    println(
        encodeMsg("abc", ::enc2)
    )
}
