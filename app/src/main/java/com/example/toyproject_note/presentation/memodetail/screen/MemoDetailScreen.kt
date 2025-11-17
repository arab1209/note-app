package com.example.toyproject_note.presentation.memodetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.toyproject_note.domain.model.NoteData
import com.example.toyproject_note.presentation.memodetail.viewmodel.MemoDetailViewModel
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@Composable
fun MemoDetailScreen(
    memoId: Long,
    onNavigateBack: () -> Unit,
    memo: NoteData?,
    isEditMode: Boolean,
    editTitle: String,
    editContent: String,
    toggleEditMode: (memo: NoteData) -> Unit,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit
) {
    Scaffold(
        topBar = {
            MemoDetailTopBar(
                isEditMode = isEditMode,
                onNavigateBack = onNavigateBack,
                onToggleEditMode = {
                    memo?.let { toggleEditMode(it) }
                }
            )
        },
        containerColor = MainScreenConstants.Colors.Background
    ) { paddingValues ->
        MemoDetailContent(
            memo = memo,
            isEditMode = isEditMode,
            editTitle = editTitle,
            editContent = editContent,
            onTitleChanged = onTitleChanged,
            onContentChanged = onContentChanged,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MemoDetailTopBar(
    isEditMode: Boolean,
    onNavigateBack: () -> Unit,
    onToggleEditMode: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "메모",
                fontSize = Typography.bodyLarge.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(onClick = onNavigateBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "뒤로가기",
                    tint = MainScreenConstants.Colors.TopBarTitle
                )
            }
        },
        actions = {
            TextButton(onClick = onToggleEditMode) {
                Text(
                    text = if (isEditMode) "저장" else "수정",
                    color = MainScreenConstants.Colors.TopBarTitle
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainScreenConstants.Colors.TopBarBackground,
            titleContentColor = MainScreenConstants.Colors.TopBarTitle
        )
    )
}

@Composable
private fun MemoDetailContent(
    memo: NoteData?,
    isEditMode: Boolean,
    editTitle: String,
    editContent: String,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainScreenConstants.Colors.Background)
            .padding(Dimens.PaddingLarge)
    ) {
        if (isEditMode) {
            EditModeContent(
                editTitle = editTitle,
                editContent = editContent,
                onTitleChanged = onTitleChanged,
                onContentChanged = onContentChanged
            )
        } else {
            ReadModeContent(memo = memo)
        }
    }
}

@Composable
private fun EditModeContent(
    editTitle: String,
    editContent: String,
    onTitleChanged: (String) -> Unit,
    onContentChanged: (String) -> Unit
) {
    // 제목 입력
    MemoTextField(
        value = editTitle,
        onValueChange = onTitleChanged,
        textStyle = TextStyle(
            fontSize = Dimens.TitleNameFontSizes,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        ),
        placeholder = "제목을 입력하세요",
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

    // 내용 입력
    MemoTextField(
        value = editContent,
        onValueChange = onContentChanged,
        textStyle = TextStyle(
            fontSize = Dimens.MemoFontSizes,
            color = Color.Black
        ),
        placeholder = "내용을 입력하세요",
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    )
}

@Composable
private fun ReadModeContent(
    memo: NoteData?
) {
    Text(
        text = memo?.title ?: "",
        fontSize = Dimens.TitleNameFontSizes,
        fontWeight = FontWeight.Bold
    )

    Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

    Text(
        text = memo?.content ?: "",
        fontSize = Dimens.MemoFontSizes
    )
}

@Composable
private fun MemoTextField(
    value: String,
    onValueChange: (String) -> Unit,
    textStyle: TextStyle,
    placeholder: String,
    modifier: Modifier = Modifier,
    singleLine: Boolean = false
) {
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = singleLine,
        modifier = modifier,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier.fillMaxWidth()) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        style = textStyle.copy(
                            color = Color.Gray.copy(alpha = 0.6f)
                        )
                    )
                }
                innerTextField()
            }
        }
    )
}