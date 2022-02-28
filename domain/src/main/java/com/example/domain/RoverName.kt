package com.example.domain

enum class RoverName {
    Curiosity, Opportunity, Spirit;

    fun lowercase(): String {
        return super.toString().lowercase()
    }
}