package com.practical.divy.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.practical.divy.api.ApiResult
import com.practical.divy.model.DogResponse
import com.practical.divy.repository.ApiRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(private val repository: ApiRepositoryImpl, application: Application) : AndroidViewModel(application) {

    private val _dogResponse: MutableLiveData<ApiResult<DogResponse>> = MutableLiveData()
    val dogResponse: LiveData<ApiResult<DogResponse>> = _dogResponse

    fun callDogApi() {
        viewModelScope.launch {
            repository.getDog().collect { values ->
                _dogResponse.value = values
            }
        }
    }
}