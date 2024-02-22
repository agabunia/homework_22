package com.example.homework_22.presentation.adapter.custom_layout_manager

import android.util.Log.d
import androidx.recyclerview.widget.RecyclerView

class CustomLayoutManager() : RecyclerView.LayoutManager() {

    private var offsetWidth = 0
    private var offsetHeight = 0

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            RecyclerView.LayoutParams.MATCH_PARENT,
            RecyclerView.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler, state: RecyclerView.State?) {
        d("CustomLayoutManager", "onLayoutChildren called")
        fill(recycler)
    }

    private fun fill(recycler: RecyclerView.Recycler) {
        d("CustomLayoutManager", "fill function called")
        if (itemCount == 0) return
        detachAndScrapAttachedViews(recycler)

        val fullWidth = width
        val fullHeight = height

        for (position in 0 until itemCount) {

            val view = recycler.getViewForPosition(position)
            addView(view)

            val layoutParams = view.layoutParams as RecyclerView.LayoutParams
            d("CustomLayoutManager", "Width: ${layoutParams.width}, Height: ${layoutParams.height}")

            when (itemCount) {
                1 -> {
                    layoutParams.width = fullWidth
                    layoutParams.height = fullHeight
                }

                2 -> {
                    layoutParams.width = fullWidth / 2
                    layoutParams.height = fullHeight
                    offsetWidth += layoutParams.width
                }

                3 -> {
                    when (position) {
                        0 -> {
                            layoutParams.width = fullWidth / 2
                            layoutParams.height = fullHeight
                        }

                        else -> {
                            layoutParams.width = fullWidth / 2
                            layoutParams.height = fullHeight / 2
                        }
                    }
                }

                else -> {
                    layoutParams.width = 0
                    layoutParams.height = 0
                }
            }

            view.layoutParams = layoutParams
        }

        val scrapListCopy = recycler.scrapList.toList()
        scrapListCopy.forEach {
            recycler.recycleView(it.itemView)
        }
    }

}