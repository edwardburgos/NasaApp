package com.example.domain

data class Rover (
    var id: Int,
    var name: RoverName,
    var landing_date: String,
    var launch_date: String,
    var status: String
)