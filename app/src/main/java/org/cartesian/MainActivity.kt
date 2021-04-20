package org.cartesian

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.cartesian.ui.theme.CartesianscoreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CartesianscoreTheme {
                Test()
            }
        }
    }
}


@Composable

fun Test() {
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
            Text(
                text = "当前分数： ",
                fontWeight = FontWeight.W900,
                modifier = Modifier.padding(15.dp)
            )
            Text(
                text = "总连击数： ",
                fontWeight = FontWeight.W900,
                modifier = Modifier.padding(15.dp)
            )

            Spacer(Modifier.padding(vertical = 50.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ){
                Button(onClick = {

                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFF8EB1CF)
                )) {
                    Text("Excellent")
                }
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

                Button(onClick = {

                }, colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color(0xFFF7EE79)
                )) {
                    Text("Good")
                }
                Spacer(modifier = Modifier.padding(horizontal = 4.dp))

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