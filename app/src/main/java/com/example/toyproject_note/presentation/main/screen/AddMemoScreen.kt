package com.example.toyproject_note.presentation.main.screen

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
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemoScreen(
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "메모",
                        fontSize = Typography.bodyLarge.fontSize
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
                    TextButton(onClick = {
                        // TODO: 저장 로직 후 onNavigateBack()
                    }) {
                        Text(
                            text = "저장",
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
            // TODO: 메모 입력 폼
            Text(text = "메모 작성 화면")
        }
    }
}