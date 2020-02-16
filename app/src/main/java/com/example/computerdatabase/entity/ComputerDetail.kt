package com.example.computerdatabase.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ComputerDetail(
    @PrimaryKey
    var id: Int,
    var name: String,
    var introduced: String?,
    var discounted: String?,
    var imageUrl: String?,
    @Embedded(prefix = "company") var company: Company?,
    var description: String?
)