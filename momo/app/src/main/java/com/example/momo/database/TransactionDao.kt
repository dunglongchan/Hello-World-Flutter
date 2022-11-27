package com.example.momo.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TransactionDao {
    @Query("select * from `transaction`")
    suspend fun getAllTransaction(): List<Transaction>

    @Query("select * from `transaction` where transaction_id = :transactionID")
    fun getTransaction(transactionID: String): Transaction

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transaction: Transaction)
}