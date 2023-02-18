package com.areeb.sekaisheet.ui.spiningWheel.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.sekaisheet.databinding.FragmentSpinnerBinding
import java.util.*

class SpinnerFragment : Fragment() {

    private var _fragmentBinding: FragmentSpinnerBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSpinnerBinding.inflate(layoutInflater, container, false)

        Log.e("randomWord", getRandomWord())
        return _fragmentBinding?.root
    }

    private fun getRandomWord(): String {
        // create an ArrayList of words
        val words = arrayListOf("apple", "banana", "cherry", "date", "elderberry")

        val rand = Random()
        val randomIndex = rand.nextInt(words.size)
        return words[randomIndex]
    }
}
