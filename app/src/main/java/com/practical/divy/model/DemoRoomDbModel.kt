package com.practical.divy.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.practical.divy.utils.AppConstants

@Entity(tableName = AppConstants.TABLE_NAMES)
data class DemoRoomDbModel(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var name: String,
)