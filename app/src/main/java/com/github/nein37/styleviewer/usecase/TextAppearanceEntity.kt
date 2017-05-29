package com.github.nein37.styleviewer.usecase


data class TextAppearanceEntity(val styleId: Int = 0,
                                val fieldName: String,
                                val textcolor: Int,
                                val textSizePx: Int,
                                val isBold: Boolean = false,
                                val isItalic: Boolean = false) {

    val styleName: String
        get() = fieldName.replace("_", ".")


}
