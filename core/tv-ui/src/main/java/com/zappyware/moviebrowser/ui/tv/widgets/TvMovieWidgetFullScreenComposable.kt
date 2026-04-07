package com.zappyware.moviebrowser.ui.tv.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import coil3.compose.AsyncImage
import com.zappyware.moviebrowser.common.ui.FavoriteIcon
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.dropShadow
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.Widget
import com.zappyware.moviebrowser.ui.tv.FocusShape

@Composable
fun TvMovieWidgetFullScreenComposable(
    modifier: Modifier,
    widget: MovieWidget,
    onDetailsClicked: (Widget) -> Unit,
) {
    val colorProvider = LocalColorProvider.current

    Row(
        modifier = modifier.padding(horizontal = 44.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(Color.LightGray),
    ) {
        FocusShape(
            modifier = Modifier.height(480.dp)
                .aspectRatio(2f / 3f, true),
            shape = RoundedCornerShape(24.dp),
        ) {
            AsyncImage(
                model = widget.smallPosterUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = it
                    .zIndex(1.0f)
                    .clip(RoundedCornerShape(24.dp))
                    .background(colorProvider.imageBackgroundColor)
                    .dropShadow(true, colorProvider.shadowColor, 24.dp, 16.dp)
                    .clickable {
                        onDetailsClicked(widget)
                    },
            )
        }
        Column(
            modifier = Modifier.weight(1f)
                .padding(16.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 8.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.Top,
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = widget.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                FavoriteIcon(
                    modifier = Modifier,
                    contentId = widget.id,
                    hideIfNotFavorite = true,
                )
            }

            widget.genre.takeIf { it.isNotEmpty() }?.let { genres ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    text = genres,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }

            widget.overview.takeIf { it.isNotEmpty() }?.let { overview ->
                Text(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                    text = overview,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurface,
                )
            }
        }
    }
}
