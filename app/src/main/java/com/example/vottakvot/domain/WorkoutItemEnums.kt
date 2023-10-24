package com.example.vottakvot.domain


data class BodyPart(
    val type: BodyType,
    val count: Int = 0
)

enum class BodyType {
    all, upper_body, press, legs
}