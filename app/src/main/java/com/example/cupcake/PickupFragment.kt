
package com.example.cupcake

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.cupcake.databinding.FragmentPickupBinding


/**
 * [PickupFragment] allows the user to choose a pickup date for the cupcake order.
 */
class PickupFragment : Fragment() {

    // Binding object instance corresponding to the fragment_pickup.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private var _binding: FragmentPickupBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel: OrderViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPickupBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.subtotal.text = sharedViewModel.price.value.toString()
        binding.dateOptions.setOnCheckedChangeListener { group, id ->
            when (binding.dateOptions.checkedRadioButtonId) {
                R.id.option0 -> sharedViewModel.setDate(sharedViewModel.dateOptions[0])
                R.id.option1 -> sharedViewModel.setDate(sharedViewModel.dateOptions[1])
                R.id.option2 -> sharedViewModel.setDate(sharedViewModel.dateOptions[2])
                R.id.option3 -> sharedViewModel.setDate(sharedViewModel.dateOptions[3])

            }
        }




        binding.apply {
            nextButton.setOnClickListener { goToNextScreen() }
        }
        binding.cancelButton.setOnClickListener{
            cancelOrder()
        }
    }

    fun goToNextScreen() {
        findNavController().navigate(R.id.action_pickupFragment_to_summaryFragment)
    }
    fun cancelOrder() {
        sharedViewModel.resetOrder()
        findNavController().navigate(R.id.action_pickupFragment_to_startFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}