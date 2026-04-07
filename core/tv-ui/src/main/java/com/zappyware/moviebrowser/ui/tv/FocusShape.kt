package com.zappyware.moviebrowser.ui.tv

import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

@Composable
fun FocusShape(
    modifier: Modifier,
    shape: Shape,
    content: @Composable (Modifier) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()

    content(
        modifier.focusable(interactionSource = interactionSource)
            .then(
                if (isFocused) {
                    Modifier.border(
                        width = 2.dp,
                        color = Color.White,
                        shape = shape,
                    )
                } else {
                    Modifier
                }
            )
    )
}
