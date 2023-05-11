package com.example.appcenttest

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView

class GraySpaceItemDecoration(private val spaceHeight: Int) : RecyclerView.ItemDecoration() {

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val child = parent.getChildAt(i)
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom + params.bottomMargin
            val bottom = top + spaceHeight

            val paint = Paint()
            paint.color = Color.GRAY

            c.drawRect(
                child.left.toFloat(),
                top.toFloat(),
                child.right.toFloat(),
                bottom.toFloat(),
                paint
            )
        }
    }
}