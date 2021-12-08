package com.alexaat.cupcakeapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.alexaat.cupcakeapp.Util.*
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel: ViewModel() {

    private val _navigateToFlavor = MutableLiveData<Event<Boolean>>()
    val navigateToFlavor: LiveData<Event<Boolean>>
        get() = _navigateToFlavor

    private val _navigateToPickupDate = MutableLiveData<Event<Boolean>>()
    val navigateToPickupDate: LiveData<Event<Boolean>>
        get() = _navigateToPickupDate

    private val _navigateToStart = MutableLiveData<Event<Boolean>>()
    val navigateToStart: LiveData<Event<Boolean>>
        get() = _navigateToStart

    private val _navigateToSummary = MutableLiveData<Event<Boolean>>()
    val navigateToSummary: LiveData<Event<Boolean>>
        get() = _navigateToSummary

    private val _navigateToAnotherApp = MutableLiveData<Event<Boolean>>()
    val navigateToAnotherApp: LiveData<Event<Boolean>>
        get() = _navigateToAnotherApp

    private val _total = MutableLiveData<Double>()
    val total: LiveData<Double>
        get() = _total

    private var _flavor = Flavor.VANILLA
    val flavor: Flavor
        get() = _flavor

    private var _quantity = 1
    val quantity: Int
        get() = _quantity

    private var _pickupDate = getDates().last()
    val pickupDate:String
        get()=_pickupDate

    fun setNumberOfCupcakes(quantity: Int){
        _quantity = if(quantity<0) 0 else quantity
        _total.value = quantity*PRICE
        navigateToFlavor()
    }

    fun setFlavor(flavor: Flavor){
        _flavor = flavor
    }

    private fun navigateToFlavor(){
        _navigateToFlavor.value = Event(true)
    }

    fun navigateToPickupDate(){
        _navigateToPickupDate.value = Event(true)
    }

    fun navigateToSummary(){
        _navigateToSummary.value = Event(true)
    }

    fun cancelOrder(){
        setFlavor(Flavor.VANILLA)
        setPickupDate(getDates().last())
        _navigateToStart.value = Event(true)
    }

    fun setPickupDate(date: CharSequence){
        _pickupDate = date.toString()
        if(date.toString()== getDates().first()){
            _total.value = quantity* PRICE+ SAME_DAY_DELIVERY_CHARGE
        }else{
            _total.value = quantity* PRICE
        }
    }

    fun shareOrder(){
        _navigateToAnotherApp.value = Event(true)
    }






}