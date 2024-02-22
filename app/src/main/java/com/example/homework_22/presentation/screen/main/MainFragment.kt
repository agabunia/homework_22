package com.example.homework_22.presentation.screen.main

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework_22.databinding.FragmentMainBinding
import com.example.homework_22.presentation.adapter.post.PostRecyclerAdapter
import com.example.homework_22.presentation.adapter.story.StoryRecyclerViewAdapter
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.MainEvent
import com.example.homework_22.presentation.state.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var storyViewPagerAdapter: StoryRecyclerViewAdapter
    private lateinit var postRecyclerAdapter: PostRecyclerAdapter

    override fun bind() {
        setStoryRecyclerAdapter()
        setRecyclerAdapter()
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.mainState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: MainState) {
        state.stories?.let {
            storyViewPagerAdapter.submitList(it)
        }

        state.posts?.let {
            postRecyclerAdapter.submitList(it)
            d("FetchPosts", "$it")
        }

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(MainEvent.ResetErrorMessage)
        }

        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private fun setStoryRecyclerAdapter() {
        storyViewPagerAdapter = StoryRecyclerViewAdapter()
        binding.apply {
            rvStory.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            rvStory.setHasFixedSize(true)
            rvStory.adapter = storyViewPagerAdapter
        }
        viewModel.onEvent(MainEvent.FetchStories)
    }

    private fun setRecyclerAdapter() {
        postRecyclerAdapter = PostRecyclerAdapter()
        binding.apply {
            rvPost.layoutManager = LinearLayoutManager(requireContext())
            rvPost.setHasFixedSize(true)
            rvPost.adapter = postRecyclerAdapter
        }
        viewModel.onEvent(MainEvent.FetchPosts)
    }

}