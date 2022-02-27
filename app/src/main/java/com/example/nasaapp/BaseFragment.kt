package com.example.nasaapp

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.nasaapp.detail.DetailView
import com.example.nasaapp.detail.DetailViewModel
import com.example.nasaapp.home.HomeView
import com.example.nasaapp.home.HomeViewModel
import com.example.nasaapp.ui.theme.NasaAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

open class BaseFragment: Fragment() {

    @ExperimentalPagerApi
    fun createComposable(
        homeviewModel: HomeViewModel?,
        detailviewModel: DetailViewModel?,
        index: Int?,
        goBack: (() -> Unit)?
    ): ComposeView {
        return ComposeView(requireContext()).apply {

            val imageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder(context))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()

            setContent {
                NasaAppTheme {
                    if (homeviewModel != null) {
                        HomeView(viewModel = homeviewModel, imageLoader = imageLoader)
                    } else if (detailviewModel != null && index != null && goBack != null) {
                        DetailView(viewModel = detailviewModel, index = index, goBack = goBack, imageLoader = imageLoader)
                    }
                }
            }
        }
    }
}