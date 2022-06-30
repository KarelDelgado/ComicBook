package com.karel.comicbook.ui.model

import com.karel.comicbook.ui.adapters.IDiffCompare

class ErrorItem(val message: String): IComicItem {

    override fun areItemsSame(other: IDiffCompare): Boolean {
        return other is ErrorItem // only one at the time
    }

    override fun areContentsSame(other: IDiffCompare): Boolean {
        other as ErrorItem
        return other.message == message
    }
}