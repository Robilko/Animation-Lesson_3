package com.example.animation_lesson_3

import android.graphics.*
import android.view.SurfaceHolder
import java.lang.Exception


internal class CarThread(private val surfaceHolder: SurfaceHolder, private  val carSurfaceView: CarSurfaceView) :
    Thread() {
    private var runFlag = false

    fun setRunning(run: Boolean) {
        runFlag = run
    }

    override fun run() {
        var canvas: Canvas?
        while (runFlag) {
            canvas = null
            try {
                canvas = surfaceHolder.lockCanvas(null)
                synchronized(surfaceHolder) {
                    carSurfaceView.drawCanvas(canvas)
                    sleep(10)
                }
            }catch (ex: Exception) {

            } finally {
                if (canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas)
                }
            }
        }
    }
}