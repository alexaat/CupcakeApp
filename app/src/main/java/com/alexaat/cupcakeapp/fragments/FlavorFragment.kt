package com.alexaat.cupcakeapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.alexaat.cupcakeapp.R
import com.alexaat.cupcakeapp.Util.formatCurrency
import com.alexaat.cupcakeapp.databinding.FragmentFlavorBinding
import com.alexaat.cupcakeapp.model.OrderViewModel


class FlavorFragment : Fragment() {

    private lateinit var binding: FragmentFlavorBinding
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flavor, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = orderViewModel
        }

        orderViewModel.navigateToPickupDate.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    val action = FlavorFragmentDirections.actionFlavorFragmentToDateFragment()
                    findNavController().navigate(action)
                }
            }
        }

        orderViewModel.navigateToStart.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    val action = FlavorFragmentDirections.actionFlavorFragmentToStartFragment()
                    findNavController().navigate(action)
                }
            }
        }

        orderViewModel.total.observe(this){
            binding.subtotalTextView.text = resources.getString(R.string.subtotal_format, formatCurrency(it))
        }

    }
}