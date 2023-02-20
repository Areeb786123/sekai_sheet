package com.areeb.sekaisheet.ui.spiningWheel.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.areeb.sekaisheet.ui.base.MainActivity
import com.areeb.sekaisheet.ui.base.fragment.BaseFragment
import com.areeb.sekaisheet.utils.CollectionData.moodSingletonText
import com.areeb.sekaisheet.utils.visible
import com.example.sekaisheet.databinding.FragmentSpinnerBinding
import dagger.hilt.android.AndroidEntryPoint
import org.json.JSONObject
import java.util.*

@AndroidEntryPoint
class SpinnerFragment : BaseFragment(), View.OnClickListener {

    private var _fragmentBinding: FragmentSpinnerBinding? = null
    private val fragmentBinding get() = _fragmentBinding!!
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val MOOD_SPINNER_DELAY = 3000L
        private const val INTENT_DELAY = 4000L
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _fragmentBinding = FragmentSpinnerBinding.inflate(layoutInflater, container, false)

        return _fragmentBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewClickListener()
        fragmentBinding.moodTextView.text = getRandomWord()
        moodSingletonText = fragmentBinding.moodTextView.text.toString()
    }

    private fun getRandomWord(): String? {
        // create an ArrayList of words
        val words = getMoodData()

        val rand = Random()
        val randomIndex = words?.size?.let { rand.nextInt(it) }
        return randomIndex?.let { words.get(it) }
    }

    private fun onViewClickListener() {
        fragmentBinding.spinnerButton.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            fragmentBinding.spinnerButton.id -> {
                runMoodSpinner()
                handler.postDelayed({
                    MainActivity.intent(requireContext())
                }, INTENT_DELAY)
            }
        }
    }

    private fun runMoodSpinner() {
        fragmentBinding.spinnerLotteAnimation.playAnimation()
        handler.postDelayed({
            fragmentBinding.spinnerLotteAnimation.visible(false)
            fragmentBinding.moodTextView.visible(true)
        }, MOOD_SPINNER_DELAY)
    }

    private fun getMoodData(): List<String?>? {
        val jsonString = context?.assets?.open("mood.json")?.bufferedReader()?.use {
            it.readText()
        }
        val jsonObject = JSONObject(jsonString)
        val moodArray = jsonObject.getJSONArray("mood")
        val moodTitles = mutableListOf<String>()
        for (i in 0 until moodArray.length()) {
            val moodObject = moodArray.getJSONObject(i)
            val moodTitle = moodObject.optString("mood_title")
            if (moodTitle.isNotEmpty()) {
                moodTitles.add(moodTitle)
            }
        }
        return if (moodTitles.isEmpty()) null else moodTitles
    }
}
