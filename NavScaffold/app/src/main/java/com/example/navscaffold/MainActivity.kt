package com.example.navscaffold

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navscaffold.ui.theme.NavScaffoldTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavScaffoldTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    myAppUI()
                }
            }
        }
    }
}

@Composable
fun myAppUI() {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
    val scope = rememberCoroutineScope()
    val navHostController = rememberNavController()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {Text("Scaffold App")},
                navigationIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_baseline_menu_24),
                        contentDescription = "Menu", modifier = Modifier.clickable {
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                    )
                },
            backgroundColor = MaterialTheme.colors.primary)
        },

        floatingActionButtonPosition = FabPosition.End,
        floatingActionButton = {
            MyFloatingActionButton( onNavigateBack = { navHostController.popBackStack() } )
        },
        drawerContent = {
            MyDrawer(
              onDrawerClose = {
                  scope.launch { scaffoldState.drawerState.close() }
              },
              listOf(
                  NavItem("Home Page", {navHostController.navigate("home")}),
                  NavItem("Page Two", {navHostController.navigate("page2")}),
                  NavItem("Page Three", {navHostController.navigate("page3")})
              )
           )
        },
        content = { MyNavHost( navController = navHostController ) },
        bottomBar = {
            BottomAppBar(backgroundColor = MaterialTheme.colors.primary,
            ) {
                Text("Copyright © 2022, Example.com",textAlign= TextAlign.Center,
                    modifier = Modifier.fillMaxWidth())
            } }
    )
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

        val pages = listOf(
            PageInfo("Home Page", Color.Blue, "home", "page2"),
            PageInfo("Page Two", Color.Red, "page2", "page3"),
            PageInfo("Page Three", Color.Green, "page3", "home")
        )

        for(page in pages ) {
            composable(page.route) {
                MyPage (
                    label = page.label,
                    color = page.color,
                    {navController.navigate(page.nextRoute)}
                )
            }
        }
    }

}

@Composable
fun MyPage(
    label :String,
    color :Color,
    onNavigateNext: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(color)
        ) {
            Text(
                text = label,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.align(Alignment.Center)
            )
        }
        Button(onClick = onNavigateNext, modifier = Modifier.padding(5.dp)) {
            Text("Next", fontSize = 20.sp)
        }
    }
}

@Composable
fun MyDrawer(
    onDrawerClose: () -> Unit,
    navItems: List<NavItem>
) {
    Column(
    ) {
        Box(modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
            .background(MaterialTheme.colors.secondary)
            .padding(10.dp)) {
                Text(
                    text = "Pages",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.secondaryVariant,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
            }

        for( navItem in navItems ) {
            Text(
            text = navItem.label,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(10.dp)
                .clickable(onClick = {
                    navItem.navFunction()
                    onDrawerClose()
                })
            )
        }

    }
}

@Composable
fun MyFloatingActionButton(
    onNavigateBack: () -> Boolean
) {
  FloatingActionButton(onClick = { onNavigateBack() }){
    Icon(
        painter = painterResource(id = R.drawable.arrow_u_left_top_bold),
        contentDescription = "Back"
    )
  }
}

class NavItem( val label :String, val navFunction :() -> Unit ) {}
class PageInfo( val label :String, val color :Color, val route :String, val nextRoute :String ) {}