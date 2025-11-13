package com.example.toyproject_note.presentation.main.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.toyproject_note.presentation.main.viewmodel.AddMemoViewModel
import com.example.toyproject_note.ui.theme.Dimens
import com.example.toyproject_note.ui.theme.MainScreenConstants
import com.example.toyproject_note.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddMemoScreen(
    viewModel: AddMemoViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(uiState) {
        when {
            uiState.saveSuccess -> {
                viewModel.resetSaveSuccess()
                onNavigateBack()
            }

            uiState.showTitleError -> {
                Toast.makeText(context, "제목을 입력해주세요", Toast.LENGTH_SHORT).show()
            }

            uiState.errorMessage != null -> {
                Toast.makeText(context, uiState.errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.clearError()
            }
        }
    }

    Scaffold(
        topBar = {
            AddMemoTopBar(
                isSaving = uiState.isSaving,
                onNavigateBack = onNavigateBack,
                onSave = viewModel::saveMemo
            )
        },
        containerColor = MainScreenConstants.Colors.Background
    ) { paddingValues ->
        AddMemoContent(
            text = uiState.text,
            isSaving = uiState.isSaving,
            onTextChanged = viewModel::onTextChanged,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AddMemoTopBar(
    isSaving: Boolean,
    onNavigateBack: () -> Unit,
    onSave: () -> Unit
) {
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
            TextButton(
                onClick = onSave,
                enabled = !isSaving
            ) {
                if (isSaving) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(Dimens.IconSizeMedium),
                        color = MainScreenConstants.Colors.TopBarTitle
                    )
                } else {
                    Text(
                        text = "저장",
                        color = MainScreenConstants.Colors.TopBarTitle
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MainScreenConstants.Colors.TopBarBackground,
            titleContentColor = MainScreenConstants.Colors.TopBarTitle
        )
    )
}

@Composable
private fun AddMemoContent(
    text: String,
    isSaving: Boolean,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MainScreenConstants.Colors.Background)
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChanged,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            textStyle = MaterialTheme.typography.bodyMedium.copy(
                color = Color.Black,
            ),
            enabled = !isSaving,
            visualTransformation = { annotatedString ->
                if (annotatedString.text.lines().isEmpty()) {
                    TransformedText(annotatedString, OffsetMapping.Identity)
                } else {
                    val styledText = buildAnnotatedString {
                        withStyle(
                            style = SpanStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black
                            )
                        ) {
                            append(annotatedString.text.lines().first())
                        }

                        if (annotatedString.text.lines().size > 1) {
                            append("\n")
                            withStyle(
                                style = SpanStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Normal,
                                    color = Color.Black
                                )
                            ) {
                                append(annotatedString.text.lines().drop(1).joinToString("\n"))
                            }
                        }
                    }
                    TransformedText(styledText, OffsetMapping.Identity)
                }
            },
            decorationBox = { innerTextField ->
                Box(modifier = Modifier.fillMaxSize()) {
                    if (text.isEmpty()) {
                        Text(
                            text = "첫 줄은 제목, 나머지는 내용으로 입력하세요",
                            color = Color.Gray.copy(alpha = 0.6f),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}