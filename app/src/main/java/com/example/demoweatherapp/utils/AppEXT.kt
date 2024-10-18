package com.example.demoweatherapp.utils

import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment

import com.google.android.material.snackbar.Snackbar
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


fun Fragment.showSnackBar(message: String) {
    val snackbar = Snackbar
        .make(this.requireView(), message, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun showSnackBar(view: View, message: String) {
    val snackbar = Snackbar
        .make(view, message, Snackbar.LENGTH_LONG)
    snackbar.show()
}

fun timeEpochToFormatTime(timeEpoch: Long): String {
    val instant = Instant.ofEpochSecond(timeEpoch)

    val dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

    val timeFormatter = DateTimeFormatter.ofPattern("h a")
    return dateTime.format(timeFormatter)
}


fun convertMillisToTime(millis: Long): String {
    val instant = Instant.ofEpochMilli(millis)

    val formatter = DateTimeFormatter.ofPattern("HH:mm:ss")
        .withZone(ZoneId.systemDefault())

    return formatter.format(instant)
}




fun View.visible() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.gone() {
    this.visibility = View.GONE
}
