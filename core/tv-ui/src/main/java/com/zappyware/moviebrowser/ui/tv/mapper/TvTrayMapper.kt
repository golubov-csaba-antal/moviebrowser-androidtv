package com.zappyware.moviebrowser.ui.tv.mapper

import androidx.compose.runtime.Composable
import com.zappyware.moviebrowser.common.ui.TrayMapper
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.data.widget.Widget
import com.zappyware.moviebrowser.ui.tv.trays.TvHorizontalPagerTrayWidget
import com.zappyware.moviebrowser.ui.tv.trays.TvShowcaseTrayWidgetComposable
import javax.inject.Inject

class TvTrayMapper @Inject constructor() : TrayMapper {
    @Composable
    override fun Map(tray: TrayWidget, onDetailsClicked: (Widget) -> Unit) {
        when (tray) {
            is HorizontalPagerTrayWidget -> {
                TvHorizontalPagerTrayWidget(
                    tray = tray,
                    onDetailsClicked = onDetailsClicked,
                )
            }
            is ShowcaseTrayWidget -> {
                TvShowcaseTrayWidgetComposable(
                    tray = tray,
                    onDetailsClicked = onDetailsClicked,
                )
            }
        }
    }
}
