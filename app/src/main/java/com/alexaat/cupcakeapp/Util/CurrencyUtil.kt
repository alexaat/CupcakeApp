package com.alexaat.cupcakeapp.Util

import java.text.NumberFormat

fun formatCurrency(d:Double):String{
    val format = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 2
    return format.format(d)
}



