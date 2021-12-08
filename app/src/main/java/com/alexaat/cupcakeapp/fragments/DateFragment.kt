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
import com.alexaat.cupcakeapp.Util.getDates
import com.alexaat.cupcakeapp.databinding.FragmentDateBinding
import com.alexaat.cupcakeapp.model.OrderViewModel
import java.text.NumberFormat

class DateFragment : Fragment() {

    private lateinit var binding: FragmentDateBinding
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_date, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dates = getDates()
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = orderViewModel
            if(dates.size>3){
                dateOneRadioButton.text = dates[0]
                dateTwoRadioButton.text = dates[1]
                dateThreeRadioButton.text = dates[2]
                dateFourRadioButton.text = dates[3]
            }
        }

        orderViewModel.total.observe(this){
            binding.subtotalTextView.text = resources.getString(R.string.subtotal_format, formatCurrency(it))
        }
        orderViewModel.navigateToSummary.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    val action = DateFragmentDirections.actionDateFragmentToSummaryFragment()
                    findNavController().navigate(action)
                }
            }
        }
        orderViewModel.navigateToStart.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    val action = DateFragmentDirections.actionDateFragmentToStartFragment()
                    findNavController().navigate(action)
                }
            }

        }





    }
}