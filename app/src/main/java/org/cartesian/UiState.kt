package org.cartesian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiState: ViewModel() {

    var input by mutableStateOf(true)

    var totalNote by mutableStateOf("")

}