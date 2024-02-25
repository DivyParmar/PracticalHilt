package com.practical.divy.repository

import com.practical.divy.api.ApiResultParser
import com.practical.divy.model.DemoRoomDbModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class RoomRepositoryImpl @Inject constructor(private val roomRepository: RoomRepository) :
    ApiResultParser() {

    suspend fun insertName(demoRoomDbModel: DemoRoomDbModel): Long{
        return roomRepository.insertName(demoRoomDbModel)
    }
}