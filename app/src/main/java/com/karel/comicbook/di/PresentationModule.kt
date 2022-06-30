package com.karel.comicbook.di

import com.karel.comicbook.ui.viewmodels.ComicBookDetailsViewModel
import com.karel.comicbook.ui.viewmodels.ComicBooksViewModel
import com.karel.comicbook.ui.viewmodels.KeysViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        ComicBookDetailsViewModel(getComicBook = get())
    }

    viewModel {
        ComicBooksViewModel(getComicBooks = get())
    }

    viewModel {
        KeysViewModel(saveUserKeys = get())
    }
}