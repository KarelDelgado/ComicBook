package com.karel.comicbook.ui.adapters

interface IDiffCompare {
    fun areItemsSame(other: IDiffCompare): Boolean
    fun areContentsSame(other: IDiffCompare): Boolean
}