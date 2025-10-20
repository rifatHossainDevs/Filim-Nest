package com.ruItSolution.filimnest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import com.ruItSolution.filimnest.databinding.ItemLayoutHomeBinding
import com.ruItSolution.filimnest.domain.model.VideoModel

class SearchVideoAdapter(
    private val videoList: List<VideoModel>,   // <-- VideoModel
    private val onItemClick: (videoId: String) -> Unit
) : RecyclerView.Adapter<SearchVideoAdapter.VideoViewHolder>() {

    inner class VideoViewHolder(val binding: ItemLayoutHomeBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemLayoutHomeBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return VideoViewHolder(binding)
    }

    override fun getItemCount(): Int = videoList.size

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val video = videoList[position]
        holder.binding.apply {
            Glide.with(root.context)
                .load(video.thumbnail)
                .into(imgMovie)

            root.setOnClickListener {
                onItemClick(video.id)
            }
        }
    }
}

