package com.example.animatedtransition.scene.main.applications

import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.animatedtransition.core.viewholder.OnViewHolderClickListener
import com.example.animatedtransition.model.Application
import com.example.animatedtransition.scene.main.application.ApplicationTransition
import java.lang.ref.WeakReference

class ApplicationsAdapter(private val items: List<Application>, listener: OnViewHolderClickListener) : RecyclerView.Adapter<ApplicationViewHolder>() {
    private val weakListener = WeakReference(listener)

    fun getItem(position: Int): Application = items[position]
    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApplicationViewHolder =
            ApplicationViewHolder(parent, weakListener)

    override fun onBindViewHolder(holder: ApplicationViewHolder, position: Int) = holder.apply {
        val item = getItem(position)
        appTitleTextView.text = item.name
        iconImageView.setImageResource(item.iconResId)
        coverImageView.setImageResource(item.coverResId)
        promoTitleTextView.text = item.type.titleResId?.let {
            context.getString(it)
        }

        iconImageView.clipToOutline = true

        ViewCompat.setTransitionName(appTitleTextView, ApplicationTransition.TITLE.name(position))
        ViewCompat.setTransitionName(iconImageView, ApplicationTransition.ICON.name(position))
        ViewCompat.setTransitionName(coverImageView, ApplicationTransition.COVER.name(position))
        ViewCompat.setTransitionName(promoTitleTextView, ApplicationTransition.PROMO.name(position))
        ViewCompat.setTransitionName(infoBackgroundView, ApplicationTransition.INFO.name(position))
    }.let { Unit }
}