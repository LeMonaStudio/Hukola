package com.nativecitizens.hukola.introslider


import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter


private const val ARG_KEY = "SlideDetails"

class IntroSliderAdapter(fragment: Fragment): FragmentStateAdapter(fragment){

    var data: List<SlideDetails> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int  = data.size

    override fun createFragment(position: Int): Fragment {
        val fragment = SlideScreen()
        fragment.arguments = Bundle().apply {
            val sliderDetails = data[position]
            putParcelable(ARG_KEY, sliderDetails)
        }
        return fragment
    }
}