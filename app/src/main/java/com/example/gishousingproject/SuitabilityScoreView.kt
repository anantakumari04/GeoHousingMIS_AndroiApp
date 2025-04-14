package com.example.gishousingproject

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SuitabilityScoreView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private val paint = Paint().apply {
        color = Color.parseColor("#3F51B5")
        textSize = 60f
        isAntiAlias = true
        textAlign = Paint.Align.CENTER
    }

    private var score = 0

    fun setScoreAnimated(finalScore: Int) {
        ValueAnimator.ofInt(0, finalScore).apply {
            duration = 1500
            addUpdateListener {
                score = it.animatedValue as Int
                invalidate()
            }
            start()
        }
    }

    // If you still want to use setScore directly without animation
    fun setScore(value: Int) {
        score = value
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawText("Suitability Score: $score%", width / 2f, height / 2f, paint)
    }
}
