package com.example.computerdatabase.entity

data class ComputerDetail(
    val id: Int,
    val name: String,
    val introduced: String?,
    val discounted: String?,
    val imageUrl: String?,
    val company: Company?,
    val description: String
)