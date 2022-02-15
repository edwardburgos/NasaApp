package com.example.nasaapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberImagePainter
import androidx.compose.material.Surface
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.Text


@Composable
fun Camera(cameraName: String,
           isSelected: Boolean,
) {
    Surface(
            modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
            elevation = 8.dp,
            shape = RoundedCornerShape(16.dp),
            color = when {
                isSelected -> colorResource(R.color.teal_200)
                else -> colorResource(R.color.purple_500)
            }
        ) {
//            Row(modifier = Modifier
//                .toggleable(
//                    value = isSelected,
//                    onValueChange = {
//                        onSelectedCategoryChanged(category)
//                        onExecuteSearch()
//                    }
//                )) {
                Text(
                    text = cameraName,
                    style = MaterialTheme.typography.body2,
                   // color = Color.White,
                    modifier = Modifier.padding(8.dp)
                )
         //   }
        }
}

//
//}: String,
//    isSelected: Boolean = false,
//    onSelectedCategoryChanged: (String) -> Unit,
//    onExecuteSearch: () -> Unit
//    ) {
//        Surface(
//            modifier = Modifier.padding(end = 8.dp, bottom = 8.dp),
//            elevation = 8.dp,
//            shape = RoundedCornerShape(16.dp),
//            color = when {
//                isSelected -> colorResource(R.color.teal_200)
//                else -> colorResource(R.color.purple_500)
//            }
//        ) {
//            Row(modifier = Modifier
//                .toggleable(
//                    value = isSelected,
//                    onValueChange = {
//                        onSelectedCategoryChanged(category)
//                        onExecuteSearch()
//                    }
//                )) {
//                Text(
//                    text = category,
//                    style = MaterialTheme.typography.body2,
//                    color = Color.White,
//                    modifier = Modifier.padding(8.dp)
//                )
//            }
//        }
//    }