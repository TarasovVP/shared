package com.vnstudio.cleanarchitecturedemo.presentation.details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnstudio.cleanarchitecturedemo.domain.mappers.ForkUIMapper
import com.vnstudio.cleanarchitecturedemo.domain.repositories.DBRepository
import com.vnstudio.cleanarchitecturedemo.presentation.uimodels.ForkUI
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val forkRepository: DBRepository,
    private val forkUIMapper: ForkUIMapper,
): ViewModel() {

    val progressVisibilityLiveData = MutableLiveData<Boolean>()
    val errorLiveData = MutableLiveData<String>()
    val forkLiveData = MutableLiveData<ForkUI>()

    fun getForkById(forkId: Long?) {
        progressVisibilityLiveData.postValue(true)
        viewModelScope.launch(CoroutineExceptionHandler { _, exception ->
            progressVisibilityLiveData.postValue(false)
            errorLiveData.postValue(exception.localizedMessage)
        }) {
            val fork = forkRepository.getForkById(forkId ?: 0)
            forkLiveData.postValue(forkUIMapper.mapToImplModel(fork))
            progressVisibilityLiveData.postValue(false)
        }
    }
}