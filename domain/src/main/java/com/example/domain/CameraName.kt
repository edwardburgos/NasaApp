package com.example.domain

enum class CameraName {
    All, FHAZ, RHAZ, MAST, CHEMCAM, MAHLI, MARDI, NAVCAM, PANCAM, MINITES;

    fun lowercase(): String {
        return super.toString().lowercase()
    }
}