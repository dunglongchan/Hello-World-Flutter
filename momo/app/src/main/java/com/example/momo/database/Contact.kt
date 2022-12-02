package com.example.momo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact")
data class Contact(
    @ColumnInfo(name = "name") val name: String,
    @PrimaryKey
    @ColumnInfo(name = "phoneNumber") val phoneNumber: String,
    @ColumnInfo(name = "momoAccount") var isContactUseMomo: Boolean
) {
}