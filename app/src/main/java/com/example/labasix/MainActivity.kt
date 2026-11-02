package com.example.labasix

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.labasix.ui.theme.LabaSixTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LabaSixTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

data class QuizQuestion(
    val text: String,
    val answer: Boolean
)
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    val questions = listOf(
        QuizQuestion("Canberra is the capital of Australia.", true),
        QuizQuestion("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        QuizQuestion("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        QuizQuestion("The source of the Nile River is in Egypt.", false),
        QuizQuestion("The Amazon River is the longest river in the Americas.", true),
        QuizQuestion("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    val questionIndex = remember { mutableStateOf(0) }
    val answered = remember { mutableStateOf(false) }
    val score = remember { mutableStateOf(0) }

    Column(modifier = modifier.fillMaxSize()) {

        Text(
            text = "GeoQuiz",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF6200EE))
                .padding(16.dp),
            color = Color.White
        )

        Text(
            text = questions[questionIndex.value].text,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        if (!answered.value) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        answered.value = true
                        if (questions[questionIndex.value].answer) {
                            score.value++
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE)
                    )
                ) {
                    Text("TRUE")
                }

                Button(
                    onClick = {
                        answered.value = true
                        if (!questions[questionIndex.value].answer) {
                            score.value++
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF6200EE)
                    )
                ) {
                    Text("FALSE")
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = {
                    if (questionIndex.value < questions.lastIndex) {
                        questionIndex.value++
                        answered.value = false
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6200EE)
                )
            ) {
                Text("NEXT  ❯")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LabaSixTheme {
        Greeting("Android")
    }
}