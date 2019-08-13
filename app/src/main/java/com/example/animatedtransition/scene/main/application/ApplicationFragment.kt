package com.example.animatedtransition.scene.main.application

import android.os.Bundle
import android.view.View
import androidx.core.app.SharedElementCallback
import androidx.core.view.ViewCompat
import com.example.animatedtransition.BuildConfig
import com.example.animatedtransition.R
import com.example.animatedtransition.core.BaseFragment
import com.example.animatedtransition.extension.getTransition
import com.example.animatedtransition.model.Application
import kotlinx.android.synthetic.main.fragment_application.*
import kotlinx.android.synthetic.main.item_application.*

class ApplicationFragment : BaseFragment() {
    companion object {
        private const val KEY_APP = BuildConfig.APPLICATION_ID + ".APP"
        private const val KEY_ID = BuildConfig.APPLICATION_ID + ".ID"

        fun newInstance(application: Application, id: Int) = ApplicationFragment().apply {
            arguments = Bundle().also {
                it.putSerializable(KEY_APP, application)
                it.putInt(KEY_ID, id)
            }
        }
    }

    override val layoutResId get() = R.layout.fragment_application

    private lateinit var application: Application
    private var itemId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args = arguments ?: throw IllegalArgumentException()

        application = args[KEY_APP] as Application
        itemId = args.getInt(KEY_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ViewCompat.setTransitionName(app_title_text_view, ApplicationTransition.TITLE.name(itemId))
        ViewCompat.setTransitionName(icon_image_view, ApplicationTransition.ICON.name(itemId))
        ViewCompat.setTransitionName(cover_image_view, ApplicationTransition.COVER.name(itemId))
        ViewCompat.setTransitionName(promo_title_text_view, ApplicationTransition.PROMO.name(itemId))
        ViewCompat.setTransitionName(info_background_view, ApplicationTransition.INFO.name(itemId))

        app_title_text_view.text = application.name
        icon_image_view.setImageResource(application.iconResId)
        cover_image_view.setImageResource(application.coverResId)
        promo_title_text_view.text = application.type.titleResId?.let { getString(it) }
        description_text_view.text = application.description

        icon_image_view.clipToOutline = true

        sharedElementEnterTransition = requireContext().getTransition(R.transition.application_enter_transition)
        setEnterSharedElementCallback(sharedElementCallback)
    }

    private val sharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(names: MutableList<String>, sharedElements: MutableMap<String, View>) {
            ApplicationTransition.values().forEach { transition ->
                val view: View = when (transition) {
                    ApplicationTransition.TITLE -> app_title_text_view
                    ApplicationTransition.PROMO -> promo_title_text_view
                    ApplicationTransition.COVER -> cover_image_view
                    ApplicationTransition.ICON -> icon_image_view
                    ApplicationTransition.INFO -> info_background_view
                }
                sharedElements[transition.name(itemId)] = view
            }
        }
    }
}