package com.example.mykeyboard.adapters

import android.content.res.Resources
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mykeyboard.R
import com.example.mykeyboard.data.FontInem
import com.example.mykeyboard.databinding.FontsCardBinding


class FontsAdapter(
    private val fonts: List<FontInem>,
    private val onItemClick: (FontInem, Int) -> Unit): RecyclerView.Adapter<FontsAdapter.FontsBoardHolder>()  {

    inner class FontsBoardHolder(val binding: FontsCardBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: FontInem, position: Int) {
            binding.tvFonts.text = item.name
            //проверяем если есть font
            try {
                val typeface = ResourcesCompat.getFont(binding.root.context, item.styleRes)
                binding.tvFonts.typeface = typeface
            } catch (e: Resources.NotFoundException) {
                Log.e("FontsAdapter", "Font resource not found: ${item.styleRes}")
            }
            if (position < 3) {
                binding.imAddFonts.setImageResource(R.drawable.added_fonts)
                binding.imAddFonts.isClickable = true
            } else {
                binding.imAddFonts.setImageResource(R.drawable.add_fonts)
                binding.imAddFonts.isClickable = false
            }

                //убераем последний divider
            if (position == fonts.lastIndex) {
                binding.divider.visibility = View.GONE
            } else {
                binding.divider.visibility = View.VISIBLE
            }

            binding.root.setOnClickListener {
                onItemClick(item, position)
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FontsAdapter.FontsBoardHolder {

        val binding = FontsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FontsBoardHolder(binding)
    }

    override fun onBindViewHolder(holder: FontsAdapter.FontsBoardHolder, position: Int) {
        holder.bind(fonts[position], position)
    }

    override fun getItemCount(): Int = fonts.size

}