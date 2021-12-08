package com.alexaat.cupcakeapp.Util

import java.text.SimpleDateFormat
import java.util.*

val sdf = SimpleDateFormat("EEE dd MMM", Locale.getDefault())

fun getDates():List<String>{
    val calendar = Calendar.getInstance()
    val list = mutableListOf<String>()
    list.add(sdf.format(calendar.time))
    repeat(3){
        calendar.add(Calendar.DAY_OF_MONTH, 1)
        list.add(sdf.format(calendar.time))
    }
    return list
}


