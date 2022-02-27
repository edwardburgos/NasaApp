package com.example.nasaapp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import com.example.data.network.model.ResponseState
import com.example.nasaapp.R
import com.example.nasaapp.components.ExtendedPhoto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun DetailView(viewModel: DetailViewModel, index: Int, goBack: () -> Unit, imageLoader: ImageLoader) {
    val photos by viewModel.photos.observeAsState(listOf())
    val responseState by viewModel.responseState.observeAsState(ResponseState.EMPTY)
    val pagerState = rememberPagerState()
    val scrolled by viewModel.scrolled.observeAsState(false)

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
         if (photos.isNotEmpty())  {
            Box {
                HorizontalPager(count = photos.size, state = pagerState,
                    modifier = Modifier.background(MaterialTheme.colors.background).fillMaxHeight()) { page ->
                    ExtendedPhoto(photos.elementAt(page))
                }
                if (!scrolled) {
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
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when (responseState) {
                    ResponseState.ERROR ->  {
                        Icon(Icons.Filled.Warning, contentDescription = "Error Ocurred", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(50.dp).padding(bottom = 16.dp))
                        Text(text = "An error ocurred", style = MaterialTheme.typography.body1.plus(
                            TextStyle (color = MaterialTheme.colors.secondary)
                        ))
                    }
                    ResponseState.INITIAL -> Image(
                        painter = rememberImagePainter(R.drawable.loader, imageLoader = imageLoader),
                        contentDescription = null,
                        modifier = Modifier.width(50.dp)
                    )
                    ResponseState.EMPTY -> {
                        Image(
                            painter = rememberImagePainter(R.drawable.nodata),
                            contentDescription = null,
                            modifier = Modifier.width(80.dp).padding(bottom = 16.dp)
                        )
                        Text(
                            text = "No results found", style = MaterialTheme.typography.body1.plus(
                                TextStyle(color = MaterialTheme.colors.secondary)
                            )
                        )
                    }
                    ResponseState.NO_INTERNET -> {
                        Icon(Icons.Filled.WifiOff, contentDescription = "No Internet Connection", tint = MaterialTheme.colors.secondary, modifier = Modifier.size(50.dp).padding(bottom = 16.dp))
                        Text(text = "No internet connection", style = MaterialTheme.typography.body1.plus(
                            TextStyle (color = MaterialTheme.colors.secondary)
                        ))
                    }
                }
            }
        }
    }

    if (pagerState.pageCount != 0 && pagerState.currentPage == 0 && !scrolled) {
        LaunchedEffect(pagerState.currentPage) {
            viewModel.updateScrolled()
            pagerState.scrollToPage(index)
        }
    }
}