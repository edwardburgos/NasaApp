package com.example.nasaapp

import androidx.compose.runtime.Composable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import androidx.compose.material.Text
import androidx.compose.ui.layout.ContentScale

//import androidx.compose.material

@Composable
fun Photo(url: String) {
//    Image(
//        painter = rememberImagePainter(
//        data = url,
////        builder = {
////            transformations(CircleCropTransformation())
////        }
//    ),
//        contentDescription = null,
//        modifier = Modifier.size(128.dp)
//    )

    Image(
        painter = rememberImagePainter(url),
        contentDescription = null,
        //modifier = Modifier.size(128.dp)
                modifier = Modifier.fillMaxWidth()
      //  contentScale = ContentScale.Crop
    )
//    Image(
//        painter = rememberImagePainter("https://i.scdn.co/image/ab6761610000e5eb4d29133ce8ef34f31346f2bc"),
//        contentDescription = null,
//        //modifier = Modifier.size(128.dp)
//        modifier = Modifier.fillMaxWidth()
//        //  contentScale = ContentScale.Crop
//    )

    //Text(text = url)
}