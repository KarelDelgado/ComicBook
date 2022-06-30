package com.karel.comicbook.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuItemCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.karel.comicbook.R
import com.karel.comicbook.databinding.FragmentComicDetailsBinding
import com.karel.comicbook.domain.exceptions.ErrorCode
import com.karel.comicbook.testing.EspressoIdlingResource
import com.karel.comicbook.ui.model.ComicBookDetails
import com.karel.comicbook.ui.model.UiState
import com.karel.comicbook.ui.viewmodels.ComicBookDetailsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


const val COMIC_ID = "comic_id"

class ComicDetailsFragment : Fragment() {

    private var comicId: String? = null
    private val viewModel: ComicBookDetailsViewModel by viewModel()
    private var binding: FragmentComicDetailsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            comicId = it.getString(COMIC_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentComicDetailsBinding.inflate(inflater, container, false)
        return binding?.root?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        requestData()

        binding?.comicBookErrorState?.retryBtn?.setOnClickListener {
            requestData()
        }
    }

    private fun requestData() {
        lifecycleScope.launchWhenStarted {
            comicId?.let {
                EspressoIdlingResource.increment() // testing
                showLoading()
                when(val state = viewModel.requestComicDetails(it)) {
                    is UiState.NoData -> {
                        showNoDataState()
                    }
                    is UiState.Data<ComicBookDetails> -> {
                        bind(state.data)
                    }
                    is UiState.Error -> {
                        val message = when(state.error) {
                            ErrorCode.NO_CONNECTION -> {
                                getString(R.string.you_are_offline_message)
                            }
                            else -> {
                                getString(R.string.unknown_error_message)
                            }
                        }
                        showErrorState(message)
                    }
                }
                hideLoading()
                EspressoIdlingResource.decrement() // testing
            } ?: kotlin.run {
                showNoDataState()
            }
        }
    }

    private fun setUpToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding?.comicBookToolbar)
            binding?.comicBookToolbar?.setupWithNavController(findNavController())
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun bind(comicBookDetails: ComicBookDetails) {
        binding?.let {
            showContent()
            it.comicBookTitle.text = comicBookDetails.title
            it.comicBookDescription.text = comicBookDetails.description
            context?.let { context ->
                Glide.with(context)
                    .load(comicBookDetails.thumbnailUrl)
                    .placeholder(R.mipmap.placeholder_bg)
                    .into(it.comicBookImage)
            }
        }
    }

    private fun showContent() {
        binding?.let {
            it.comicBookDetails.visibility = View.VISIBLE
            it.comicBookEmptyState.emptyState.visibility = View.GONE
            it.comicBookErrorState.errorState.visibility = View.GONE
        }
    }

    private fun showErrorState(message: String) {
        binding?.let {
            it.comicBookDetails.visibility = View.GONE
            it.comicBookEmptyState.emptyState.visibility = View.GONE
            it.comicBookErrorState.apply {
                errorState.visibility = View.VISIBLE
                errorStateMessage.text = message
            }
        }
    }

    private fun showNoDataState() {
        binding?.let {
            it.comicBookDetails.visibility = View.GONE
            it.comicBookEmptyState.emptyState.visibility = View.VISIBLE
            it.comicBookErrorState.errorState.visibility = View.GONE
        }
    }

    private fun hideLoading() {
        binding?.comicBookProgressbar?.visibility = View.GONE
    }

    private fun showLoading() {
        binding?.comicBookProgressbar?.visibility = View.VISIBLE
    }
}