package com.k4tr1n4.mlteste.core.extensions

import java.text.NumberFormat
import java.util.Locale

fun Double.formatMoney(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)