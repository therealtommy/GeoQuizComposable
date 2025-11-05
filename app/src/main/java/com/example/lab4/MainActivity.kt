package com.example.lab4

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.lab4.ui.theme.Lab4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab4Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GeoQuizApp(modifier = Modifier.padding(innerPadding))
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
fun GeoQuizApp(modifier: Modifier) {
    val questions = listOf(
        Question("Canberra is the capital of Australia.", true),
        Question("The Pacific Ocean is larger than the Atlantic Ocean.", true),
        Question("The Suez Canal connects the Red Sea and the Indian Ocean.", false),
        Question("The source of the Nile River is in Egypt.", false),
        Question("The Amazon River is the longest river in the Americas.", true),
        Question("Lake Baikal is the world's oldest and deepest freshwater lake.", true)
    )
}