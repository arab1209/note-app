package com.example.toyproject_note.presentation.main.util

import androidx.compose.animation.core.tween
import com.example.toyproject_note.ui.theme.MemoItemConstants

object AnimationSpecs {
    fun <T> standardTween() = tween<T>(
        durationMillis = MemoItemConstants.ANIMATION_DURATION,
        easing = MemoItemConstants.ANIMATION_EASING
    )
}