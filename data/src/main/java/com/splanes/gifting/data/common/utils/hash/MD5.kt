package com.splanes.gifting.data.common.utils.hash // ktlint-disable filename

import com.google.android.gms.common.util.Hex
import java.security.MessageDigest

fun String.md5() =
    MessageDigest.getInstance("MD5").apply {
        update(toByteArray())
    }.digest().let { bytes -> Hex.bytesToStringLowercase(bytes) }
