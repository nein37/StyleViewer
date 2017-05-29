package com.github.nein37.styleviewer.usecase

data class StyleEntity(val styleId: Int = 0,
                       val fieldName: String) {

    val styleName: String
        get() = fieldName.replace("_", ".")

}
