package com.example.animatedtransition.scene.main.applications

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.animatedtransition.R
import com.example.animatedtransition.core.viewholder.OnViewHolderClickListener
import com.example.animatedtransition.core.viewholder.ViewHolder
import java.lang.ref.WeakReference

class ApplicationViewHolder(
        container: ViewGroup,
        weakListener: WeakReference<OnViewHolderClickListener>?
) : ViewHolder(container, R.layout.item_application, weakListener) {
    val coverImageView: ImageView
    val iconImageView: ImageView
    val promoTitleTextView: TextView
    val appTitleTextView: TextView
    val infoBackgroundView: View

    val context: Context get() = itemView.context

    init {
        itemView.clipToOutline = true

        coverImageView = itemView.findViewById(R.id.cover_image_view)
        iconImageView = itemView.findViewById(R.id.icon_image_view)
        promoTitleTextView = itemView.findViewById(R.id.promo_title_text_view)
        appTitleTextView = itemView.findViewById(R.id.app_title_text_view)
        infoBackgroundView = itemView.findViewById(R.id.info_background_view)
    }
}