package com.zappyware.moviebrowsertv.ui.theme.colors

import androidx.compose.ui.graphics.Color
import androidx.tv.material3.ColorScheme
import com.zappyware.moviebrowser.common.ui.ColorProvider

class LightColorProvider(colorScheme: ColorScheme) : ColorProvider {

    override val shadowColor: Color = Color.DarkGray
    override val imageBackgroundColor: Color = Color.LightGray
    override val imageBorderColor: Color = colorScheme.primary
    override val textColorDark: Color = Color.Black
    override val textColorLight: Color = Color.White
    override val rateIconColor: Color = Color(0xFFF3DD00)
}
