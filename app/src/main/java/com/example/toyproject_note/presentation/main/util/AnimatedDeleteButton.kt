package com.example.toyproject_note.presentation.main.util

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toyproject_note.ui.theme.MemoItemConstants

@Composable
fun AnimatedDeleteButton(
    width: Dp,
    alpha: Float,
    onDeleteConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(width)
            .height(MemoItemConstants.DELETE_BUTTON_HEIGHT),
        contentAlignment = Alignment.Center
    ) {
        if (width > 0.dp) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha)
                    .background(
                        color = MemoItemConstants.DELETE_BUTTON_COLOR,
                        shape = RoundedCornerShape(MemoItemConstants.DELETE_BUTTON_CORNER_RADIUS)
                    )
                    .clickable { onDeleteConfirm() },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "삭제",
                    color = Color.White,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}