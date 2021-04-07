package com.nativecitizens.hukola.introslider



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.nativecitizens.hukola.R
import com.nativecitizens.hukola.databinding.FragmentIntroSliderBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class IntroSliderFragment : Fragment(), SlideScreen.SlideScreenListener {

    private lateinit var introSliderAdapter: IntroSliderAdapter
    private lateinit var binding: FragmentIntroSliderBinding
    private lateinit var introSliderViewModel: IntroSliderViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_intro_slider, container, false)
        introSliderViewModel = ViewModelProvider(this).get(IntroSliderViewModel::class.java)

        //sliderAdapter handed to the viewPager
        introSliderAdapter = IntroSliderAdapter(this)
        introSliderAdapter.data = introSliderViewModel.getData()
        binding.viewPager.adapter = introSliderAdapter

        //set active slide indicator
        setActiveIndicator(0)
        binding.viewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setActiveIndicator(position)
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }


    private fun setActiveIndicator(currentSlidePosition: Int){
        val indicatorCount = binding.introIndicatorContainer.childCount
        for(i in 0 until indicatorCount){
            val imageView = binding.introIndicatorContainer[i] as ImageView
            if(i == currentSlidePosition){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_active)
                )
            } else{
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(requireContext(), R.drawable.indicator_inactive)
                )
            }
        }
    }

    //override the slideScreen fragment's interface callback method
    override fun onSlideScreenContinueButtonClicked() {
        this.findNavController().navigate(R.id.action_introSliderFragment_to_homeScreenFragment)
    }
}
