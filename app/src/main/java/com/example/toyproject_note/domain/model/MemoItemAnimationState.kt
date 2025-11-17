package com.example.toyproject_note.domain.model

import androidx.compose.ui.unit.Dp

data class MemoItemAnimationState(
    val iconAreaWidth: Dp,
    val iconAlpha: Float,
    val deleteButtonWidth: Dp,
    val deleteButtonAlpha: Float,
    val arrowWidth: Dp,
    val arrowAlpha: Float
)