package com.github.nein37.styleviewer.presenter

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import com.github.nein37.styleviewer.usecase.TextAppearanceUseCase
import com.github.nein37.styleviewer.view.MainView

class TextAppearancePresenter(val context: Context, val mainView: MainView, val usecase: TextAppearanceUseCase) {
    fun onCreate() {
        mainView.setupRecyclerView(
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false),
                TextAppearanceAdapter(usecase.getStyleList(prefix = "TextAppearance_"))
        )
    }
}
