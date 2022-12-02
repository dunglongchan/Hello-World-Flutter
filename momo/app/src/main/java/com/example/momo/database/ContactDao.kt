package com.example.momo.database

import androidx.room.*

@Dao
interface ContactDao {
    @Query("select * from contact")
    suspend fun getAllContact(): List<Contact>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllContact(listContact: List<Contact>)

    @Update
    fun updateAllContact(contact: Contact)

    @Delete
    fun deleteAllContact(contact: Contact)

    @Query("select * from contact where phoneNumber = :phoneNumber")
    fun getContact(phoneNumber: String): Contact

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertContact(contact: Contact)

    @Query("update contact set momoAccount = :isContactUseMomo where phoneNumber = :phoneNumber")
    fun updateContact(isContactUseMomo: Boolean, phoneNumber: String)

    @Query("delete from contact where phoneNumber = :phoneNumber")
    fun deleteContact(phoneNumber: String)

}