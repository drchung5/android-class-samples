package com.example.complexconstraintlayout

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.complexconstraintlayout.ui.theme.ComplexConstraintLayoutTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComplexConstraintLayoutTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    layoutTest()
                }
            }
        }
    }
}

@Composable
fun layoutTest() {

    ConstraintLayout {
        val (first, last, submit, name, reset) = createRefs()

        var show by remember { mutableStateOf(false) }
        var firstname by remember { mutableStateOf("") }
        var lastname by remember { mutableStateOf("") }

        OutlinedTextField(
            enabled = !show,
            value = firstname,
            onValueChange = {firstname = it},
            label = {Text("First name")},
            modifier = Modifier
                .constrainAs(first) {
                    top.linkTo(parent.top, 10.dp)
                    start.linkTo(parent.start)
                    end.linkTo(submit.start)
                }
                .size(250.dp, 65.dp)
        )
        OutlinedTextField(
            enabled = !show,
            value = lastname,
            onValueChange = {lastname = it},
            label = {Text("Last name")},
            modifier = Modifier
                .constrainAs(last) {
                    top.linkTo(first.bottom, 10.dp)
                    start.linkTo(first.start)
                    end.linkTo(first.end)
                }
                .size(250.dp, 65.dp)
        )

        Button(
            enabled = lastname != "" && firstname != "" && !show,
            onClick = { show = true },
            modifier = Modifier
                .padding(4.dp)
                .constrainAs(submit) {
                    top.linkTo(parent.top, 13.dp)
                    start.linkTo(first.end)
                    end.linkTo(parent.end)
                }
                .height(133.dp)
        ) {
            Text( "Submit" )
        }

        if(show && lastname != "" && firstname != "") {
            Text(
                """$firstname $lastname""",
                modifier = Modifier
                    .constrainAs(name) {
                        top.linkTo(last.bottom, 10.dp)
                        start.linkTo(last.start)
                        end.linkTo(last.end)
                    }
                    .border(
                        1.dp,
                        Color.LightGray,
                        shape = RoundedCornerShape(5.dp)
                    )
                    .size(250.dp, 65.dp)
                    .padding(start = 10.dp, top = 20.dp))

            Button(
                onClick = {
                    show = false
                    firstname = ""
                    lastname = ""
                },
                modifier = Modifier
                    .padding(4.dp)
                    .constrainAs(reset) {
                        top.linkTo(submit.bottom, 13.dp)
                        start.linkTo(submit.start)
                        end.linkTo(submit.end)
                    }
            ) {
                Text("Reset")
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComplexConstraintLayoutTheme {
        layoutTest()
    }
}

//ConstraintLayout(){
//    val (centerButton, textField)= createRefs()
//    Button(
//        onClick = {},
//        Modifier.width(200.dp).constrainAs(centerButton){
//            top.linkTo(parent.top, 40.dp)
//            start.linkTo(parent.start)
//            end.linkTo(parent.end)
//        }
//    ){
//        Text("Press Me")
//    }
//    Text("Text",Modifier.constrainAs(textField){
//        top.linkTo(centerButton.bottom, 40
//            .dp)
//        start.linkTo(parent.start)
//        end.linkTo(parent.end)
//    })
//}