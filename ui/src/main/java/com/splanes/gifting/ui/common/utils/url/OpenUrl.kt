package com.splanes.gifting.ui.common.utils.url

import android.content.Context
import android.content.Intent
import android.net.Uri

fun Context.openUrl(url: String) =
    startActivity(Intent(Intent.ACTION_VIEW).apply { data = Uri.parse(url) })
