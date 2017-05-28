package com.github.nein37.styleviewer;


import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.nein37.styleviewer.databinding.ItemTextappearanceBinding;

import java.lang.reflect.Field;
import java.util.List;

public class TextAppearanceAdapter extends RecyclerView.Adapter<TextAppearanceAdapter.TextAppearanceViewHolder> {

    List<Field> textAppearanceList;

    public TextAppearanceAdapter(List<Field> textAppearanceList) {
        this.textAppearanceList = textAppearanceList;
    }

    @Override
    public TextAppearanceAdapter.TextAppearanceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemTextappearanceBinding bidning = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_textappearance, parent, false);
        return new TextAppearanceViewHolder(bidning);
    }

    @Override
    public void onBindViewHolder(TextAppearanceAdapter.TextAppearanceViewHolder holder, int position) {
        Field field = textAppearanceList.get(position);
        holder.bind(field);
    }

    @Override
    public int getItemCount() {
        return textAppearanceList.size();
    }

    static class TextAppearanceViewHolder extends RecyclerView.ViewHolder {

        ItemTextappearanceBinding binding;

        public TextAppearanceViewHolder(ItemTextappearanceBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Field field) {

            String styleName = field.getName().replace("_", ".");
            int styleId = 0;
            try {
                styleId = field.getInt(null);
            } catch (IllegalAccessException e) {
            }
            TextViewCompat.setTextAppearance(binding.styleName, styleId);
            binding.styleName.setText(styleName);

            int textColor = (0xFFFFFFFF & binding.styleName.getCurrentTextColor());
            binding.textColor.setText(binding.textColor.getResources().getString(R.string.text_color_format, textColor));
            if (textColor == Color.WHITE) {
                binding.getRoot().setBackgroundColor(Color.GRAY);
            } else {
                binding.getRoot().setBackgroundColor(Color.WHITE);
            }

            float textSize = binding.styleName.getTextSize();
            int textSizeSp = Math.round(textSize / binding.getRoot().getResources().getDisplayMetrics().scaledDensity);
            binding.textSize.setText(binding.textSize.getResources().getString(R.string.text_size_format, textSizeSp));

            boolean isBold = false;
            boolean isItalic = false;
            if (binding.styleName.getTypeface() != null) {
                isBold = binding.styleName.getTypeface().isBold();
                isItalic = binding.styleName.getTypeface().isItalic();
            }
            binding.isBold.setText(Boolean.toString(isBold));
            binding.isItalic.setText(Boolean.toString(isItalic));

            boolean isAllCaps = false;
            if (binding.styleName.getTransformationMethod() != null) {
                if (TextUtils.equals(binding.styleName.getTransformationMethod().getClass().getSimpleName(), "AllCapsTransformationMethod")) {
                    isAllCaps = true;
                }
            }
            binding.isAllCaps.setText(Boolean.toString(isAllCaps));
        }
    }
}
