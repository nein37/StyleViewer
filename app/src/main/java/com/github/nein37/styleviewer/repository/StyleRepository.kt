package com.github.nein37.styleviewer.repository

import com.github.nein37.styleviewer.R
import java.lang.reflect.Field

class StyleRepository {
    fun getStyleFieldList(prefix: String = "", suffix: String = ""): List<Field> {
        return R.style::class.java.getFields().filter {
            (!prefix.isEmpty() && it.name.startsWith(prefix))
                    || (!suffix.isEmpty() && it.name.endsWith(suffix))
        }
    }
}
