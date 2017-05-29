package com.github.nein37.styleviewer.view

import android.support.v7.widget.RecyclerView

interface MainView {
    fun <T : RecyclerView.ViewHolder> setupRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<T>)
}
