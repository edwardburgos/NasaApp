package com.example.nasaapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorPalette = darkColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Color.White,
    background = Color.Black,
)

private val LightColorPalette = lightColors(
    primary = Blue500,
    primaryVariant = Blue700,
    secondary = Color.Black,
    background = Color.White
)

@Composable
fun NasaAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val systemUiController = rememberSystemUiController()
    var colors = LightColorPalette
    if (darkTheme) {
        colors = DarkColorPalette
        systemUiController.setSystemBarsColor(
            color = Color.Black
        )
    } else {
        systemUiController.setSystemBarsColor(
            color = Color.White
        )
    }
    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}