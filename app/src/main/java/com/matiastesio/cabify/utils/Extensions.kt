package com.matiastesio.cabify.utils

import android.view.View
import android.view.View.IMPORTANT_FOR_ACCESSIBILITY_YES
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.matiastesio.cabify.R

fun LottieAnimationView.startViewWithAnimation(animation: Int) {
    setAnimation(animation)
    repeatCount = LottieDrawable.INFINITE
    playAnimation()
    visibility = View.VISIBLE
}

fun TextView.setTextWithContentDescription(value: String) {
    importantForAccessibility = IMPORTANT_FOR_ACCESSIBILITY_YES
    text = value
    contentDescription = value
}