package com.nativecitizens.hukola.home


import android.graphics.drawable.Drawable
import android.graphics.drawable.TransitionDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.DataBindingUtil

import com.nativecitizens.hukola.R
import com.nativecitizens.hukola.databinding.FragmentHomeScreenBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class HomeScreenFragment : Fragment() {

    private lateinit var binding: FragmentHomeScreenBinding
    //private var moreInfoItemsVisible = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_screen,
            container,false)

        val hideShowInfoBtnTransitionDrawable: Drawable = binding.hideShowInfoBtn.drawable

        /*binding.hideShowInfoBtn.setOnClickListener {
            if(moreInfoItemsVisible){
                moreInfoItemsVisible = false
                (binding.hideShowInfoBtn.drawable as TransitionDrawable).startTransition(1000)
            }
        }*/


        binding.moreInfoContainer.setTransitionListener(object: MotionLayout.TransitionListener{
            override fun onTransitionTrigger(p0: MotionLayout?, p1: Int, p2: Boolean, p3: Float) {}

            override fun onTransitionStarted(p0: MotionLayout?, p1: Int, p2: Int) {}

            override fun onTransitionChange(p0: MotionLayout?, p1: Int, p2: Int, p3: Float) {}

            override fun onTransitionCompleted(p0: MotionLayout?, p1: Int) {
                if(hideShowInfoBtnTransitionDrawable is TransitionDrawable){
                    when(p0?.currentState){
                        p0?.endState -> hideShowInfoBtnTransitionDrawable.startTransition(200)
                        p0?.startState -> hideShowInfoBtnTransitionDrawable.reverseTransition(200)
                    }
                }
            }

        })




        return binding.root
    }

}
