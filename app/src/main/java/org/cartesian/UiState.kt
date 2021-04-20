package org.cartesian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiState: ViewModel() {

    var input by mutableStateOf(true)

    var totalNote by mutableStateOf("")

    var currentNote by mutableStateOf(0)

    var excellent by mutableStateOf(0)
    var good by mutableStateOf(0)
    var miss by mutableStateOf(0)
}