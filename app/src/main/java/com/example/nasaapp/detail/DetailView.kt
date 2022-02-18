package com.example.nasaapp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.nasaapp.R
import com.example.nasaapp.components.ExtendedPhoto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun DetailView(viewModel: DetailViewModel, index: Int, goBack: () -> Unit, imageLoader: ImageLoader) {
    val photos by viewModel.photos.observeAsState(listOf())
    val pagerState = rememberPagerState()
    val scrolled = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar (
                title = {},
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Go Back", tint = MaterialTheme.colors.secondary)
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        }
    ) {
        Box {
            HorizontalPager(count = photos.size, state = pagerState,
                modifier = Modifier.background(MaterialTheme.colors.background).fillMaxHeight()) { page ->
                ExtendedPhoto(photos.elementAt(page))
            }
            if (!scrolled.value) {
                Column(
                    modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = rememberImagePainter(R.drawable.loader, imageLoader = imageLoader),
                        contentDescription = null,
                        modifier = Modifier.width(50.dp)
                    )
                }
            }
        }
    }

    if (pagerState.pageCount != 0 && pagerState.currentPage == 0 && !scrolled.value) {
        LaunchedEffect(pagerState.currentPage) {
            scrolled.value = true
            pagerState.scrollToPage(index)
        }
    }
}