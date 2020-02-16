package com.example.computerdatabase.entity

import androidx.room.Entity

@Entity
data class Company(
    val id: Int,
    val name: String
)