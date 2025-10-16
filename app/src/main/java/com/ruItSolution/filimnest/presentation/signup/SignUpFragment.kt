package com.ruItSolution.filimnest.presentation.signup

import androidx.navigation.fragment.findNavController
import com.ruItSolution.filimnest.R
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {
    override fun setAllClickListener() {
        binding.signInWithGoogle.setOnClickListener {
            //findNavController().navigate(R.id.action_signUpFragment_to_containerFragment)
        }
    }

    override fun allObserver() {

    }

}