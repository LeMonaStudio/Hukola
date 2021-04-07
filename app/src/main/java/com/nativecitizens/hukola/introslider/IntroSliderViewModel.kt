package com.nativecitizens.hukola.introslider


import androidx.lifecycle.ViewModel
import com.nativecitizens.hukola.R

class IntroSliderViewModel: ViewModel(){

    private var data: List<SlideDetails> = listOf(
            SlideDetails(
                1,
                R.string.slide_title_one,
                R.drawable.image_one,
                R.string.slide_info_one),
            SlideDetails(
                2,
                R.string.slide_title_two,
                R.drawable.image_two,
                R.string.slide_info_two),
            SlideDetails(
                3,
                R.string.slide_title_three,
                R.drawable.image_three,
                R.string.slide_info_three),
            SlideDetails(
                4,
                R.string.slide_title_four,
                R.drawable.image_four,
                R.string.slide_info_four),
            SlideDetails(
                5,
                R.string.slide_title_five,
                R.drawable.image_five,
                R.string.slide_info_five),
            SlideDetails(
                6,
                R.string.slide_title_six,
                R.drawable.image_six,
                0)
        )

    init {
        //do nothing
    }

    //this function will be called to give data to the viewPager2's adapter
    fun getData(): List<SlideDetails>{
        return data
    }
}