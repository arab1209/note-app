package com.example.toyproject_note.presentation.main.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.presentation.main.viewmodel.MainViewModel
import com.example.toyproject_note.ui.theme.ADD_MEMO
import com.example.toyproject_note.ui.theme.AppColors
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MAIN_TITLE
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onMemoClick: (Long) -> Unit, onAddClick: () -> Unit, viewModel: MainViewModel = hiltViewModel()
) {
    val dummyMemos = listOf(
        NoteData(id = 1, title = "더더엄리 ㅏ ㅊ", content = ""),
        NoteData(id = 2, title = "어오라랩도ㅡ동ㅇ", content = ""),
        NoteData(id = 3, title = "너어어오어ㅏㅇ", content = ""),
        NoteData(id = 4, title = "너넝아ㅏㅇ원", content = ""),
        NoteData(id = 5, title = "ㅇㅇㅇ", content = ""),
        NoteData(id = 6, title = "너너너", content = ""),
        NoteData(id = 7, title = "모ㄴ으르으", content = ""),
        NoteData(id = 8, title = "샘플 메모ㅏㅏㅏㅏㅏ", content = ""),
        NoteData(id = 9, title = "길게 놀러 순서 변경", content = ""),
        NoteData(id = 10, title = "원쪽으로 밀어 삭제", content = ""),
        NoteData(id = 11, title = "오른쪽으로 밀어 새상 추가", content = "")
    )

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = MAIN_TITLE, fontSize = Typography.bodyLarge.fontSize
                )
            }, colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MainScreenConstants.Colors.TopBarBackground,
                titleContentColor = MainScreenConstants.Colors.TopBarTitle
            )
        )
    }, containerColor = MainScreenConstants.Colors.Background, floatingActionButton = {
        FloatingActionButton(
            onClick = onAddClick,
            containerColor = MainScreenConstants.Colors.FabContainer,
            contentColor = MainScreenConstants.Colors.FabIcon
        ) {
            Icon(
                imageVector = Icons.Default.Add, contentDescription = ADD_MEMO
            )
        }
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MainScreenConstants.Colors.Background)
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(MainScreenConstants.Dimensions.ScreenHorizontalPadding)
                    .clip(MainScreenConstants.Shapes.ContentShape)
                    .background(MainScreenConstants.Colors.ContentBackground),
                contentPadding = PaddingValues(
                    vertical = MainScreenConstants.Dimensions.ContentPaddingVertical,
                    horizontal = MainScreenConstants.Dimensions.ContentPaddingHorizontal
                )
            ) {
                items(dummyMemos) { memo ->
                    MemoItem(memo = memo, onClick = { onMemoClick(memo.id) })
                }
            }
        }
    }
}

@Composable
fun MemoItem(
    memo: NoteData, onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .background(MainScreenConstants.Colors.ItemBackground)
            .padding(
                horizontal = Dimens.PaddingMedium, vertical = Dimens.PaddingSmall
            )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = memo.title,
                fontWeight = FontWeight.Normal,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = MainScreenConstants.Dimensions.MemoFontSize
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "상세보기",
                modifier = Modifier.size(MainScreenConstants.Dimensions.ItemIconSize),
                tint = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }

        Divider(
            modifier = Modifier.padding(top = Dimens.PaddingSmall),
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
            thickness = MainScreenConstants.Dimensions.ItemDividerThickness
        )
    }
}