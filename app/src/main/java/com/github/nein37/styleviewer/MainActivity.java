package com.github.nein37.styleviewer;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import com.github.nein37.styleviewer.databinding.ActivityMainBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setSupportActionBar(binding.toolbar);

        final List<Field> textAppearanceList = new ArrayList<>();

        for (Field field : R.style.class.getFields()) {
            String fieldName = field.getName();
            if (fieldName.startsWith("TextAppearance_")) {
                textAppearanceList.add(field);
            }
        }

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setAdapter(new TextAppearanceAdapter(textAppearanceList));
    }
}
