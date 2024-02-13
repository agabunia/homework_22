package com.example.homework_22.presentation.screen

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.homework_22.databinding.PostLayoutBinding
import com.example.homework_22.presentation.module.Post
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostRecyclerAdapter : ListAdapter<Post, PostRecyclerAdapter.PostViewHolder>(PostDiffUtil()) {

    class PostDiffUtil : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        return PostViewHolder(PostLayoutBinding.inflate(inflate, parent, false))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind()
    }

    inner class PostViewHolder(private val binding: PostLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var post: Post
        private lateinit var context: Context
        private lateinit var imagesRecyclerViewAdapter: ImagesRecyclerViewAdapter

        fun bind() {
            post = currentList[adapterPosition]
            context = binding.root.context

            binding.apply {
                tvUserFirstName.text = post.owner.firstName
                tvUserLastName.text = post.owner.lastName
                if (!post.owner.profile.isNullOrBlank()) {
                    Glide.with(context)
                        .load(post.owner.profile)
                        .transform(CircleCrop())
                        .into(ivAuthorProfileImage)
                }
                tvPostTime.text = getDateTime(post.owner.postDate.toLong())
                tvTitle.text = post.title
                tvCommentNumber.text = post.comments.toString()
                tvLikeNumber.text = post.likes.toString()

                if (!post.images.isNullOrEmpty()) {
                    imagesRecyclerViewAdapter = ImagesRecyclerViewAdapter(post.images!!)
                    rvImages.adapter = imagesRecyclerViewAdapter
                    val staggeredGridLayoutManager =
                        StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
                    rvImages.layoutManager = staggeredGridLayoutManager
//                    rvImages.setHasFixedSize(false)
                }

            }
        }

        private fun getDateTime(epoch: Long): String {
            val date = Date(epoch * 1000)
            val simpleDateFormat = SimpleDateFormat("dd MMMM 'at' h:mm a", Locale.getDefault())
            val formattedDateTime = simpleDateFormat.format(date)
            return formattedDateTime.toString()
        }
    }
}
