package org.cartesian

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class UiState: ViewModel() {

    var input by mutableStateOf(true)

    var totalNote by mutableStateOf("")
    var totalScore by mutableStateOf(0)

    var currentScore by mutableStateOf(0.0)

    var score by mutableStateOf(0)
    var excellent by mutableStateOf(0)
    var good by mutableStateOf(0)
    var miss by mutableStateOf(0)

    var currentCombo by mutableStateOf(0)

    var totalCombo by mutableStateOf(0)
    var resultText by mutableStateOf("")



    fun compare(a:Int, b:Int): Boolean {
        var result = false
        when(true){
            a > b -> result = false
            a < b -> result =  true
            a == b -> result =  false
        }
        return result
    }

    fun algorithm(
        noteType: Int
    ) {
        currentScore += (1 + minOf(currentCombo, 5) / 10.0 ) * (when(noteType) {
            1 -> 750
            else -> 550
        })
    }

    fun result() {

        resultText = when(true) {
            excellent == totalNote.toInt() -> "您满分了("
            currentScore > (totalScore * 0.87) -> "您 S 了("
            currentScore > (totalScore * 0.82) -> "您 A 了("
            currentScore > (totalScore * 0.77) -> "您 B 了("
            currentScore > (totalScore * 0.72) -> "您 C 了("
            else -> "您 D 了("
        }
    }

    fun returnInput() {
        input = true

        totalNote = ""
        totalScore = 0

        currentScore = 0.0

        score = 0
        excellent = 0
        good = 0
        miss = 0

        currentCombo = 0

        totalCombo = 0
        resultText = ""
    }
}
