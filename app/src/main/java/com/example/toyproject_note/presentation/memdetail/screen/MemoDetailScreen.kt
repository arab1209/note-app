package com.example.toyproject_note.presentation.memdetail.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoDetailScreen(
    memoId: Long,
    onNavigateBack: () -> Unit
) {

    // TODO: ViewModel에서 실제 메모 데이터 가져오기
    val memoTitle = "메모 제목 예시"
    val memoContent = "메모 내용..."

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = memoTitle,
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
                    TextButton(onClick = { }) {
                        Text(
                            text = "수정",
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
            // TODO: 메모 내용 표시
            Text(text = memoContent)
        }
    }
}