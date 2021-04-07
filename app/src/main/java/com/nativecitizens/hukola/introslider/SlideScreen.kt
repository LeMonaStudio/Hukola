package com.nativecitizens.hukola.introslider


import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil

import com.nativecitizens.hukola.R
import com.nativecitizens.hukola.databinding.FragmentSlideScreenBinding

/**
 * A simple [Fragment] subclass.
 *
 */

class SlideScreen : Fragment() {
    private lateinit var binding: FragmentSlideScreenBinding

    companion object {
        private const val ARG_KEY = "SlideDetails"
    }

    interface SlideScreenListener{
        fun onSlideScreenContinueButtonClicked()
    }

    //This fragments continue button onClick listener
    private lateinit var slideScreenListener: SlideScreenListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_slide_screen, container, false)


        //get the argument passed to this fragment
        arguments?.takeIf { it.containsKey(ARG_KEY) }?.apply {
            val slideDetails: SlideDetails = getParcelable(ARG_KEY)!!

            //set the spannedString for the Slide title and info
            when(slideDetails.slideNumber){
                1 -> {
                    binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 0,9)
                    binding.slideInfo.text = styleSpan(getString(slideDetails.slideInfoStringId), 0, 6)
                }
                2 ->{
                    binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 0,7)
                    binding.slideInfo.text = styleSpan(getString(slideDetails.slideInfoStringId), 4, 16)
                }
                3 -> {
                    binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 0,5)
                    binding.slideInfo.text = styleSpan(getString(slideDetails.slideInfoStringId), 4, 9)
                }
                4 -> {
                    binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 0,8)
                    binding.slideInfo.text = styleSpan(getString(slideDetails.slideInfoStringId), 0, 8)
                }
                5 -> {
                    binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 12,16)
                    binding.slideInfo.text = styleSpan(getString(slideDetails.slideInfoStringId), 0, 6)
                }
                else -> binding.slideTitle.text = foregroundSpan(getString(slideDetails.slideTitleStringId), 8,15)
            }

            //binding.slideTitle.text = getString(slideDetails.slideTitleStringId)
            //if(slideDetails.slideInfoStringId != 0) binding.slideInfo.text = getString(slideDetails.slideInfoStringId)

            //set the slide image drawable
            binding.slideImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),slideDetails.slideImageId))

            when(slideDetails.slideNumber){
                6 -> binding.continueBtn.visibility = View.VISIBLE
                else -> binding.continueBtn.visibility = View.GONE
            }
        }



        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.continueBtn.setOnClickListener{
            slideScreenListener = requireParentFragment() as SlideScreenListener
            slideScreenListener.onSlideScreenContinueButtonClicked()
        }
    }

    //function call to apply StyleSpan to a given string
    private fun styleSpan(string: String, start: Int, end: Int): SpannableString {
        val spannable = SpannableString(string)
        spannable.setSpan(StyleSpan(Typeface.BOLD), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return spannable
    }

    //function call to apply ForegroundSpan to a given string
    private fun foregroundSpan(string: String, start: Int, end: Int): SpannableString {
        val color = ContextCompat.getColor(requireContext(), R.color.colorSecondary)
        val spannable = SpannableString(string)
        spannable.setSpan(ForegroundColorSpan(color), start, end, Spannable.SPAN_EXCLUSIVE_INCLUSIVE)
        return spannable
    }
}
