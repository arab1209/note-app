package com.example.toyproject_note.presentation.main.screen

import android.util.Log
import androidx.compose.animation.core.EaseInOutCubic
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.presentation.CommonTopAppBar
import com.example.toyproject_note.presentation.main.util.AnimationSpecs
import com.example.toyproject_note.presentation.main.util.rememberMemoItemAnimations
import com.example.toyproject_note.presentation.main.viewmodel.MainViewModel
import com.example.toyproject_note.ui.theme.ADD_MEMO
import com.example.toyproject_note.ui.theme.AppColors
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.FINISH_MEMO
import com.example.toyproject_note.ui.theme.MAIN_TITLE
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.MemoItemConstants
import com.example.toyproject_note.ui.theme.NOT_MEMO
import com.example.toyproject_note.ui.theme.NOT_MEMO_ADD
import com.example.toyproject_note.ui.theme.Typography
import com.example.toyproject_note.ui.theme.UPDATE_MEMO

@Composable
fun MainScreen(
    onMemoClick: (Long) -> Unit,
    onAddClick: () -> Unit,
    onEditModeChange: (Boolean) -> Unit,
    onDeleteClick: (memo: NoteData) -> Unit,
    memoList: List<NoteData>,
    isEditMode: Boolean,
) {
    Scaffold(
        topBar = {
            CommonTopAppBar(
                title = MAIN_TITLE,
                navigationIcon = {
                    Text(
                        text = if (isEditMode) FINISH_MEMO else UPDATE_MEMO,
                        fontSize = Typography.bodyLarge.fontSize,
                        color = MainScreenConstants.Colors.TopBarTitle,
                        modifier = Modifier
                            .clickable { onEditModeChange(!isEditMode) }
                            .padding(Dimens.PaddingLarge)
                    )
                }
            )
        },
        containerColor = MainScreenConstants.Colors.Background,
        floatingActionButton = {
            if (!isEditMode) {
                MainFab(onAddClick = onAddClick)
            }
        }
    ) { paddingValues ->
        MainScreenContent(
            memoList = memoList,
            isEditMode = isEditMode,
            onMemoClick = onMemoClick,
            onDeleteClick = onDeleteClick,
            paddingValues = paddingValues
        )
    }
}

@Composable
private fun MainFab(onAddClick: () -> Unit) {
    FloatingActionButton(
        onClick = onAddClick,
        containerColor = MainScreenConstants.Colors.FabContainer,
        contentColor = MainScreenConstants.Colors.FabIcon
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ADD_MEMO
        )
    }
}

@Composable
private fun MainScreenContent(
    memoList: List<NoteData>,
    isEditMode: Boolean,
    onMemoClick: (Long) -> Unit,
    onDeleteClick: (NoteData) -> Unit,
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(MainScreenConstants.Colors.Background)
    ) {
        if (memoList.isEmpty()) {
            EmptyMemoState()
        } else {
            MemoList(
                memoList = memoList,
                isEditMode = isEditMode,
                onMemoClick = onMemoClick,
                onDeleteClick = onDeleteClick
            )
        }
    }
}

@Composable
private fun EmptyMemoState() {
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
}

@Composable
private fun MemoList(
    memoList: List<NoteData>,
    isEditMode: Boolean,
    onMemoClick: (Long) -> Unit,
    onDeleteClick: (NoteData) -> Unit
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
        items(
            items = memoList,
            key = { it.id }
        ) { memo ->
            MemoItem(
                memo = memo,
                onClick = {
                    if (!isEditMode) {
                        onMemoClick(memo.id)
                    }
                },
                isEditMode = isEditMode,
                onDeleteClick = { onDeleteClick(memo) }
            )
        }
    }
}

@Composable
fun MemoItem(
    memo: NoteData,
    onClick: () -> Unit,
    isEditMode: Boolean = false,
    onDeleteClick: () -> Unit = {}
) {
    var showDeleteButton by remember { mutableStateOf(false) }

    // 🔥 애니메이션 상태들을 한 번에 관리
    val animations = rememberMemoItemAnimations(
        isEditMode = isEditMode,
        showDeleteButton = showDeleteButton
    )

    LaunchedEffect(isEditMode) {
        if (!isEditMode) {
            showDeleteButton = false
        }
    }

    MemoItemLayout(
        memo = memo,
        onClick = onClick,
        showDeleteButton = showDeleteButton,
        animations = animations,
        onToggleDelete = { showDeleteButton = !showDeleteButton },
        onDeleteConfirm = {
            onDeleteClick()
            showDeleteButton = false
        }
    )
}