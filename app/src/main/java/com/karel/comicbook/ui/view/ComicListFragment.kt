package com.karel.comicbook.ui.view

import android.os.Bundle
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.karel.comicbook.R
import com.karel.comicbook.databinding.FragmentComicListBinding
import com.karel.comicbook.domain.exceptions.ErrorCode
import com.karel.comicbook.ui.adapters.ComicBookClickListener
import com.karel.comicbook.ui.adapters.ComicBooksItemDecoration
import com.karel.comicbook.ui.adapters.ComicBooksListAdapter
import com.karel.comicbook.ui.adapters.ErrorRetryListener
import com.karel.comicbook.ui.model.EmptyItem
import com.karel.comicbook.ui.model.ErrorItem
import com.karel.comicbook.ui.model.UiState
import com.karel.comicbook.ui.viewmodels.ComicBooksViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ComicListFragment: Fragment(), ComicBookClickListener, ErrorRetryListener {

    private var binding: FragmentComicListBinding? = null
    private val adapter = ComicBooksListAdapter(comicBookClickListener = this, retryListener = this)
    private val viewModel: ComicBooksViewModel by viewModel()
    private var itemDecoration: ComicBooksItemDecoration = ComicBooksItemDecoration()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentComicListBinding.inflate(inflater, container, false)
        return binding?.root?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.let {
            it.comicListRecycler.adapter = adapter
            it.comicListRecycler.addItemDecoration(itemDecoration)
            it.comicListSwipeRefresh.setOnRefreshListener {
                viewModel.request()
            }
            setupMenu()
            viewModel.uiState.observe(viewLifecycleOwner) { state ->
                hideLoading()
                when(state) {
                    is UiState.NoData -> {
                        adapter.submitList(listOf(EmptyItem(getString(R.string.not_books_found_message))))
                    }
                    is UiState.Data -> {
                        adapter.submitList(state.data)
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
                        adapter.submitList(listOf(ErrorItem(message)))
                    }
                }
            }
            viewModel.request()
        }
    }

    private fun setupMenu() {
        binding?.let {
            it.comicListToolbar.inflateMenu(R.menu.main_menu)
            it.comicListToolbar.setOnMenuItemClickListener { menuItem ->
                when(menuItem.itemId) {
                    R.id.action_settings -> {
                        findNavController().navigate(R.id.action_comicListFragment_to_keysFragment)
                        true
                    }
                    else -> false
                }
            }
        }
    }

    override fun onClick(comicId: String) {
        findNavController().navigate(R.id.action_comicListFragment_to_comicDetailsFragment, bundleOf(
                Pair(COMIC_ID, comicId)))
    }

    override fun onRetry() {
        showLoading()
        viewModel.request()
    }

    private fun hideLoading() {
        binding?.let {
            it.comicListProgressbar.visibility = View.GONE
            it.comicListSwipeRefresh.isRefreshing = false
        }
    }

    private fun showLoading() {
        binding?.comicListProgressbar?.visibility = View.VISIBLE
    }
}