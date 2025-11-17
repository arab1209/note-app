package com.example.toyproject_note.presentation.main.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.toyproject_note.ui.theme.MemoItemConstants

@Composable
fun AnimatedMinusIcon(
    isVisible: Boolean,
    alpha: Float,
    onToggleDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    if (isVisible) {
        Box(
            modifier = modifier
                .size(MemoItemConstants.ICON_SIZE)
                .alpha(alpha)
                .background(
                    color = MemoItemConstants.ICON_COLOR,
                    shape = CircleShape
                )
                .clickable { onToggleDelete() },
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .width(MemoItemConstants.MINUS_LINE_WIDTH)
                    .height(MemoItemConstants.MINUS_LINE_HEIGHT)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(1.dp)
                    )
            )
        }
    }
}