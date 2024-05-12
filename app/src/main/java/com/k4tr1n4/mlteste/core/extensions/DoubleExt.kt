package com.k4tr1n4.mlteste.core.extensions

import com.k4tr1n4.mlteste.BuildConfig
import java.text.NumberFormat
import java.util.Locale

fun Double.formatMoney(): String =
    NumberFormat
        .getCurrencyInstance(Locale(BuildConfig.BASE_LANGUAGE, BuildConfig.BASE_COUNTRY))
        .format(this)