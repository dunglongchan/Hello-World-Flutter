package com.example.momo.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.momo.common.Constant

@Database(entities = [Contact::class, Transaction::class], version = 2, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    companion object {
        var instance: AppDataBase? = null

        fun getAppDatabase(context: Context): AppDataBase? {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java, Constant.APP_DB_NAME
                )
                    .allowMainThreadQueries().addMigrations(MIGRATION_1_2).build()
            }
            return instance
        }

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS `contact` (`name` TEXT, `phoneNumber` TEXT, `momoAccount` BOOLEAN DEFAULT 'false',PRIMARY KEY(`phoneNumber`))")
                database.execSQL("CREATE TABLE IF NOT EXISTS `transaction` (`transaction_id` TEXT NOT NULL, `sender` TEXT NOT NULL, `receiver` TEXT NOT NULL, `date` TEXT NOT NULL,`value` TEXT NOT NULL,PRIMARY KEY(`transaction_id`))")

            }
        }
    }

    abstract fun ContactDao(): ContactDao

    abstract fun TransactionDao(): TransactionDao
}