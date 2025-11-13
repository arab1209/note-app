package com.example.toyproject_note.presentation.main.screen

import android.util.Log
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
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import com.example.toyproject_note.ui.theme.NOT_MEMO
import com.example.toyproject_note.ui.theme.NOT_MEMO_ADD
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onMemoClick: (Long) -> Unit, onAddClick: () -> Unit, memoList: List<NoteData>
) {
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
            if (memoList.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            modifier = Modifier.size(64.dp),
                            tint = Color.Gray.copy(alpha = 0.6f)
                        )
                        Spacer(modifier = Modifier.height(Dimens.PaddingLarge))
                        Text(
                            text = NOT_MEMO,
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.Gray
                        )
                        Spacer(modifier = Modifier.height(Dimens.PaddingSmall))
                        Text(
                            text = NOT_MEMO_ADD,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.LightGray
                        )
                    }
                }
            } else {
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
                    items(memoList) { memo ->
                        MemoItem(memo = memo, onClick = { onMemoClick(memo.id) })
                    }
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

        HorizontalDivider(
            modifier = Modifier.padding(top = Dimens.PaddingSmall),
            thickness = MainScreenConstants.Dimensions.ItemDividerThickness,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f)
        )
    }
}