package com.murat.movielist.utils.extensions

import androidx.fragment.app.Fragment
import androidx.appcompat.app.AlertDialog
import android.widget.Toast

fun androidx.fragment.app.Fragment.toast(message: CharSequence, duration: Int = Toast.LENGTH_SHORT) = activity?.toast(message, duration)

inline fun androidx.fragment.app.Fragment.alertDialog(body: AlertDialog.Builder.() -> AlertDialog.Builder) = activity?.alertDialog(body)
