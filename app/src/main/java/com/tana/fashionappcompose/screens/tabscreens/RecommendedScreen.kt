package com.tana.fashionappcompose.screens.tabscreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.lifecycle.viewmodel.compose.*
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import com.tana.fashionappcompose.screens.home.HomeScreenViewModel
import com.tana.fashionappcompose.ui.theme.Gray
import com.tana.fashionappcompose.ui.theme.Shapes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun RecommendedScreen(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    modifier: Modifier = Modifier,
) {
    Column {
        Spacer(modifier = modifier.height(20.dp))
        ModelsList(modifier = modifier)
        Spacer(modifier = modifier.height(20.dp))
        Gallery(coroutineScope = coroutineScope)
    }
}

@Composable
fun ModelsList(
    modifier: Modifier,
    viewModel: HomeScreenViewModel = viewModel()
) {
    val fashionistas = viewModel.fashionistas.value
    LazyRow() {
        item(fashionistas) {
            fashionistas.forEach { fashionista ->
                Column(
                    modifier = modifier.padding(12.dp)
                ) {
                    Box(
                        modifier = modifier
                            .shadow(5.dp, RoundedCornerShape(25.dp))
                            .background(viewModel.randomColors(), RoundedCornerShape(25.dp))
                            .clip(RoundedCornerShape(25.dp))
                    ) {
                        val painter = rememberImagePainter(data = fashionista.photo)
                        Image(
                            painter = painter,
                            contentDescription = "Model Profile",
                            modifier = modifier
                                .width(160.dp)
                                .height(210.dp),
                            contentScale = ContentScale.Crop
                        )
                    }
                    Spacer(modifier = modifier.height(12.dp))
                    Text(
                        text = fashionista.name,
                        style = MaterialTheme.typography.subtitle2,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = modifier.height(6.dp))
                    Text(
                        text = fashionista.city,
                        style = MaterialTheme.typography.subtitle2,
                        color = if (isSystemInDarkTheme()) Gray else MaterialTheme.colors.onBackground,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Gallery(
    coroutineScope: CoroutineScope,
    viewModel: HomeScreenViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val galleryList = viewModel.gallery.value
    val pagerState = rememberPagerState(pageCount = galleryList.size)

    HorizontalPager(state = pagerState) { page ->
        Card(
            modifier = modifier
                .height(230.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(25.dp))
        ) {
            val painter = rememberImagePainter(galleryList[0].imageUrl)
            Image(
                painter = painter,
                contentDescription = "Gallery photo",
                contentScale = ContentScale.Crop
            )
        }
    }

}