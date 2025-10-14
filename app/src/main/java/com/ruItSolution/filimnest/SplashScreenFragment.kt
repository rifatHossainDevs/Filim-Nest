package com.ruItSolution.filimnest

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSplashScreenBinding


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {
    override fun setAllClickListener() {
        binding.root.postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment_to_signUpFragment)
        }, 3000)
    }

    override fun allObserver() {

    }

}