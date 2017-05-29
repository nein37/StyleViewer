package com.github.nein37.styleviewer.presenter


import android.databinding.DataBindingUtil
import android.graphics.Color
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import com.github.nein37.styleviewer.R
import com.github.nein37.styleviewer.databinding.ItemTextappearanceBinding
import com.github.nein37.styleviewer.usecase.TextAppearanceEntity

class TextAppearanceAdapter(internal var textAppearanceList: List<TextAppearanceEntity>) : RecyclerView.Adapter<TextAppearanceAdapter.TextAppearanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAppearanceViewHolder {
        val bidning = DataBindingUtil.inflate<ItemTextappearanceBinding>(LayoutInflater.from(parent.context), R.layout.item_textappearance, parent, false)
        return TextAppearanceViewHolder(bidning)
    }

    override fun onBindViewHolder(holder: TextAppearanceViewHolder, position: Int) {
        val entity = textAppearanceList[position]
        holder.bind(entity)
    }

    override fun getItemCount(): Int {
        return textAppearanceList.size
    }

    class TextAppearanceViewHolder(var binding: ItemTextappearanceBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: TextAppearanceEntity) {

            TextViewCompat.setTextAppearance(binding.styleName, entity.styleId)
            binding.styleName.setText(entity.styleName)


            binding.textColor.text = binding.textColor.resources.getString(R.string.text_color_format, entity.textcolor)
            if (entity.textcolor == Color.WHITE) {
                binding.root.setBackgroundColor(Color.GRAY)
            } else {
                binding.root.setBackgroundColor(Color.WHITE)
            }

            val textSizeSp = Math.round(entity.textSizePx / binding.root.resources.displayMetrics.scaledDensity)
            binding.textSize.text = binding.textSize.resources.getString(R.string.text_size_format, textSizeSp)
            binding.isBold.text = entity.isBold.toString()
            binding.isItalic.text = entity.isItalic.toString()

            // 何かもっとスマートな方法を探したい…
            var isAllCaps = false
            if (binding.styleName.transformationMethod != null) {
                if (TextUtils.equals(binding.styleName.transformationMethod.javaClass.getSimpleName(), "AllCapsTransformationMethod")) {
                    isAllCaps = true
                }
            }
            binding.isAllCaps.text = isAllCaps.toString()
        }
    }
}
