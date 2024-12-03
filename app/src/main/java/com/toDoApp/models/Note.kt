package com.toDoApp.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sell_table")
data class Sell(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val price: Double,
    val category: String,
    val description: String,
)






