package com.example.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigation.ui.theme.NavigationTheme
import java.security.AccessController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MyNavHost()
                }
            }
        }
    }
}

@Composable
fun MyNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "home"
) {
    NavHost(
        modifier = Modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            Home(
                onNavigateToPageTwo = {navController.navigate("pageTwo")}
            )
        }
        composable("pageTwo") {
            PageTwo(
                onNavigateToHome = {navController.navigate("home")},
                onNavigateBack = {navController.popBackStack()},
            )
        }
    }

}

@Composable
fun Home(
    onNavigateToPageTwo: () -> Unit
) {
    Column() {
        Text( "Home", fontSize = 30.sp, modifier = Modifier.padding(10.dp) )
        Button(onClick = onNavigateToPageTwo, modifier = Modifier.padding(10.dp)) {
            Text("Goto Page 2", fontSize = 30.sp)
        }
    }
}

@Composable
fun PageTwo(
    onNavigateToHome: () -> Unit,
    onNavigateBack: () -> Unit
) {
    Column{
        Text( "Page 2", color = Color.Red, fontSize = 30.sp, modifier = Modifier.padding(10.dp) )
//        Button(onClick = onNavigateToHome, modifier = Modifier.padding(10.dp)) {
//            Text("Goto Home", fontSize = 30.sp)
//        }
        Button(onClick = onNavigateBack, modifier = Modifier.padding(10.dp)) {
            Text("Back", fontSize = 30.sp)
        }
    }

}