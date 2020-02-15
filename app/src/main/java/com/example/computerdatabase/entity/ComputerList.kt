package com.example.computerdatabase.entity

data class ComputerList(
    val items: List<Computer>,
    val page: Int,
    val offset: Int,
    val total: Int
)