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
import com.example.nasaapp.BaseFragment
import com.example.nasaapp.ui.theme.NasaAppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalPagerApi
@AndroidEntryPoint
class DetailFragment: BaseFragment() {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val informationReceived = DetailFragmentArgs.fromBundle(requireArguments()).information
        viewModel.getPhotosFromFlow(informationReceived.roverName, informationReceived.sol, informationReceived.selectedCamera)
        return createComposable(null, viewModel, informationReceived.photoIndex) { goBack() }
    }

    private fun goBack() {
        val navController = Navigation.findNavController(requireView())
        navController.navigateUp()
    }
}



