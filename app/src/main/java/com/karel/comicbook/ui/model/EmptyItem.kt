package com.karel.comicbook.ui.model

import com.karel.comicbook.ui.adapters.IDiffCompare

data class EmptyItem(val message: String): IComicItem {

    override fun areItemsSame(other: IDiffCompare): Boolean {
        return other is EmptyItem // only one at the time
    }

    override fun areContentsSame(other: IDiffCompare): Boolean {
        other as EmptyItem
        return other.message == message
    }
}