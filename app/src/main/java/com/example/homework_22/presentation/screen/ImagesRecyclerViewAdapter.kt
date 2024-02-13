package com.example.homework_22.presentation.screen

import android.content.Context
import android.util.Log.d
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_22.databinding.ImageLayoutBinding

class ImagesRecyclerViewAdapter(private val images: List<String>) :
    RecyclerView.Adapter<ImagesRecyclerViewAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return ImagesViewHolder(ImageLayoutBinding.inflate(inflate, parent, false))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        holder.bind(images[position])
    }

    inner class ImagesViewHolder(private val binding: ImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var context: Context

        fun bind(image: String) {
            context = binding.root.context
            d("adapter", image)
            Glide.with(context)
                .load(image)
                .into(binding.sivPostImage)
        }
    }

}
