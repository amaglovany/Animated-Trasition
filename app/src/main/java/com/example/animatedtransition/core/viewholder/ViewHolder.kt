package com.example.animatedtransition.core.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import java.lang.ref.WeakReference

abstract class ViewHolder(
        container: ViewGroup,
        @LayoutRes layoutResId: Int,
        private val weakListener: WeakReference<OnViewHolderClickListener>?
) : RecyclerView.ViewHolder(
        LayoutInflater.from(container.context).inflate(layoutResId, container, false)
) {
    init {
        if (weakListener != null) {
            itemView.isClickable = true
            itemView.setOnClickListener {
                weakListener.get()?.onViewHolderClick(this, adapterPosition, it.id)
            }
        }
    }
}