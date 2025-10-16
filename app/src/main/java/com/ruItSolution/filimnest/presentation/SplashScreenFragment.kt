package com.ruItSolution.filimnest.presentation

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ruItSolution.filimnest.MainActivity
import com.ruItSolution.filimnest.R
import com.ruItSolution.filimnest.core.GoogleSignInClient
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
@SuppressLint("CustomSplashScreen")
class SplashScreenFragment : BaseFragment<FragmentSplashScreenBinding>(FragmentSplashScreenBinding::inflate) {

    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        googleSignInClient = GoogleSignInClient(requireContext())
    }

    override fun setAllClickListener() {
        /*binding.root.postDelayed({
            findNavController().navigate(R.id.action_splashScreenFragment2_to_signUpFragment2)
        }, 3000)*/

        isUserLoggedIn()
    }

    override fun allObserver() {

    }

    private fun isUserLoggedIn() {
        lifecycleScope.launch {
            delay(3000)
            if (googleSignInClient.isSignIn()) {
                Intent(requireContext(), MainActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                    requireActivity().finish()
                }
            }else{
                findNavController().navigate(R.id.action_splashScreenFragment2_to_signUpFragment2)
            }
        }
    }

}