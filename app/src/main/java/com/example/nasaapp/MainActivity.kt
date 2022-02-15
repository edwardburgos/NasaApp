package com.example.nasaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.nasaapp.ui.theme.NasaAppTheme
import com.google.accompanist.flowlayout.FlowRow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NasaAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
//                    NasaApp("Edward")
                    Column {
                        Camera("Camara de Paolo", true)
                        LazyColumn {
                            items(800) {
                                //
                                Card(modifier = Modifier.padding(8.dp)) {
                                    Image(
                                        painter = rememberImagePainter("https://media-exp1.licdn.com/dms/image/C560BAQG9pFfHC8xcEA/company-logo_200_200/0/1585858747024?e=1652918400&v=beta&t=dy0KKtxJapteIW7xhp0IY4UeOsJLop3iMiIox38BoeM"),
                                        contentDescription = null,
                                        //modifier = Modifier.size(128.dp)
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(300.dp)
                                        //  contentScale = ContentScale.Crop
                                    )
                                }
                            }

                        }
                    }

                }
            }
        }
    }
}
//
//@Composable
//fun Greeting(name: String) {
//    Text(text = "Hello $name!")
//}
////
////@Composable
////fun TopAppBar() {
////    Scaffold(
////        topBar = {
////            TopAppBar {
////
////            }
////        }
////    ) {
////        // Screen content
////    }
////}
//
//@Preview(showBackground = true)
//@Composable
//fun DefaultPreview() {
//    NasaAppTheme {
//        Greeting("Paolo")
//    }
//}