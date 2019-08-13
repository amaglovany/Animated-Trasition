package com.example.animatedtransition.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {
    @LayoutRes
    protected open val layoutResId: Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (layoutResId > 0) {
            return inflater.inflate(layoutResId, container, false)
        }
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}