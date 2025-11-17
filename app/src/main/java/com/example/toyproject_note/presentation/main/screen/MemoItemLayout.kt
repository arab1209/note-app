package com.example.toyproject_note.presentation.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.toyproject_note.domain.model.MemoItemAnimationState
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.presentation.main.util.AnimatedArrowIcon
import com.example.toyproject_note.presentation.main.util.AnimatedDeleteButton
import com.example.toyproject_note.presentation.main.util.AnimatedMinusIcon
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.MemoItemConstants

@Composable
fun MemoItemLayout(
    memo: NoteData,
    onClick: () -> Unit,
    showDeleteButton: Boolean,
    animations: MemoItemAnimationState,
    onToggleDelete: () -> Unit,
    onDeleteConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                onClick = onClick,
                enabled = !showDeleteButton
            )
            .background(MainScreenConstants.Colors.ItemBackground)
            .padding(
                horizontal = Dimens.PaddingMedium,
                vertical = Dimens.PaddingSmall
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 마이너스 아이콘 영역
            Box(
                modifier = Modifier
                    .width(animations.iconAreaWidth) // animations 객체에서 접근
                    .height(MemoItemConstants.ICON_SIZE),
                contentAlignment = Alignment.CenterStart
            ) {
                AnimatedMinusIcon(
                    isVisible = animations.iconAreaWidth > 0.dp,
                    alpha = animations.iconAlpha, // animations 객체에서 접근
                    onToggleDelete = onToggleDelete
                )
            }

            // 텍스트
            Text(
                text = memo.title,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MainScreenConstants.Dimensions.MemoFontSize
            )

            // 삭제 버튼
            AnimatedDeleteButton(
                width = animations.deleteButtonWidth, // animations 객체에서 접근
                alpha = animations.deleteButtonAlpha, // animations 객체에서 접근
                onDeleteConfirm = onDeleteConfirm
            )

            // 화살표 아이콘
            AnimatedArrowIcon(
                width = animations.arrowWidth, // animations 객체에서 접근
                alpha = animations.arrowAlpha // animations 객체에서 접근
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(top = Dimens.PaddingSmall),
            thickness = MainScreenConstants.Dimensions.ItemDividerThickness,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    }
}