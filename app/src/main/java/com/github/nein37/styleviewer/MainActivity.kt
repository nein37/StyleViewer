package com.github.nein37.styleviewer

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager

import com.github.nein37.styleviewer.databinding.ActivityMainBinding

import java.lang.reflect.Field
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        setSupportActionBar(binding.toolbar)

        val textAppearanceList = ArrayList<Field>()

        for (field in R.style::class.java!!.getFields()) {
            val fieldName = field.getName()
            if (fieldName.startsWith("TextAppearance_")) {
                textAppearanceList.add(field)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerView.adapter = TextAppearanceAdapter(textAppearanceList)
    }
}
