package com.example.homework_22.presentation.screen.detailed

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.homework_22.databinding.FragmentDetailedBinding
import com.example.homework_22.presentation.base.BaseFragment
import com.example.homework_22.presentation.event.DetailedEvent
import com.example.homework_22.presentation.extension.loadImage
import com.example.homework_22.presentation.state.DetailedState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DetailedFragment : BaseFragment<FragmentDetailedBinding>(FragmentDetailedBinding::inflate) {
    private val viewModel: DetailedViewModel by viewModels()

    override fun bind() {
    }

    override fun bindListeners() {
    }

    override fun bindObserves() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.detailedState.collect {
                    handleState(it)
                }
            }
        }
    }

    private fun handleState(state: DetailedState) {
        state.post?.let {
            binding.apply {
                ivAuthorProfileImage.loadImage(it.owner.profile)
                tvUserFirstName.text = it.owner.firstName
                tvUserLastName.text = it.owner.lastName
                tvPostTime.text = it.owner.postDate
                tvTitle.text = it.title
                ivImage.loadImage(it.images[0])
                tvCommentNumber.text = it.comments.toString()
                tvLikeNumber.text = it.likes.toString()
            }
        }

        state.errorMessage?.let {
            toastMessage(it)
            viewModel.onEvent(DetailedEvent.ResetErrorMessage)
        }

        binding.progressBar.visibility =
            if (state.isLoading) View.VISIBLE else View.GONE
    }

    private fun toastMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

}