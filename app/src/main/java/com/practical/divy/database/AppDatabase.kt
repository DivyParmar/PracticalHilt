package com.practical.divy.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.practical.divy.model.DemoRoomDbModel

@Database(entities = [DemoRoomDbModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}