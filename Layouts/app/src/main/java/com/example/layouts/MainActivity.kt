package com.example.layouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.layouts.ui.theme.LayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LayoutsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    myPoetryDisplay()
                }
            }
        }
    }
}

val poem = """Some say the world will end in fire,
             |  Some say in ice.
             |From what I've tasted of desire
             |  I hold with those who favor fire.
             |But if it were to perish twice,
             |  I know enough of hate
             |To say that for destruction ice
             |  Is also great
             |  And would suffice.""".trimMargin()

@Composable
fun myPoetryDisplay() {
    Column {
        myTitle("Fire and Ice")
        myPoem(poem, Modifier.fillMaxSize().weight(0.5f))
        myFooter("December 1920", "Robert Frost")

    }
}

@Composable
fun myTitle( title: String ) {
    Text(
        title,
        color=Color.Blue,
        fontSize = 40.sp,
        fontWeight= FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .background(Color.LightGray)
            .fillMaxWidth()
    )
}

@Composable
fun myPoem(text: String, modifier: Modifier = Modifier ) {
    Box(
        modifier
    ) {
        Text(
            text,
            fontSize = 25.sp,
            modifier = Modifier.padding(8.dp))
    }
}

@Composable
fun myFooter( date: String, author: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
    ) {
        Text(text = date, modifier = Modifier.padding(4.dp))
        Text(text = """by $author""",
            textAlign = TextAlign.End,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth())
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LayoutsTheme {
        Column {
            myTitle("Fire and Ice")
            myPoem(poem, Modifier.fillMaxSize().weight(0.5f))
            myFooter("December 1920", "Robert Frost")

        }
    }
}