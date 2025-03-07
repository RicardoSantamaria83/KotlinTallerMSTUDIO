package com.mobilestudio.localstorage.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PetProduct")
data class Product(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val price: Int,
    val description: String,
    @ColumnInfo(name = "shortDesc")
    val shortDescription: String,
    val quantity: Int,
    val img: String,
    val isFav: Boolean
)