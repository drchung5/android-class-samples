package com.example.statefulcomponents

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.statefulcomponents.ui.theme.StatefulComponentsTheme
import androidx.compose.material.TextField as TextField

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StatefulComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Greeting()
                }
            }
        }
    }
}

@Composable
fun Greeting() {
    var sliderValue by remember {
        mutableStateOf(25.0f)
    }
    Text(
        text = sliderValue.toInt().toString(),
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center)
    Slider(
        value = sliderValue,
        onValueChange = {sliderValue = it},
        valueRange = -100f..100f,
        modifier = Modifier.padding(20.dp))
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    StatefulComponentsTheme {
        Greeting()
    }
}