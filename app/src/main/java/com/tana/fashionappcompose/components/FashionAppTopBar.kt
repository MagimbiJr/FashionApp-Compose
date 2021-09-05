package com.tana.fashionappcompose.components

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Sort
import androidx.compose.runtime.Composable
import com.tana.fashionappcompose.ui.theme.DarkBlue
import com.tana.fashionappcompose.ui.theme.LightPurple

@Composable
fun FashionAppTopBar() {
    Surface() {
        TopAppBar(
            title = { Text(text = "") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Sort, contentDescription = "Navigation Icon")
                }
            },
            // backgroundColor = DarkBlue,
            actions = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        )
    }
}