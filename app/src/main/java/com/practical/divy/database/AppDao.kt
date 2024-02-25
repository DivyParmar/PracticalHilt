package com.practical.divy.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.practical.divy.model.DemoRoomDbModel

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(taskModel: DemoRoomDbModel): Long
}