package com.example.homework_22.presentation.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.homework_22.databinding.StoryLayoutBinding
import com.example.homework_22.presentation.module.Story

class StoryViewPagerAdapter :
    ListAdapter<Story, StoryViewPagerAdapter.StoryViewHolder>(StoryDiffUtil()) {

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
        private lateinit var context: Context

        fun bind() {
            story = currentList[adapterPosition]
            context = binding.root.context

            binding.apply {
                tvTitle.text = story.title
                Glide.with(context).load(story.cover).into(sivImage)
            }
        }
    }

}
