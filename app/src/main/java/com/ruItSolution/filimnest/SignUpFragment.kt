package com.ruItSolution.filimnest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    override fun setAllClickListener() {
        binding.signInWithGoogle.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_containerFragment)
        }
    }

    override fun allObserver() {

    }

}