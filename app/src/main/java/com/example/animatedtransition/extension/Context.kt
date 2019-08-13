package com.example.animatedtransition.extension

import android.content.Context
import android.transition.TransitionInflater

fun Context.getTransition(resource: Int) =
        TransitionInflater.from(this).inflateTransition(resource)