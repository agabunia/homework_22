package com.example.homework_22.presentation.screen

import android.util.Log.d
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.homework_22.databinding.FragmentMainBinding
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.MainEvent
import com.example.homework_22.presentation.state.MainState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.math.abs

@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var storyViewPagerAdapter: StoryViewPagerAdapter
    private lateinit var postRecyclerAdapter: PostRecyclerAdapter

    override fun bind() {
        setViewPagerAdapter()
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

    private fun setViewPagerAdapter() {
        storyViewPagerAdapter = StoryViewPagerAdapter()
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(10))
        transformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.85f + r * 0.15f
        }
        binding.apply {
            viewPager.adapter = storyViewPagerAdapter
            viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            viewPager.offscreenPageLimit = 3
            viewPager.setPageTransformer(transformer)
        }
        viewModel.onEvent(MainEvent.FetchStories)
    }

    private fun setRecyclerAdapter() {
        postRecyclerAdapter = PostRecyclerAdapter()
        binding.apply {
            recyclerAdapter.layoutManager = LinearLayoutManager(requireContext())
            recyclerAdapter.setHasFixedSize(true)
            recyclerAdapter.adapter = postRecyclerAdapter
        }
        viewModel.onEvent(MainEvent.FetchPosts)
    }

}