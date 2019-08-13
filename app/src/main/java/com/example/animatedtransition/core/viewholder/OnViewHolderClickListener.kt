package com.example.animatedtransition.core.viewholder

import androidx.recyclerview.widget.RecyclerView

interface OnViewHolderClickListener {
    fun onViewHolderClick(holder: RecyclerView.ViewHolder, position: Int, id: Int) = Unit
}