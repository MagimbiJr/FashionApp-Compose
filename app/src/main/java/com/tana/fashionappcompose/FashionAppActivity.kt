package com.tana.fashionappcompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.tana.fashionappcompose.components.TabItems
import com.tana.fashionappcompose.screens.home.HomeScreenViewModel
import com.tana.fashionappcompose.ui.theme.FashionAppComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val pages = listOf(
                TabItems.Recommended,
                TabItems.NewModel,
                TabItems.Shows
            )
            val navController = rememberNavController()
            val pagerState = rememberPagerState(pageCount = pages.size)
            val coroutineScope = rememberCoroutineScope()
            val systemUiController = rememberSystemUiController()
            systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
            FashionAppComposeTheme {
                systemUiController.setSystemBarsColor(MaterialTheme.colors.background)
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    FashionAppNavGraph(
                        navController = navController,
                        pagerState = pagerState,
                        pages = pages,
                        coroutineScope = coroutineScope
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: HomeScreenViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    LazyColumn() {
        val fashionistas = viewModel.fashionistas.value
        Log.d("TAG", "Greeting: $fashionistas")
        item(fashionistas) {
            fashionistas.forEach { model ->
                Text(text = model.name)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    FashionAppComposeTheme {
       // Greeting("Android")
    }
}