package com.tana.fashionappcompose.screens.home

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Tune
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.tana.fashionappcompose.components.AppTabs
import com.tana.fashionappcompose.components.FashionAppTopBar
import com.tana.fashionappcompose.components.TabContent
import com.tana.fashionappcompose.components.TabItems
import com.tana.fashionappcompose.ui.theme.LightPurple
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    pagerState: PagerState,
    pages: List<TabItems>,
    coroutineScope: CoroutineScope,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { FashionAppTopBar() }
    ) {
        LazyColumn(
            modifier = modifier.padding(16.dp)
        ) {
            item { Spacer(modifier = modifier.height(15.dp))
                Text(
                    text = "Fashion Week",
                    style = MaterialTheme.typography.h4,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemInDarkTheme()) MaterialTheme.colors.onBackground else LightPurple
                )
            }
            item {
                Spacer(modifier = modifier.height(5.dp))
                Text(
                    text = "2021 Fashion show in Dar",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.W500,
                    fontSize = 18.sp
                )
            }
            item {
                Spacer(modifier = modifier.height(10.dp))
                ExploreRow(modifier = modifier)
            }
            item {
                Spacer(modifier = modifier.height(15.dp))
                Column {
                    AppTabs(
                        pages = pages,
                        pagerState = pagerState,
                        coroutineScope = coroutineScope
                    )
                    TabContent(pages = pages, pagerState = pagerState)
                }
            }
        }
    }
}

@Composable
fun ExploreRow(modifier: Modifier) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Explore",
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            fontSize = 31.sp
        )
        IconButton(onClick = { /*TODO*/ }) {
            Icon(
                imageVector = Icons.Filled.Tune, contentDescription = "explore button"
            )

        }
    }
}
