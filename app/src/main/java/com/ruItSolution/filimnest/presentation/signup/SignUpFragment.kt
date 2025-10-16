package com.ruItSolution.filimnest.presentation.signup

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.ruItSolution.filimnest.MainActivity
import com.ruItSolution.filimnest.core.GoogleSignInClient
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignUpFragment : BaseFragment<FragmentSignUpBinding>(FragmentSignUpBinding::inflate) {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleSignInClient = GoogleSignInClient(requireContext())
    }

    override fun setAllClickListener() {
        binding.signInWithGoogle.setOnClickListener {
            signInWithGoogle()
        }
    }

    override fun allObserver() {

    }


    private fun signInWithGoogle() {
        lifecycleScope.launch {
            if (googleSignInClient.signIn()){
                // Signed in successfully
                Intent(requireContext(), MainActivity::class.java)
                requireActivity().finish()
            }else{
                Toast.makeText(requireContext(), "Sign in failed", Toast.LENGTH_SHORT).show()
            }
        }
    }

}


