package com.example.mykeyboard.adapters

import android.graphics.Color
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import com.example.mykeyboard.databinding.KbGreadBinding

class KeyBoardAdapter(
    private val photos: List<Int>,
    private val onItemClick: (Int, Int) -> Unit
)
    : RecyclerView.Adapter<KeyBoardAdapter.KeyBoardHolder>() {

    inner class KeyBoardHolder(val binding: KbGreadBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(photo: Int, position: Int) {
            binding.ivKb.setImageResource(photo)
            val isRewarded = position % 3 == 0
            if (isRewarded) {
                // Показываем иконку подарка и текст "Unlock" для заблокированных тем
                binding.ivUnlock.visibility = View.VISIBLE
            } else {
                // Скрываем элементы блокировки для свободных тем
                binding.ivUnlock.visibility = View.GONE
            }
            binding.root.setOnClickListener {
                onItemClick(photo, position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyBoardAdapter.KeyBoardHolder {
        val binding = KbGreadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return KeyBoardHolder(binding)
    }

    override fun onBindViewHolder(holder: KeyBoardHolder, position: Int) {
        holder.bind(photos[position], position)
    }

    override fun getItemCount(): Int = photos.size

}