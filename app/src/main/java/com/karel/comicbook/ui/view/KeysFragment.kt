package com.karel.comicbook.ui.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.karel.comicbook.R
import com.karel.comicbook.databinding.FragmentKeysBinding
import com.karel.comicbook.ui.viewmodels.KeysViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class KeysFragment : Fragment() {

    private var binding: FragmentKeysBinding? = null
    private val viewModel: KeysViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentKeysBinding.inflate(inflater, container, false)
        return binding?.root?.rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar()
        binding?.saveKeysButton?.setOnClickListener {
            lifecycleScope.launchWhenStarted {
                viewModel.saveKeys(
                    binding?.devKeysPrivate?.text.toString(),
                    binding?.devKeysPublic?.text.toString()
                )
                findNavController().popBackStack()
            }
        }
    }

    private fun setUpToolbar() {
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(binding?.devKeysToolbar)
            binding?.devKeysToolbar?.setupWithNavController(findNavController())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}