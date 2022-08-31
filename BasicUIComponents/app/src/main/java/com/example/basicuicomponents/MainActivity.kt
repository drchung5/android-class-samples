package com.example.basicuicomponents

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.basicuicomponents.ui.theme.BasicUIComponentsTheme
import androidx.compose.ui.unit.sp as sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasicUIComponentsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background,
                ) {
                    DisplayAnnotatedString()
                }
            }
        }
    }
}


@Composable
fun DisplayAnnotatedString() {
        Spacer(
            modifier = Modifier
                .background(Color.Magenta)
                .size(100.dp, 30.dp)
        )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BasicUIComponentsTheme {
        DisplayAnnotatedString()
    }
}