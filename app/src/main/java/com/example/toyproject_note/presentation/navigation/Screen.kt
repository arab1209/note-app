package com.example.toyproject_note.presentation.navigation

sealed class Screen(val route: String) {
    object MemoMain: Screen("main_memo")
    object MemoDetail: Screen("memo_detail/{memoId}") {
        fun createRoute(memoId: Long) = "memo_detail/$memoId"
    }
    object AddMemo: Screen("add_memo")
}