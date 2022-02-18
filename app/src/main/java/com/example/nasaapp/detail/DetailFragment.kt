package com.example.nasaapp.detail

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.nasaapp.ui.theme.NasaAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class DetailFragment: Fragment() {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply{

            val imageLoader = ImageLoader.Builder(context)
                .componentRegistry {
                    if (Build.VERSION.SDK_INT >= 28) {
                        add(ImageDecoderDecoder(context))
                    } else {
                        add(GifDecoder())
                    }
                }
                .build()

            val informationReceived = DetailFragmentArgs.fromBundle(requireArguments()).information
            viewModel.getPhotosFromFlow(informationReceived.roverName, informationReceived.sol, informationReceived.selectedCamera)
            setContent {
                NasaAppTheme {
                    DetailView(viewModel = viewModel, index = informationReceived.photoIndex, goBack = { goBack() }, imageLoader = imageLoader)
                }
            }
        }
    }

    private fun goBack() {
        val navController = Navigation.findNavController(requireView())
        navController.navigateUp()
    }
}



