package com.github.nein37.styleviewer


import android.content.res.TypedArray
import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup

import com.github.nein37.styleviewer.databinding.ItemTextappearanceBinding
import com.github.nein37.styleviewer.repository.StyleEntity

import java.lang.reflect.Field

class TextAppearanceAdapter(internal var textAppearanceList: List<StyleEntity>) : RecyclerView.Adapter<TextAppearanceAdapter.TextAppearanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAppearanceAdapter.TextAppearanceViewHolder {
        val bidning = DataBindingUtil.inflate<ItemTextappearanceBinding>(LayoutInflater.from(parent.context), R.layout.item_textappearance, parent, false)
        return TextAppearanceViewHolder(bidning)
    }

    override fun onBindViewHolder(holder: TextAppearanceAdapter.TextAppearanceViewHolder, position: Int) {
        val entity = textAppearanceList[position]
        holder.bind(entity)
    }

    override fun getItemCount(): Int {
        return textAppearanceList.size
    }

    class TextAppearanceViewHolder(var binding: ItemTextappearanceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: StyleEntity) {

            val styleName = entity.styleName.replace("_", ".")
            var styleId = entity.styleId;
            TextViewCompat.setTextAppearance(binding.styleName, styleId)
            binding.styleName.setText(styleName)

            val textColor = 0xFFFFFFFF.toInt() and binding.styleName.currentTextColor
            binding.textColor.text = binding.textColor.resources.getString(R.string.text_color_format, textColor)
            if (textColor == Color.WHITE) {
                binding.root.setBackgroundColor(Color.GRAY)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }

            val textSize = binding.styleName.textSize
            val textSizeSp = Math.round(textSize / binding.root.resources.displayMetrics.scaledDensity)
            binding.textSize.text = binding.textSize.resources.getString(R.string.text_size_format, textSizeSp)

            var isBold = false
            var isItalic = false
            if (binding.styleName.typeface != null) {
                isBold = binding.styleName.typeface.isBold
                isItalic = binding.styleName.typeface.isItalic
            }
            binding.isBold.text = java.lang.Boolean.toString(isBold)
            binding.isItalic.text = java.lang.Boolean.toString(isItalic)

            var isAllCaps = false
            if (binding.styleName.transformationMethod != null) {
                if (TextUtils.equals(binding.styleName.transformationMethod.javaClass.getSimpleName(), "AllCapsTransformationMethod")) {
                    isAllCaps = true
                }
            }
            binding.isAllCaps.text = java.lang.Boolean.toString(isAllCaps)
        }
    }
}
