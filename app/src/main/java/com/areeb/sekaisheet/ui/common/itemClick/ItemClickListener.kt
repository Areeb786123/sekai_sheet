package com.areeb.sekaisheet.ui.common.itemClick

class ItemClickListener<T> (val clickListener: (t: T) -> Unit) {
    fun onClick(t: T) = clickListener(t)
}
