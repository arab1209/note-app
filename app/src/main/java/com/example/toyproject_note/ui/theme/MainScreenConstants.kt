package com.example.toyproject_note.ui.theme

import androidx.compose.ui.graphics.Color

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