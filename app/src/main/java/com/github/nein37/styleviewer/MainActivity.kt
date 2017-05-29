package com.github.nein37.styleviewer

import android.content.Intent
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import com.github.nein37.styleviewer.databinding.ActivityMainBinding
import com.github.nein37.styleviewer.presenter.TextAppearancePresenter
import com.github.nein37.styleviewer.usecase.TextAppearanceUseCase
import com.github.nein37.styleviewer.view.MainView


class MainActivity : AppCompatActivity(), MainView {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val presenter = TextAppearancePresenter(this, this, TextAppearanceUseCase(this))
        presenter.onCreate()
    }

    override fun <T : RecyclerView.ViewHolder> setupRecyclerView(layoutManager: RecyclerView.LayoutManager, adapter: RecyclerView.Adapter<T>) {
        binding.recyclerView.layoutManager = layoutManager
        binding.recyclerView.adapter = adapter
    }
}
