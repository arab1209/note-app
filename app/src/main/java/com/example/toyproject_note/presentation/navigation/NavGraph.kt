package com.example.toyproject_note.presentation.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.toyproject_note.presentation.main.screen.AddMemoScreen
import com.example.toyproject_note.presentation.main.screen.MainScreen
import com.example.toyproject_note.presentation.main.viewmodel.MainViewModel
import com.example.toyproject_note.presentation.memodetail.screen.MemoDetailScreen
import com.example.toyproject_note.presentation.memodetail.viewmodel.MemoDetailViewModel
import com.example.toyproject_note.ui.theme.MEMO_ID


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: MainViewModel = hiltViewModel(),
    detailViewModel: MemoDetailViewModel = hiltViewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MemoMain.route
    ) {
        composable(route = Screen.MemoMain.route) {
            val memoList by viewModel.memos.collectAsState()
            val isEditMode by viewModel.isEditMode.collectAsState()

            MainScreen(
                onMemoClick = { memoId ->
                    navController.navigate(Screen.MemoDetail.createRoute(memoId))
                },
                onAddClick = {
                    navController.navigate(Screen.AddMemo.route)
                },
                onEditModeChange = viewModel::updateEditMode,
                onDeleteClick = viewModel::deleteMemo,
                memoList = memoList,
                isEditMode = isEditMode,
            )
        }

        composable(
            route = Screen.MemoDetail.route,
            arguments = listOf(
                navArgument(MEMO_ID) {
                    type = NavType.LongType
                }
            )
        ) { backStackEntry ->
            val memoId = backStackEntry.arguments?.getLong(MEMO_ID) ?: -1L

            val memo by detailViewModel.getMemoById(memoId).collectAsStateWithLifecycle(initialValue = null)
            val isEditMode by detailViewModel.isEditMode.collectAsStateWithLifecycle()
            val editTitle by detailViewModel.editTitle.collectAsStateWithLifecycle()
            val editContent by detailViewModel.editContent.collectAsStateWithLifecycle()


            MemoDetailScreen(
                memoId = memoId,
                onNavigateBack = { navController.popBackStack() },
                memo = memo,
                isEditMode = isEditMode,
                editTitle = editTitle,
                editContent = editContent,
                toggleEditMode = detailViewModel::toggleEditMode,
                onTitleChanged = detailViewModel::updateTitle,
                onContentChanged = detailViewModel::updateContent,
            )
        }

        composable(route = Screen.AddMemo.route) {
            AddMemoScreen(
                onNavigateBack = {
                    navController.popBackStack()
                } ,
            )
        }
    }
}