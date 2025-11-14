package com.example.toyproject_note.presentation.memodetail.screen

import android.util.Log
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.toyproject_note.presentation.memodetail.viewmodel.MemoDetailViewModel
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailScreen(
    memoId: Long,
    onNavigateBack: () -> Unit,
    viewModel: MemoDetailViewModel = hiltViewModel()
) {
    val memo by viewModel.getMemoById(memoId).collectAsState(initial = null)
    val isEditMode by viewModel.isEditMode.collectAsState()
    val editTitle by viewModel.editTitle.collectAsState()
    val editContent by viewModel.editContent.collectAsState()

    Scaffold(
        topBar = {
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
                    TextButton(onClick = { viewModel.toggleEditMode(memo!!)}) {
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
        },
        containerColor = MainScreenConstants.Colors.Background
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(MainScreenConstants.Colors.Background)
        ) {
            Column(modifier = Modifier.padding(Dimens.PaddingLarge)) {
                if (isEditMode) {
                    // 제목 수정
                    BasicTextField(
                        value = editTitle,
                        onValueChange = { viewModel.updateTitle(it)
                                        Log.d("test", it)},
                        textStyle = TextStyle(
                            fontSize = Dimens.TitleNameFontSizes,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(Dimens.PaddingSmall))

                    // 내용 수정
                    BasicTextField(
                        value = editContent,
                        onValueChange = { viewModel.updateContent(it) },
                        textStyle = TextStyle(
                            fontSize = Dimens.MemoFontSizes,
                            color = Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                    )
                } else {
                    // 읽기 모드
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
            }
        }
    }
}