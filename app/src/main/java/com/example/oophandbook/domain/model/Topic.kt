package com.example.oophandbook.domain.model

data class Topic(
    val id: Int,
    val name: String,
    val units: List<UnitBase>
)