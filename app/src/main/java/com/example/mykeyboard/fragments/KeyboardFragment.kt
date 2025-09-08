package com.example.mykeyboard.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mykeyboard.R
import com.example.mykeyboard.adapters.KeyBoardAdapter
import com.example.mykeyboard.databinding.FragmentKeyboardBinding
import com.example.mykeyboard.dialogs.KeyBoardDialog

class KeyboardFragment : Fragment() {
    private var _binding: FragmentKeyboardBinding? = null
    private val binding get() = _binding!!

    private lateinit var themeAdapter: KeyBoardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
      _binding = FragmentKeyboardBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
    }
    private fun setupRecyclerView() {
        // Sample data - replace with your actual theme data
        val themes = listOf(
            R.drawable.kb_one,
            R.drawable.kb_two,
            R.drawable.kb_three,
            R.drawable.kb_four,
            R.drawable.kb_five,
            R.drawable.kb_six,
            R.drawable.kb_seven,
            R.drawable.kb_eight,
            R.drawable.kb_nine,
            R.drawable.kb_ten,
            R.drawable.kb_eleven,
            R.drawable.kb_twelve,
            R.drawable.kb_thirteen,
            R.drawable.kb_fifteen,
            R.drawable.kb_sixteen,
            R.drawable.kb_fourteen,
        )
        themeAdapter = KeyBoardAdapter(themes) { themeResourceId, position ->
            val isRewarded = isThemeRewarded(position)
            if (isRewarded) {
                val dialog = KeyBoardDialog()
                dialog.show(parentFragmentManager, "KeyBoardDialog")
            } else {
            //    applyTheme(themeResourceId)
            //    showThemeAppliedToast()
            }
        }
        binding.gwKeyBoard.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = themeAdapter
        }
    }
    private fun isThemeRewarded(position: Int): Boolean {
        // Паттерн: 1 заблокированная → 2 свободные → 1 заблокированная → 2 свободные...
        return position % 3 == 0
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}