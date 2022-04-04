package com.hua.monetcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Colors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.lifecycle.lifecycleScope
import com.hua.monetcompose.ui.darkMonetCompatScheme
import com.hua.monetcompose.ui.lightMonetCompatScheme
import com.hua.monetcompose.ui.theme.Shapes
import com.hua.monetcompose.ui.theme.Typography
import com.kieronquinn.monetcompat.app.MonetCompatActivity
import com.kieronquinn.monetcompat.core.MonetCompat

class MainActivity :MonetCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        WindowCompat.setDecorFitsSystemWindows(window,false)
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            monet.awaitMonetReady()
            setContent {
                MonetCompatTheme(monetCompat = monet) {
                    Box(
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.primary)
                            .fillMaxSize()
                    )
                }
            }
        }
    }
}

@Composable
fun MonetCompatTheme(
    monetCompat: MonetCompat,
    content:@Composable ()->Unit
){
    val colors = if(
        isSystemInDarkTheme()
    ) monetCompat.darkMonetCompatScheme()else monetCompat.lightMonetCompatScheme()
    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
    ) {
        androidx.compose.material.MaterialTheme(
            colors = Colors(
                primary = colors.primary,
                primaryVariant = colors.inversePrimary,
                secondary = colors.secondary,
                onSecondary = colors.onSecondary,
                secondaryVariant = colors.secondaryContainer,
                background = colors.background,
                onBackground = colors.onBackground,
                surface = colors.surface, error = colors.error,
                onPrimary = colors.onPrimary,
                onSurface = colors.onSurface,
                onError = colors.onError, isLight = isSystemInDarkTheme()
            ),
            shapes = Shapes, content = content
        )
    }
}