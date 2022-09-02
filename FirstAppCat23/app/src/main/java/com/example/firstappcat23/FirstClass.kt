package com.example.firstappcat23

// Primary constructor
class FirstClass constructor (
    var name: String,
    var age: String,
    var enabled: String?,
    var address: String?
) {

    // Order of execution
    // primary constructor
    // init block
    // secondary constructor

    init {
        println(name)
        name = "Name changed"
    }

    // secondary constructors
    constructor(name: String, age: String): this(name, age, null, "")
    constructor(name: String): this(name, "", "", "")
    constructor(name: String, age: String, address: String): this(name, age, "", address)

    private val firstDataClass by lazy {
        MyFirstDataClass(
            enabled = true
        )
    }

    // THis is where i am keeping the static variables
    // and static methods
    companion object {
        // const val it is assigned at compile time
        const val something: String = "CONSTANT"
        // val is assigned at runtime
        val name: String = "name"

        fun startSomething() {

        }
    }
}