package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lab4.ui.theme.Lab4Theme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeoQuizApp(innerPadding)
                }
            }
        }
    }
}

data class Question(
    val text: String,
    val answer: Boolean
)
@Composable
fun GeoQuizApp(innerPadding: PaddingValues) {
    val questions = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var isAnswered by remember { mutableStateOf(false) }
    var showResult by remember{ mutableStateOf(false) }

    val currentQuestion = questions[currentIndex]
    val isLastQuestion = currentIndex == questions.size - 1

    Column(modifier = Modifier.fillMaxSize().padding(innerPadding)) {
            Text(
                text = currentQuestion.text,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(vertical = 32.dp),
            )

            if (!isAnswered) {
                Button(
                    onClick = {
                        isAnswered = true
                        if (currentQuestion.answer) score++
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("True")
                }

                Button(
                    onClick = {
                        isAnswered = true
                        if (!currentQuestion.answer) score++
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("False")
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (isAnswered && !isLastQuestion) {
                Button(
                    onClick = {
                        currentIndex++
                        isAnswered = false
                    }
                ) {
                    Text("Next Question")
                }
            }

            if (isAnswered && isLastQuestion) {
                Button(
                    onClick = { showResult = true }
                ) {
                    Text("Show Results")
                }
            }
        }
        if (showResult) {
            AlertDialog(
                onDismissRequest = { showResult = false },
                title = { Text("Quiz Finished!") },
                text = {
                    Text("Your score: $score out of ${questions.size}\n")
                },
                confirmButton = {
                    Button(
                        onClick = {
                            showResult = false
                            currentIndex = 0
                            score = 0
                            isAnswered = false
                        }
                    ) {
                        Text("Restart Quiz")
                    }
                }
            )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab4Theme {
        GeoQuizApp (innerPadding = PaddingValues())
    }
}