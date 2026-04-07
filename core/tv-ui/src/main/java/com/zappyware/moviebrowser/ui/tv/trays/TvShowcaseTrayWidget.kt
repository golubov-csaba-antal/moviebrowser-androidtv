package com.zappyware.moviebrowser.ui.tv.trays

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget
import com.zappyware.moviebrowser.ui.tv.widgets.TvMovieWidgetFullScreenComposable

@Composable
fun TvShowcaseTrayWidget(
    tray: ShowcaseTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Column {
        Text(
            text = tray.title,
            style = MaterialTheme.typography.bodyLarge,
            color = colorProvider.textColorDark,
            modifier = Modifier
                .padding(start = 44.dp, end = 44.dp, top = 44.dp, bottom = 12.dp)
        )

        when (val widget = tray.widget) {
            is MovieWidget -> {
                TvMovieWidgetFullScreenComposable(
                    modifier = Modifier.fillMaxSize(),
                    widget = widget,
                    onDetailsClicked = onDetailsClicked,
                )
            }
        }
    }
}
