package com.example.firstappcat23

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.firstappcat23.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // lazy and lateinit

    // LAZY variable will get initialised the first time you
    // access to it and then will delegate the same value every time
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    // lateinit is a variable that will be allocated in memory but
    // you need to assign it in a later point in time
    // if it is not assigned you will get an Exception
    lateinit var mString3: String

    // val is not mutable variable and cannot be reassigned
    val mString: String = "string"
    // var is mutable and can be reassigned as many times as you want
    var mString2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mString2?.let { name ->
            name.length
            name.chars()
            name.toCharArray()
        } ?: let {
            // do something else
        }

        // this is how you check if a lateinit variable is initialized
        if(this::mString3.isInitialized) {
            // you do something
        }

        // Elvis operator (?:)
        // allows you to check the nullability of the variable
        // if the variable is null returns the right side
        // otherwise the left side
        val size: Int = mString2?.length ?: 0

        // null safety call
        // checks if the variable is null to return the attribute
        // if it is null assigns the variable to null
        val size2: Int? = mString2?.length

        // Assertion operator (!!.)
        // this is not checking any null value
        // if the variable is null it will throw a Null pointer exception
        // YOU WILL NEVER USE THIS
        val size3: Int = mString2!!.length

        mString3 = "adding value"

        binding.button
        binding.checkBox

        mString2 = "anotherValue"
        mString2 = null

        val button = findViewById<Button>(R.id.button)
        nameOfYourFunction("", 12, false)

        // accessing static method from the companion object
        FirstClass.startSomething()
    }

    // this is the way to create a function in kotlin
    // allows to assign function values like variables (=)
    // and you can use the scope functions
    // RUN, LET, APPLY, ALSO, AND WITH
    fun nameOfYourFunction(name: String, age: Int, enabled: Boolean = true): String = run {
        print("my message")
        mString2 = "another"
        name + age + enabled
    }
}

private val mStringLazy by lazy { // Hello
    println("This is a lazy variable")
    "Hello"
}

fun main() {
    println(mStringLazy)
    // mStringLazy = "Hello"
    println(mStringLazy)
}

// this is a lazy variable
// hello
// hello

// hello
// hello

// This is a lazy variable

// hello
// hello