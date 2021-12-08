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
import com.alexaat.cupcakeapp.databinding.FragmentStartBinding
import com.alexaat.cupcakeapp.model.OrderViewModel

class StartFragment : Fragment() {

    private lateinit var binding: FragmentStartBinding
    private val orderViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_start, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = orderViewModel
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        orderViewModel.apply {
            navigateToFlavor.observe(viewLifecycleOwner){event->
               event.getContentIfNotHandled()?.let{
                   if(it){
                        val action = StartFragmentDirections.actionStartFragmentToFlavorFragment()
                        findNavController().navigate(action)
                   }
               }
            }
        }

    }
}