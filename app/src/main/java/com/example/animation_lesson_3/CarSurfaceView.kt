package com.example.animation_lesson_3

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.view.SurfaceHolder
import android.view.SurfaceView

class CarSurfaceView(context: Context?) : SurfaceView(context), SurfaceHolder.Callback {
    private var carThread: CarThread? = CarThread(holder, this)
    private var bitmap: Bitmap = BitmapFactory.decodeResource(resources, R.drawable.car)
    private var corX: Float = 0f
    private var corY: Float = 0f

    override fun surfaceChanged(
        holder: SurfaceHolder, format: Int, width: Int,
        height: Int
    ) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        carThread?.setRunning(true)
        carThread?.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        var retry = true
        carThread?.setRunning(false)
        while (retry) {
            try {
                carThread?.join()
                retry = false
            } catch (ex: InterruptedException) {
            }
        }
        carThread = null
    }

    fun drawCanvas(canvas: Canvas) {
        canvas.drawColor(Color.WHITE)
        canvas.drawBitmap(bitmap, corX, corY, null)
        update(canvas)
    }

    private fun update(canvas: Canvas) {
        val height = canvas.height.toFloat() - 100f
        val width = canvas.width.toFloat() - 100f
        if (corY != height) {
            corY += 5
            if (corX != width) {
                when(corY) {
                    in 100f..500f, in 800f..1100f, in 1500f..1700f-> {}
                    else -> {corX += 5}
                }
            }
            canvas.drawBitmap(bitmap, corX, corY, null)
        }
    }

    init {
        holder.addCallback(this)
    }
}