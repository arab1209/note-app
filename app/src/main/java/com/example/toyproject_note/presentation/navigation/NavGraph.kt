package com.example.toyproject_note.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.toyproject_note.presentation.main.screen.AddMemoScreen
import com.example.toyproject_note.presentation.main.screen.MainScreen
import com.example.toyproject_note.presentation.memdetail.screen.MemoDetailScreen
import com.example.toyproject_note.ui.theme.MEMO_ID


@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MemoMain.route
    ) {
        composable(route = Screen.MemoMain.route) {
            MainScreen(
                onMemoClick = { memoId ->
                    navController.navigate(Screen.MemoDetail.createRoute(memoId))
                },
                onAddClick = {
                    navController.navigate(Screen.AddMemo.route)
                }
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
            MemoDetailScreen(
                memoId = memoId,
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }

        composable(route = Screen.AddMemo.route) {
            AddMemoScreen(
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}