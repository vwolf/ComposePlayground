package com.example.composeplayground.secondScreen


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SecondScreenViewModel : ViewModel() {

    private val _sharedUiState = MutableSharedFlow<SecondScreenUiState>()
    val sharedUiState = _sharedUiState.asSharedFlow()

    fun setBottomSheetState(state: Boolean) {
        viewModelScope.launch {
            _sharedUiState.emit(
                SecondScreenUiState(
                    bottomSheetState = state
                )
            )
        }
    }


}

data class SecondScreenUiState(
    val bottomSheetState: Boolean = false
)