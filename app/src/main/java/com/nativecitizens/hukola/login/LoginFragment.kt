package com.nativecitizens.hukola.login


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

import com.nativecitizens.hukola.R
import com.nativecitizens.hukola.databinding.FragmentLoginBinding


/**
 * A simple [Fragment] subclass.
 *
 */
class LoginFragment : Fragment() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false)

        //the viewModel
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        loginViewModel.navigate.observe(viewLifecycleOwner, Observer { navigate ->
            if(navigate){
                //navigate
                this.findNavController().navigate(R.id.action_loginFragment_to_introSliderFragment)
                //confirm navigation has completed
                loginViewModel.onNavigateCompleted()
            }
        })

        // Inflate the layout for this fragment
        return binding.root
    }


}
