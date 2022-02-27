package com.example.nasaapp.home

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
class HomeFragment : BaseFragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return createComposable(viewModel,null,null,null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel.navigateToSelectedPhoto.observe(viewLifecycleOwner, { photoIndex ->
            if (null != photoIndex) {
                viewModel.roverName.value?.let { roverName ->
                    viewModel.sol.value?.let { sol ->
                        viewModel.selectedCamera.value?.let { selectedCamera ->
                            Navigation.findNavController(requireView())
                                .navigate(HomeFragmentDirections.actionShowDetail(DataSend(roverName, photoIndex, sol, selectedCamera)))
                            viewModel.displayPropertyDetailsComplete()
                        }
                    }
                }
            }
        })
    }
}




