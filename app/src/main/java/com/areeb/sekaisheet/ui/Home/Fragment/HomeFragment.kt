package com.areeb.sekaisheet.ui.Home.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.areeb.sekaisheet.ui.Home.ViewModel.HomeViewModel
import com.example.sekaisheet.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _fragmentBinding: FragmentHomeBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getWallpaper()
        setObserver()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        _fragmentBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        return _fragmentBinding?.root
    }

    private fun setObserver() {
        Log.e("ii","I frago is called ")
        viewModel.wallpaper.observe(viewLifecycleOwner) {
            Log.e("ansAA", it.toString())
            Log.e("ansAAA", "inside aaa")
        }
    }
}
