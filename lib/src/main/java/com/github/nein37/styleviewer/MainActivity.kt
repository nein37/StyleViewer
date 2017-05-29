package com.github.nein37.styleviewer

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.github.nein37.styleviewer.presenter.TextAppearancePresenter
import com.github.nein37.styleviewer.usecase.TextAppearanceUseCase
import com.github.nein37.styleviewer.view.MainView

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), MainView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val presenter = TextAppearancePresenter(this, this, TextAppearanceUseCase(this))
        presenter.onCreate()
    }

    override fun <T : RecyclerView.ViewHolder> setupRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<T>) {
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
    }
}
