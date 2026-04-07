package com.zappyware.moviebrowser.ui.tv.trays

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.LocalColorProvider
import com.zappyware.moviebrowser.common.ui.LocalWidgetMapper
import com.zappyware.moviebrowser.common.ui.WidgetMapper
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.widget.Widget
import com.zappyware.moviebrowser.ui.tv.PositionFocusedItemInLazyLayout

@Composable
fun TvHorizontalPagerTrayWidget(
    tray: HorizontalPagerTrayWidget,
    onDetailsClicked: (Widget) -> Unit,
    widgetMapper: WidgetMapper = LocalWidgetMapper.current,
) {
    val onDetailsClickedCallback = remember(onDetailsClicked) {
        { widget: Widget ->
            onDetailsClicked(widget)
        }
    }

    val gridState = rememberLazyGridState()

    val trayItemWidth = remember {
        when (tray.orientation) {
            Orientation.Portrait -> 144f
            Orientation.Landscape -> 270f
            Orientation.Circular -> 120f
        }
    }
    val trayItemHeight = remember {
        when (tray.orientation) {
            Orientation.Portrait -> 196f
            Orientation.Landscape -> 198f
            Orientation.Circular -> 120f
        }
    }

    val colorProvider = LocalColorProvider.current

    Column {
        Text(
            text = tray.title,
            style = MaterialTheme.typography.bodyLarge,
            color = colorProvider.textColorDark,
            modifier = Modifier
                .padding(start = 44.dp, end = 44.dp, top = 44.dp, bottom = 12.dp)
        )

        PositionFocusedItemInLazyLayout(
            parentFraction = 0.05f,
            childFraction = 0f
        ) {
            LazyHorizontalGrid(
                rows = GridCells.Fixed(1),
                state = gridState,
                modifier = Modifier.fillMaxWidth()
                    .height(trayItemHeight.dp),
                contentPadding = PaddingValues(44.dp, vertical = 0.dp),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
            ) {
                items(tray.widgets.size) { index ->
                    val widget = tray.widgets[index]
                    widgetMapper.Map(
                        widget = widget,
                        orientation = tray.orientation,
                        onDetailsClicked = onDetailsClickedCallback,
                        modifier = Modifier.size(trayItemWidth.dp, trayItemHeight.dp)
                    )
                }
            }
        }
    }
}
