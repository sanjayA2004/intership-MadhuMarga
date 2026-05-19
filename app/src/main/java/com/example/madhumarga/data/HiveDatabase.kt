package com.example.madhumarga.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Hive::class, HiveLog::class], version = 2)
abstract class HiveDatabase : RoomDatabase() {
    abstract fun hiveDao(): HiveDao

    companion object {
        @Volatile
        private var INSTANCE: HiveDatabase? = null

        fun getDatabase(context: Context): HiveDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HiveDatabase::class.java,
                    "hive_database"
                )
                    .fallbackToDestructiveMigration(true)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
