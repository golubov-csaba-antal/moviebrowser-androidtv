package com.zappyware.moviebrowsertv.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.darkColorScheme
import androidx.tv.material3.lightColorScheme
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.LocalTrayMapper
import com.zappyware.moviebrowser.common.ui.LocalWidgetMapper
import com.zappyware.moviebrowser.ui.tv.mapper.TvTrayMapper
import com.zappyware.moviebrowser.ui.tv.mapper.TvWidgetMapper
import com.zappyware.moviebrowsertv.ui.theme.colors.DarkColorProvider
import com.zappyware.moviebrowsertv.ui.theme.colors.LightColorProvider

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun MovieBrowserTVTheme(
    isInDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (isInDarkTheme) {
        darkColorScheme(
            background = Color.Black,
            onBackground = Color.White,
            primary = Purple80,
            secondary = PurpleGrey80,
            tertiary = Pink80,
        )
    } else {
        lightColorScheme(
            primary = Purple40,
            secondary = PurpleGrey40,
            tertiary = Pink40,
        )
    }
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
    ) {
        CompositionLocalProvider(
            LocalColorProvider provides if (isInDarkTheme) {
                DarkColorProvider(MaterialTheme.colorScheme)
            } else {
                LightColorProvider(MaterialTheme.colorScheme)
            },
            LocalTrayMapper provides TvTrayMapper(),
            LocalWidgetMapper provides TvWidgetMapper(),
        ) {
            content()
        }
    }
}
