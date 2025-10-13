package com.ruItSolution.filimnest

import com.denzcoskun.imageslider.models.SlideModel
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate) {

    override fun setAllClickListener() {
        setImageSlider()
    }


    override fun allObserver() {

    }

    private fun setImageSlider() {
        val images = listOf(
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_5,
            R.drawable.img_4,

            )

        val slideImageList = mutableListOf(
            SlideModel(images[0]),
            SlideModel(images[1]),
            SlideModel(images[2]),
            SlideModel(images[3]),
        )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(slideImageList)



    }

}