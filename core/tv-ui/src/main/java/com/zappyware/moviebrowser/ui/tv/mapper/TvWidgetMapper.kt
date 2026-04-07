package com.zappyware.moviebrowser.ui.tv.mapper

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zappyware.moviebrowser.common.ui.WidgetMapper
import com.zappyware.moviebrowser.common.ui.mapper.DefaultWidgetMapper
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.widget.Widget
import com.zappyware.moviebrowser.ui.tv.FocusShape
import javax.inject.Inject

class TvWidgetMapper @Inject constructor() : WidgetMapper {

    private val defaultMapper = DefaultWidgetMapper()

    @Composable
    override fun Map(
        widget: Widget,
        orientation: Orientation,
        onDetailsClicked: (Widget) -> Unit,
        modifier: Modifier
    ) {
        val shape = remember(orientation) {
            when (orientation) {
                Orientation.Circular -> RoundedCornerShape(60.dp)
                else -> RoundedCornerShape(24.dp)
            }
        }
        FocusShape(
            modifier = modifier,
            shape = shape,
        ) {
            defaultMapper.Map(
                widget = widget,
                orientation = orientation,
                onDetailsClicked = onDetailsClicked,
                modifier = it,
            )
        }
    }
}
