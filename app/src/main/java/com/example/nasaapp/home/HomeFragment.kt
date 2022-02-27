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
import com.example.nasaapp.ui.theme.NasaAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // TODO: Since this code seems to be duplicated in both HomeFragment and DetailFragment,
        //  you could've moved it to a BaseFragment class that both fragments would inherit from
        //  and only pass the necessary variables.
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
                    HomeView(viewModel = viewModel, imageLoader = imageLoader)
                }
            }
        }
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




