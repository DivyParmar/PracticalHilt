package com.practical.divy.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.practical.divy.model.DemoRoomDbModel
import com.practical.divy.repository.RoomRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: RoomRepositoryImpl,
    application: Application
) : AndroidViewModel(application) {


    private val _insertNameResponse: MutableLiveData<Long> = MutableLiveData()
    val insertNameResponse: LiveData<Long> = _insertNameResponse

    fun insertName(name: String) {
        viewModelScope.launch {
            _insertNameResponse.postValue(repository.insertName(DemoRoomDbModel(name = name)))
        }
    }
}