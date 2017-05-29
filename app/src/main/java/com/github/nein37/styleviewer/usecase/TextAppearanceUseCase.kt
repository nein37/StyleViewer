package com.github.nein37.styleviewer.usecase

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Color
import com.github.nein37.styleviewer.R
import com.github.nein37.styleviewer.repository.StyleRepository

class TextAppearanceUseCase(val context: Context) {

    private val TextAppearance_textSize = 0
    private val TextAppearance_textStyle = 2
    private val TextAppearance_textColor = 3


    fun TypedArray.contains(index: Int): Boolean {
        try {
            this.hasValue(index)
            return true;
        } catch(ex: ArrayIndexOutOfBoundsException) {
            return false;
        }
    }

    fun TypedArray.getIntSafely(index: Int, defValue: Int): Int {
        return if (contains(index)) getInt(index, defValue) else defValue
    }

    fun TypedArray.getColorSafely(index: Int, defValue: Int): Int {
        return if (contains(index)) getColor(index, defValue) else defValue
    }

    fun TypedArray.getDimensionPixelSizeSafely(index: Int, defValue: Int): Int {
        return if (contains(index)) getDimensionPixelSize(index, defValue) else defValue
    }

    val repository = StyleRepository();

    fun getStyleList(prefix: String = "", suffix: String = ""): List<TextAppearanceEntity> {
        return repository.getStyleFieldList(prefix, suffix).map {
            val fieldName = it.getName()
            val styleId = it.getInt(null)

            convertTextAppearanceEntity(context, styleId, fieldName)
        }
    }

    fun convertTextAppearanceEntity(context: Context, styleId: Int, fieldName: String): TextAppearanceEntity {
        context.theme.obtainStyledAttributes(styleId, R.styleable.TextAppearance).let {
            val textColor = 0xFFFFFFFF.toInt() and it.getColorSafely(TextAppearance_textColor, Color.WHITE)
            val textSizePx = it.getDimensionPixelSizeSafely(TextAppearance_textSize, 0)
            val textStyle = it.getIntSafely(TextAppearance_textStyle, 0)
            val isBold = textStyle.let { it and 1 == 1 }
            val isItalic = textStyle.let { it and 2 == 2 }
            return TextAppearanceEntity(styleId, fieldName, textColor, textSizePx, isBold, isItalic)
        }
    }
}
