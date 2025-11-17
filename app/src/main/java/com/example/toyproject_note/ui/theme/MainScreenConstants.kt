package com.example.toyproject_note.ui.theme

import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

object MainScreenConstants {

    object Dimensions {
        val ScreenHorizontalPadding = Dimens.PaddingLarge
        val ContentPaddingVertical = Dimens.PaddingLarge
        val ContentPaddingHorizontal = Dimens.PaddingMedium
        val ItemIconSize = Dimens.IconSizeMedium
        val ItemDividerThickness = Dimens.DividerThickness
        val MemoFontSize = Dimens.MemoFontSizes
    }

    object Shapes {
        val ContentShape = com.example.toyproject_note.ui.theme.Shapes.Large
    }

    object Colors {
        val Background = AppColors.BackgroundGray
        val ContentBackground = AppColors.White
        val TopBarBackground = AppColors.ItemBackground
        val TopBarTitle = AppColors.Black
        val ItemBackground = AppColors.ItemBackground
        val FabContainer = Color(0xFFB8A8D9)  // 연한 보라
        val FabIcon = Color.White
    }
}

object MemoItemConstants {
    val ICON_AREA_WIDTH = 36.dp
    val ICON_SIZE = 24.dp
    val DELETE_BUTTON_WIDTH = 80.dp
    val DELETE_BUTTON_HEIGHT = 32.dp
    val MINUS_LINE_WIDTH = 12.dp
    val MINUS_LINE_HEIGHT = 2.dp
    val DELETE_BUTTON_CORNER_RADIUS = 6.dp

    val ICON_COLOR = Color(0xFFFF3B30)
    val DELETE_BUTTON_COLOR = Color(0xFFFF3B30)

    const val ANIMATION_DURATION = 300
    val ANIMATION_EASING = EaseInOutCubic
}