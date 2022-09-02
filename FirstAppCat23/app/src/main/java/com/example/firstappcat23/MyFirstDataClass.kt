package com.example.firstappcat23

data class MyFirstDataClass(
    var name: String? = null,
    var age: Int? = null,
    val enabled: Boolean = false
)

// data classes hold properties only
// they provide setters and getters by default
// they provide the toString(),
// hashCode(), equals(), and copy() by default
