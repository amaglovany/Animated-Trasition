package com.example.animatedtransition.scene.main.application

import com.example.animatedtransition.core.transition.Transition

enum class ApplicationTransition : Transition {
    PROMO, COVER, ICON, TITLE, INFO;

    override fun name(id: Int) = "${name}_$id"
}