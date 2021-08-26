package com.example.breezepoc

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}