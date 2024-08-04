package com.vnteam.architecturetemplates.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vnteam.architecturetemplates.domain.usecase.DetailsUseCase
import com.vnteam.architecturetemplates.presentation.intents.DetailsIntent
import com.vnteam.architecturetemplates.presentation.mappers.ForkUIMapper
import com.vnteam.architecturetemplates.presentation.states.DetailsViewState
import com.vnteam.architecturetemplates.presentation.states.screen.InfoMessageState
import com.vnteam.architecturetemplates.presentation.states.screen.ScreenState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val detailsUseCase: DetailsUseCase,
    private val forkUIMapper: ForkUIMapper
): ViewModel() {

    private val _state = MutableStateFlow(DetailsViewState())
    val state: StateFlow<DetailsViewState> = _state.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        _state.value = state.value.copy(isLoading = false, infoMessage = mutableStateOf( InfoMessageState(message = exception.message.orEmpty(), isError = true)))
    }

    fun processIntent(intent: DetailsIntent) {
        when (intent) {
            is DetailsIntent.LoadFork -> getForkById(intent.forkId, intent.isUpdated)
        }
    }

    private fun getForkById(forkId: String?, isUpdated: Boolean) {
        if (isUpdated) {
            _state.value = _state.value.copy(fork = null)
        }
        viewModelScope.launch(exceptionHandler) {
            detailsUseCase.getForkById(forkId.orEmpty()).collect { fork ->
                _state.value = _state.value.copy(fork = fork?.let { forkUIMapper.mapToImplModel(it) }, isLoading = false)
            }
        }
    }
}