package com.jpoveda.files.data.persons

class Person(val name: String,val lastName :String,val age: Int) {
    override fun toString(): String {
        return "$name;$lastName;$age"
    }
}