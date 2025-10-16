package com.ruItSolution.filimnest.presentation

import android.annotation.SuppressLint
import androidx.navigation.fragment.findNavController
import com.ruItSolution.filimnest.R
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    override fun setAllClickListener() {
        binding.root.postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment2_to_signUpFragment2)
        }, 3000)
    }

    override fun allObserver() {

    }

}