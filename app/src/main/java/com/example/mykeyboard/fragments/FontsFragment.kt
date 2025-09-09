package com.example.mykeyboard.fragments

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.RecyclerListener
import com.example.mykeyboard.R
import com.example.mykeyboard.adapters.FontsAdapter
import com.example.mykeyboard.data.FontInem
import com.example.mykeyboard.databinding.FragmentFontsBinding
import com.example.mykeyboard.databinding.FragmentKeyboardBinding
import com.example.mykeyboard.dialogs.FontsDialog
import com.example.mykeyboard.dialogs.KeyBoardDialog
import com.example.mykeyboard.dialogs.KeyBoardDialogFree

class FontsFragment : Fragment() {
    private var _binding: FragmentFontsBinding? = null
    private val binding get() = _binding!!
    private  lateinit var fontsAdapter: FontsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFontsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textHeader()
        setupFontsForAdapter()

    }
    fun textHeader(){
        val textView: TextView = binding.tvHeaderFonts
        val width = textView.paint.measureText(textView.text.toString())
        val textShader = LinearGradient(
            0f, 0f, 5f, -30f,
            intArrayOf(
                Color.parseColor("#4E75F6"), // start color
                Color.parseColor("#7642FF"), // center color
                Color.parseColor("#A315FD"), // end color
            ),
            null,
            Shader.TileMode.CLAMP
        )
        textView.paint.shader = textShader
    }

    private fun setupFontsForAdapter (){
        val fonts = listOf(
           FontInem("Carben", R.font.corben),
            FontInem("Roboto", R.font.roboto),
            FontInem("Lobster", R.font.lobster),
            FontInem("Trochut", R.font.trochut),
            FontInem("Pacifico", R.font.pacifico),
            FontInem("Courgette", R.font.courgette),
            FontInem("Homemade", R.font.homemade_apple),
            FontInem("Chocolate", R.font.ofont_chocolate),
            FontInem("Encode Sans", R.font.encode_sans_sc_thin)
        )
        fontsAdapter = FontsAdapter(fonts){ themeResourceId, points ->
            val dialog = FontsDialog()
            dialog.show(parentFragmentManager, "KeyBoardDialog")
        }
        binding.rcFontsFragment.layoutManager = LinearLayoutManager(context)
        binding.rcFontsFragment.adapter = fontsAdapter

    }

}