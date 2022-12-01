package com.example.momo.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.momo.common.Constant


@Entity(tableName = "transaction")
data class Transaction(
    @PrimaryKey
    @ColumnInfo(name = Constant.TRANSACTION_ID) val transactionId: String,
    @ColumnInfo(name = Constant.SENDER) val sender: String,
    @ColumnInfo(name = Constant.RECEIVER) val receiver: String,
    @ColumnInfo(name = Constant.DATE) val date: String,
    @ColumnInfo(name = Constant.VALUE) val value: String,
) {
}