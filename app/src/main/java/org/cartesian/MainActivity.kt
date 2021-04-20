package org.cartesian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
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
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.padding(vertical = 25.dp))
            Button(onClick = {
                viewModel.input = false
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

    var score by remember{ mutableStateOf(0)}

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
                WorkSpacer(score,viewModel)
                Spacer(Modifier.padding(vertical = 50.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ){
                    Button(onClick = {
                        score += 100

                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFF8EB1CF)
                    )) {
                        Text("Excellent")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                    Button(onClick = {




                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF7EE79)
                    )) {
                        Text("Good")
                    }
                    Spacer(modifier = Modifier.padding(horizontal = 10.dp))

                    Button(onClick = {



                    }, colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color(0xFFF78080)
                    )) {
                        Text("Miss")
                    }
                }
            }
        }
    }
}

@Composable
fun WorkSpacer(
    score:Int,
    viewModel: UiState
){
    Text(
        text = "当前分数： $score",
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
        text = "Excellent： 245x",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFF8EB1CF)
    )

    Text(
        text = "Good： 245x",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFFF7EE79)
    )
    Text(
        text = "Miss： 245x",
        fontWeight = FontWeight.W900,
        modifier = Modifier.padding(15.dp),
        color = Color(0xFFF78080)
    )
}