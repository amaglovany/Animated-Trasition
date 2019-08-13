package com.example.animatedtransition.scene.main.applications

import android.os.Bundle
import android.transition.TransitionSet
import android.view.View
import android.view.View.OnLayoutChangeListener
import androidx.core.app.SharedElementCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.animatedtransition.BuildConfig
import com.example.animatedtransition.R
import com.example.animatedtransition.core.BaseFragment
import com.example.animatedtransition.core.viewholder.OnViewHolderClickListener
import com.example.animatedtransition.core.widget.SpaceItemDecoration
import com.example.animatedtransition.extension.getTransition
import com.example.animatedtransition.model.Application
import com.example.animatedtransition.scene.main.application.ApplicationFragment
import com.example.animatedtransition.scene.main.application.ApplicationTransition
import kotlinx.android.synthetic.main.fragment_applications.*

class ApplicationsFragment : BaseFragment(), OnViewHolderClickListener {
    companion object {
        const val KEY_POSITION = BuildConfig.APPLICATION_ID + ".POSITION"
    }

    override val layoutResId get() = R.layout.fragment_applications

    private lateinit var adapter: ApplicationsAdapter

    private var clickedPosition: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = mutableListOf(
                Application.STAR_TREK_PROMO,
                Application.STAR_TREK,
                Application.STAR_TREK,
                Application.STAR_TREK,
                Application.STAR_TREK
        )

        adapter = ApplicationsAdapter(items, this)

        clickedPosition = savedInstanceState?.getInt(KEY_POSITION) ?: clickedPosition
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler.addItemDecoration(SpaceItemDecoration(requireContext(), R.dimen.key_line_2))
        recycler.addOnLayoutChangeListener(createLayoutChangeListener())
        recycler.layoutManager = LinearLayoutManager(requireContext())
        recycler.adapter = adapter

        exitTransition = requireContext().getTransition(R.transition.application_exit_transition)
        setExitSharedElementCallback(sharedElementCallback)

        postponeEnterTransition()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt(KEY_POSITION, clickedPosition)
    }

    override fun onViewHolderClick(holder: RecyclerView.ViewHolder, position: Int, id: Int) {
        if (holder !is ApplicationViewHolder) return

        val application = adapter.getItem(position)

        clickedPosition = position

        (exitTransition as TransitionSet).excludeTarget(holder.itemView, true)

        requireFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .addSharedElement(holder.coverImageView, holder.coverImageView.transitionName)
                .addSharedElement(holder.iconImageView, holder.iconImageView.transitionName)
                .addSharedElement(holder.promoTitleTextView, holder.promoTitleTextView.transitionName)
                .addSharedElement(holder.appTitleTextView, holder.appTitleTextView.transitionName)
                .addSharedElement(holder.infoBackgroundView, holder.infoBackgroundView.transitionName)
                .replace(R.id.fragment_container, ApplicationFragment.newInstance(application, position))
                .addToBackStack(null)
                .commit()
    }

    private val sharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(
                names: MutableList<String>,
                sharedElements: MutableMap<String, View>
        ) {
            val holder = recycler.findViewHolderForAdapterPosition(clickedPosition) as? ApplicationViewHolder
                    ?: return

            ApplicationTransition.values().forEach { transition ->
                val view: View = when (transition) {
                    ApplicationTransition.TITLE -> holder.appTitleTextView
                    ApplicationTransition.PROMO -> holder.promoTitleTextView
                    ApplicationTransition.COVER -> holder.coverImageView
                    ApplicationTransition.ICON -> holder.iconImageView
                    ApplicationTransition.INFO -> holder.infoBackgroundView
                }
                sharedElements[transition.name(holder.adapterPosition)] = view
            }
        }
    }

    private fun createLayoutChangeListener() = object : OnLayoutChangeListener {
        override fun onLayoutChange(
                v: View,
                left: Int, top: Int, right: Int, bottom: Int,
                oldLeft: Int, oldTop: Int, oldRight: Int, oldBottom: Int
        ) {
            startPostponedEnterTransition()

            recycler.removeOnLayoutChangeListener(this)

            val manager = recycler.layoutManager ?: return
            val view = manager.findViewByPosition(clickedPosition)

            if (view == null || manager.isViewPartiallyVisible(view, false, true)) {
                recycler.post {
                    manager.scrollToPosition(clickedPosition)
                }
            }
        }
    }
}