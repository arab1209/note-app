package com.example.toyproject_note.presentation.main.util

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import com.example.toyproject_note.domain.model.MemoItemAnimationState
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.MemoItemConstants

@Composable
fun rememberMemoItemAnimations(
    isEditMode: Boolean,
    showDeleteButton: Boolean
): MemoItemAnimationState {
    val iconAreaWidth by animateDpAsState(
        targetValue = if (isEditMode) MemoItemConstants.ICON_AREA_WIDTH else 0.dp,
        animationSpec = AnimationSpecs.standardTween(),
        label = "icon_area_width"
    )

    val iconAlpha by animateFloatAsState(
        targetValue = if (isEditMode) 1f else 0f,
        animationSpec = AnimationSpecs.standardTween(),
        label = "icon_alpha"
    )

    val deleteButtonWidth by animateDpAsState(
        targetValue = if (showDeleteButton) MemoItemConstants.DELETE_BUTTON_WIDTH else 0.dp,
        animationSpec = AnimationSpecs.standardTween(),
        label = "delete_button_width"
    )

    val deleteButtonAlpha by animateFloatAsState(
        targetValue = if (showDeleteButton) 1f else 0f,
        animationSpec = AnimationSpecs.standardTween(),
        label = "delete_button_alpha"
    )

    val arrowWidth by animateDpAsState(
        targetValue = if (showDeleteButton) 0.dp else MainScreenConstants.Dimensions.ItemIconSize,
        animationSpec = AnimationSpecs.standardTween(),
        label = "arrow_width"
    )

    val arrowAlpha by animateFloatAsState(
        targetValue = if (isEditMode || showDeleteButton) 0f else 1f,
        animationSpec = AnimationSpecs.standardTween(),
        label = "arrow_alpha"
    )

    return MemoItemAnimationState(
        iconAreaWidth = iconAreaWidth,
        iconAlpha = iconAlpha,
        deleteButtonWidth = deleteButtonWidth,
        deleteButtonAlpha = deleteButtonAlpha,
        arrowWidth = arrowWidth,
        arrowAlpha = arrowAlpha
    )
}
