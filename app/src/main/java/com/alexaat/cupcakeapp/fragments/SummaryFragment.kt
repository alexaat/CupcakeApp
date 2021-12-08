package com.alexaat.cupcakeapp.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.alexaat.cupcakeapp.R
import com.alexaat.cupcakeapp.Util.formatCurrency
import com.alexaat.cupcakeapp.databinding.FragmentSummaryBinding
import com.alexaat.cupcakeapp.model.OrderViewModel


class SummaryFragment : Fragment() {

    private lateinit var binding: FragmentSummaryBinding
    private val orderViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = orderViewModel
        }

        orderViewModel.navigateToAnotherApp.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    shareOrder()
                }
            }
        }
        orderViewModel.navigateToStart.observe(this){event->
            event.getContentIfNotHandled()?.let{
                if(it){
                    val action = SummaryFragmentDirections.actionSummaryFragmentToStartFragment()
                    findNavController().navigate(action)
                }
            }
        }
        orderViewModel.total.observe(this){
            binding.totalTextView.text = resources.getString(R.string.total_format, formatCurrency(it))
        }
    }

    private fun shareOrder(){
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcakes_order))
            putExtra(Intent.EXTRA_TEXT, resources.getString(R.string.order_summary_format,
                resources.getString(R.string.quantity_format, orderViewModel.quantity,resources.getQuantityString(R.plurals.quantity_format,orderViewModel.quantity)),
                orderViewModel.flavor.toString(),
                orderViewModel.pickupDate,
                formatCurrency(orderViewModel.total.value!!)
            ))

        }
        val packageManager = activity!!.packageManager
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(context,getString(R.string.no_intent_available_to_handle_action),Toast.LENGTH_SHORT).show()
        }

    }

}