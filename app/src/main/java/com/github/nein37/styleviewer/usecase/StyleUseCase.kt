package com.github.nein37.styleviewer.usecase

import com.github.nein37.styleviewer.repository.StyleRepository

class StyleUseCase {

    val repository = StyleRepository();

    fun getStyleList(prefix: String = "", suffix: String = ""): List<StyleEntity> {
        return repository.getStyleFieldList(prefix, suffix).map {
            val fieldName = it.getName();
            val styleId = it.getInt(null);
            StyleEntity(styleId, fieldName);
        }
    }
}
