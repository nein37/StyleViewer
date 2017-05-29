package com.github.nein37.styleviewer.presenter


import android.graphics.Color
import android.support.v4.widget.TextViewCompat
import android.support.v7.widget.RecyclerView
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.nein37.styleviewer.R
import com.github.nein37.styleviewer.usecase.TextAppearanceEntity
import kotlinx.android.synthetic.main.item_textappearance.view.*

class TextAppearanceAdapter(internal var textAppearanceList: List<TextAppearanceEntity>) : RecyclerView.Adapter<TextAppearanceAdapter.TextAppearanceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TextAppearanceViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_textappearance, parent, false)
        return TextAppearanceViewHolder(view)
    }

    override fun onBindViewHolder(holder: TextAppearanceViewHolder, position: Int) {
        val entity = textAppearanceList[position]
        holder.bind(entity)
    }

    override fun getItemCount(): Int {
        return textAppearanceList.size
    }

    class TextAppearanceViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(entity: TextAppearanceEntity) {
            TextViewCompat.setTextAppearance(itemView.styleName, entity.styleId)
            itemView.styleName.setText(entity.styleName)


            itemView.textColor.text = itemView.textColor.resources.getString(R.string.text_color_format, entity.textcolor)
            if (entity.textcolor == Color.WHITE) {
                itemView.setBackgroundColor(Color.GRAY)
            } else {
                itemView.setBackgroundColor(Color.WHITE)
            }

            val textSizeSp = Math.round(entity.textSizePx / itemView.resources.displayMetrics.scaledDensity)
            itemView.textSize.text = itemView.textSize.resources.getString(R.string.text_size_format, textSizeSp)
            itemView.isBold.text = entity.isBold.toString()
            itemView.isItalic.text = entity.isItalic.toString()

            // 何かもっとスマートな方法を探したい…
            var isAllCaps = false
            if (itemView.styleName.transformationMethod != null) {
                if (TextUtils.equals(itemView.styleName.transformationMethod.javaClass.getSimpleName(), "AllCapsTransformationMethod")) {
                    isAllCaps = true
                }
            }
            itemView.isAllCaps.text = isAllCaps.toString()
        }
    }
}
