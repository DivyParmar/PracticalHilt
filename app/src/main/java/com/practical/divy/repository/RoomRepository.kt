package com.practical.divy.repository

import com.practical.divy.database.AppDao
import com.practical.divy.model.DemoRoomDbModel
import javax.inject.Inject


class RoomRepository @Inject constructor(private val appDao: AppDao) {
    suspend fun insertName(demoRoomDbModel: DemoRoomDbModel) = appDao.insertTask(demoRoomDbModel)
}