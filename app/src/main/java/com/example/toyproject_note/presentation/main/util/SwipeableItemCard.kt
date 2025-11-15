package com.example.toyproject_note.presentation.main.util

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.presentation.main.screen.MemoItem
import kotlin.math.roundToInt
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.Spacer

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableItemCard(
    memo: NoteData,
    onClick: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier
) {
    var offsetX: Float by remember { mutableFloatStateOf(0f) }
    val maxSwipe = -100f
    val density = LocalDensity.current

    val animatedOffsetX by animateFloatAsState(
        targetValue = offsetX,
        animationSpec = tween(
            durationMillis = 200,
            easing = FastOutSlowInEasing
        ),
        label = "swipe_offset"
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Box(
            modifier = Modifier
                .matchParentSize()
                .background(Color(0xFFFF3B30)),
            contentAlignment = Alignment.CenterEnd
        ) {
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clickable {
                        offsetX = 0f
                        onDelete()
                    },
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "삭제",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "삭제",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .offset { IntOffset(animatedOffsetX.roundToInt(), 0) }
                .draggable(
                    orientation = Orientation.Horizontal,
                    state = rememberDraggableState { delta ->
                        val newOffset = offsetX + delta
                        offsetX = newOffset.coerceIn(maxSwipe * density.density, 0f)
                    },
                    onDragStopped = { velocity ->
                        offsetX = if (offsetX < maxSwipe * density.density / 2) {
                            maxSwipe * density.density
                        } else {
                            0f
                        }
                    }
                )
        ) {
            MemoItem(
                memo = memo,
                onClick = {
                    if (offsetX < 0f) {
                        offsetX = 0f
                    } else {
                        onClick()
                    }
                }
            )
        }
    }
}