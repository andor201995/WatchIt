package com.andor.watchit.core

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.Navigator

fun NavController.safeNavigation(currentDestinationResourceId: Int, actionId: Int) {
    if (currentDestination != null && currentDestination!!.id == currentDestinationResourceId) {
        navigate(actionId)
    }
}

fun NavController.safeNavigation(listCurrentDestinationResourceId: List<Int>, actionId: Int) {
    if (currentDestination != null && listCurrentDestinationResourceId.contains(currentDestination!!.id)) {
        navigate(actionId)
    }
}

fun NavController.safeNavigation(currentDestinationResourceId: Int, action: NavDirections) {
    if (currentDestination != null && currentDestination!!.id == currentDestinationResourceId) {
        navigate(action)
    }
}


fun NavController.safeNavigation(
    currentDestinationResourceId: Int,
    action: NavDirections,
    extra: Navigator.Extras
) {
    if (currentDestination != null && currentDestination!!.id == currentDestinationResourceId) {
        navigate(action, extra)
    }
}

fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.inVisible() {
    this.visibility = View.GONE
}

fun TextView.setTextWithColor(value: String, color: Int) {
    val word: Spannable = getSpanWithColor(value, color)
    this.setText(word, TextView.BufferType.SPANNABLE)
}

fun TextView.appendTextWithColor(value: String, color: Int) {
    val word: Spannable = getSpanWithColor(value, color)
    this.append(word)
}

private fun getSpanWithColor(value: String, color: Int): Spannable {
    val word: Spannable = SpannableString(value)
    word.setSpan(
        ForegroundColorSpan(color),
        0,
        word.length,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )
    return word
}