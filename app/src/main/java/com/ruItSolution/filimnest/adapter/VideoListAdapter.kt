package com.ruItSolution.filimnest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import com.ruItSolution.filimnest.databinding.ItemLayoutHomeBinding
import com.ruItSolution.filimnest.domain.model.VideoModel

class VideoListAdapter(val videoList: List<ResponseGetVideoByCategoryPaginatedDTO.Data.Doc?>) :
    RecyclerView.Adapter<VideoListAdapter.VideoViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VideoViewHolder {
        val binding =
            ItemLayoutHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return videoList.size
    }

    override fun onBindViewHolder(
        holder: VideoViewHolder,
        position: Int
    ) {
        holder.apply {
            binding.apply {
                Glide.with(root.context)
                    .load(videoList[position]!!.thumbnail)
                    .into(imgMovie)
            }
        }
    }

    inner class VideoViewHolder(val binding: ItemLayoutHomeBinding) : RecyclerView.ViewHolder(binding.root)

}