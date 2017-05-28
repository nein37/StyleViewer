package com.github.nein37.styleviewer.repository

import android.text.TextUtils
import com.github.nein37.styleviewer.R
import java.lang.reflect.Field
import java.util.ArrayList

class StyleRepositoryImpl : StyleRepository {
    override fun getStyleList(prefix: String, suffix: String): List<StyleEntity> {
        return R.style::class.java!!.getFields().filter {
            (!TextUtils.isEmpty(prefix) && it.name.startsWith(prefix))
                    || (!TextUtils.isEmpty(suffix) && it.name.endsWith(suffix))
        }.map {
            val fieldName = it.getName();
            val styleId = it.getInt(null);
            StyleEntity(styleId, fieldName);
        }
    }
}