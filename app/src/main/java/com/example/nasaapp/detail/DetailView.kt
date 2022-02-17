package com.example.nasaapp.detail

import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import com.example.nasaapp.components.ExtendedPhoto
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun DetailView(viewModel: DetailViewModel, index: Int) {
    val photos by viewModel.photos.observeAsState(listOf())
    val pagerState = rememberPagerState()
    val scrolled = remember { mutableStateOf(false) }

    HorizontalPager(count = photos.size, state = pagerState) { page ->
        ExtendedPhoto(photos.elementAt(page))
    }

    if (pagerState.pageCount != 0 && pagerState.currentPage == 0 && !scrolled.value) {
        LaunchedEffect(pagerState.currentPage) {
            scrolled.value = true
            pagerState.scrollToPage(index)
        }
    }
}