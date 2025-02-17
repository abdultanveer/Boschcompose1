package com.example.boschcompose1.kotlinexs

import com.example.boschcompose1.Address
import com.example.boschcompose1.Student

fun main() {
    var numberOfBooks: Int
    //= "good brake"
    var abdul = Student("abdul",123,Address())
    var ansari = Student("abdul",123,Address())

    println(abdul==ansari)
    println(abdul===ansari)

    var length = 2.5
    var len = 10
    var name = "abdul"
    val oneMillion:Int = 1_000_000
    println(oneMillion)

    println(  2.times(3))
    infix fun Int.times(str: String) = str.repeat(this)        // 1
    println(2 times "Bye ")                                    // 2

    val mpair = "Ferrari" to "Katrina"                          // 3
    println(mpair)

    infix fun String.onto(other: String) = Pair(this, other)   // 4
    val myPair = "McLaren" onto "Lucas"
    println(myPair)

    val sophia = Person("Sophia")
    val claudia = Person("Claudia")
    sophia likes claudia                                       // 5
}

class Person(val name: String) {
    val likedPeople = mutableListOf<Person>()
    infix fun likes(other: Person) { likedPeople.add(other) }  // 6
}