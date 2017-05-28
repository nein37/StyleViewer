package com.github.nein37.styleviewer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.github.nein37.styleviewer.databinding.ActivityMainBinding
import com.github.nein37.styleviewer.repository.StyleRepository
import com.github.nein37.styleviewer.repository.StyleRepositoryImpl

import java.lang.reflect.Field
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)
        val repo = StyleRepositoryImpl();
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = TextAppearanceAdapter(repo.getStyleList(prefix = "TextAppearance_"))
    }
}
