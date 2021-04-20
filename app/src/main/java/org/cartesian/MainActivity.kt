package org.cartesian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cartesian.ui.theme.CartesianscoreTheme

class MainActivity : ComponentActivity() {

    private val viewModel:UiState by viewModels()


    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartesianscoreTheme {
                Input(viewModel)
                Test(viewModel)
            }
        }
    }

    override fun onBackPressed() {
        when(true){
            !viewModel.input -> viewModel.returnInput()
            else -> super.onBackPressed()
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun Input(
    viewModel:UiState
){

    AnimatedVisibility(visible = viewModel.input) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                value = viewModel.totalNote,
                onValueChange = {
                    viewModel.totalNote = it
                },
                label = {
                    Text("输入音符总数")
                },
            )
            Spacer(modifier = Modifier.padding(vertical = 25.dp))
            Button(onClick = {
                viewModel.input = false
                viewModel.totalScore = (
                        (viewModel.totalNote.toInt() - 5) * 1125
                                + 825 + 900 + 975 + 1050 + 1125
                )


            }) {
                Text("确认")
            }
        }
    }

}


@ExperimentalAnimationApi
@Composable
fun Test(
    viewModel:UiState
) {

    AnimatedVisibility(visible = !viewModel.input) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp),
                verticalArrangement = Arrangement.Top
            ) {
                WorkSpacer(viewModel.score,viewModel)
                Spacer(Modifier.padding(vertical = 50.dp))
                if(viewModel.totalNote != "") {
                    AnimatedVisibility(visible = viewModel.compare( viewModel.totalCombo, viewModel.totalNote.toInt())
                    ) {
                        Buttons(viewModel)
                    }
                    AnimatedVisibility(visible = !(viewModel.compare( viewModel.totalCombo, viewModel.totalNote.toInt()))
                    ) {
                        Result(viewModel)
                    }
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@Composable
fun WorkSpacer(
    score:Int,
    viewModel: UiState
){

    Text(
        text = "总分： ${viewModel.totalScore}",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp)
    )

    Text(
        text = "当前分数： ${viewModel.currentScore}",
        fontWeight = FontWeight.W900,
        modifier = Modifier
            .padding(15.dp)
            .animateContentSize(),
    )

    Text(
        text = "总音符数量： ${viewModel.totalNote}",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp)
    )

    Text(
        text = "Excellent： ${viewModel.excellent}",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFF8EB1CF)
    )

    Text(
        text = "Good： ${viewModel.good}",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFFF7EE79)
    )
    Text(
        text = "Miss： ${viewModel.miss}",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFFF78080)
    )
}

@Composable
fun Buttons(
    viewModel: UiState
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ){
        Button(onClick = {
            viewModel.currentCombo++
            viewModel.totalCombo++
            viewModel.algorithm(1)
            viewModel.excellent +=1

        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFF8EB1CF)
        ), modifier = Modifier.size(120.dp)
        ) {
            Text("Excellent")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))

        Button(onClick = {
            viewModel.currentCombo++
            viewModel.totalCombo++
            viewModel.algorithm(2)
            viewModel.good +=1

        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF7EE79)
        ),modifier = Modifier.size(120.dp)
        ) {
            Text("Good")
        }
        Spacer(modifier = Modifier.padding(horizontal = 10.dp))

        Button(onClick = {
            viewModel.currentCombo = 0
            viewModel.totalCombo++
            viewModel.miss += 1

        }, colors = ButtonDefaults.buttonColors(
            backgroundColor = Color(0xFFF78080)
        ),modifier = Modifier.size(120.dp)
        ) {
            Text("Miss")
        }
    }
}

@Composable
fun Result(
    viewModel: UiState
) {
    viewModel.result()
    Column (
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(viewModel.resultText)
    }
}