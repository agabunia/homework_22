package com.example.homework_22.presentation.adapter.story

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.homework_22.databinding.StoryLayoutBinding
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.model.Story

class StoryRecyclerViewAdapter :
    ListAdapter<Story, StoryRecyclerViewAdapter.StoryViewHolder>(StoryDiffUtil()) {

    class StoryDiffUtil : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Story, newItem: Story): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return StoryViewHolder(StoryLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind()
    }

    inner class StoryViewHolder(private val binding: StoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var story: Story

        fun bind() {
            story = currentList[adapterPosition]

            binding.apply {
                tvTitle.text = story.title
                sivImage.loadImage(story.cover)
            }
        }
    }

}
