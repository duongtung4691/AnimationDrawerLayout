package com.example.myapplication

import android.view.animation.Animation
import android.graphics.Camera
import android.view.animation.Transformation






class Rotate3dAnimation : Animation{
    private val mFromDegrees: Float
    private val mToDegrees: Float
    private val mCenterX: Float
    private val mCenterY: Float
    private val mDepthZ: Float
    private val mReverse: Boolean
    private var mCamera: Camera? = null


     constructor( fromDegrees : Float,toDegrees: Float,
                  centerX: Float,  centerY: Float,  depthZ: Float, reverse: Boolean){
         mFromDegrees = fromDegrees
         mToDegrees = toDegrees
         mCenterX = centerX
         mCenterY = centerY
         mDepthZ = depthZ
         mReverse = reverse
    }

    override fun initialize(width: Int, height: Int, parentWidth: Int, parentHeight: Int) {
        super.initialize(width, height, parentWidth, parentHeight)
        mCamera = Camera()

    }

    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        val fromDegrees = mFromDegrees
        val degrees = fromDegrees + (mToDegrees - fromDegrees) * interpolatedTime

        val centerX = mCenterX
        val centerY = mCenterY
        val camera = mCamera

        val matrix = t?.matrix

        camera?.save()

        if (mReverse) {
            camera?.translate(0.0f, 0.0f, mDepthZ * interpolatedTime)
        } else {
            camera?.translate(0.0f, 0.0f, mDepthZ * (1.0f - interpolatedTime))
        }
        camera?.rotateY(degrees)
//        camera?.rotateZ(degrees)
        camera?.getMatrix(matrix)
        camera?.restore()

        matrix?.preTranslate(-centerX, -centerY)
        matrix?.postTranslate(centerX, centerY)
    }
}