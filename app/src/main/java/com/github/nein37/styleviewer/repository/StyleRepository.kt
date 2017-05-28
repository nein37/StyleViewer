package com.github.nein37.styleviewer.repository

interface StyleRepository {
    fun getStyleList(prefix: String = "", suffix: String = ""): List<StyleEntity>
}
