package com.example.homework_22.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.ImageLayoutBinding
import com.example.homework_22.presentation.extension.loadImage

class ImagesRecyclerViewAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ImageViewHolder(ImageLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])
    }

    override fun getItemCount(): Int {
        return images.size
    }


    inner class ImageViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: String) {
            binding.sivPostImage.loadImage(image)
        }
    }


}
