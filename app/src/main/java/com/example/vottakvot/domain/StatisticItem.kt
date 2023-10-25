package com.example.vottakvot.domain

data class StatisticItem(
    val type: StatisticType,
    val count: Int = 0
)

enum class StatisticType {
    ONE, TWO, THREE, FOUR
}

