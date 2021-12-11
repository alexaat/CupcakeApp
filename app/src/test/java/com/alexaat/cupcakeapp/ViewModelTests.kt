package com.alexaat.cupcakeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.alexaat.cupcakeapp.Util.getDates
import com.alexaat.cupcakeapp.model.OrderViewModel
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test



class ViewModelTests{

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: OrderViewModel

    @Before
    fun setup(){
        viewModel = OrderViewModel()
    }

    @Test
    fun quantity_twelve_cupcakes(){
        viewModel.setNumberOfCupcakes(12)
        assertEquals(12,viewModel.quantity)
    }

    @Test
    fun price_twelve_cupcakes(){
        viewModel.setNumberOfCupcakes(12)
        viewModel.total.observeForever{}
        assertEquals(36.00, viewModel.total.value)
    }

    @Test
    fun price_twelve_cupcakes_with_next_day_delivery(){
        viewModel.setNumberOfCupcakes(12)
        viewModel.setPickupDate(getDates().first())
        viewModel.total.observeForever{}
        assertEquals(40.00, viewModel.total.value)
    }


}

