package com.uzun.pseudosendy.presentation.ui.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UiState, A : UiSideEffect, E : UiEvent>(
    initialState: S,
) : ViewModel() {

    private val _uiState = MutableStateFlow<S>(initialState)
    val uiState = _uiState.asStateFlow()

    private val _effect: Channel<A> = Channel()
    val effect = _effect.receiveAsFlow()

    private val currentState: S
        get() = _uiState.value

    open fun setEvent(event: E) = viewModelScope.launch { handleEvent(event) }

    protected abstract suspend fun handleEvent(event: E)

    protected fun setState(reduce: S.() -> S) {
        val state = currentState.reduce()
        _uiState.value = state
    }

    protected fun setEffect(vararg builder: A) {
        for (effectValue in builder)
            viewModelScope.launch { _effect.send(effectValue) }
    }
}
