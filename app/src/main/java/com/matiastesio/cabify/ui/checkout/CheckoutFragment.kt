package com.matiastesio.cabify.ui.checkout

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.matiastesio.cabify.R
import com.matiastesio.cabify.databinding.CheckoutFragmentBinding
import com.matiastesio.cabify.utils.startViewWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CheckoutFragment : DialogFragment() {

    private val checkoutViewModel: CheckoutViewModel by viewModels()
    private lateinit var binding: CheckoutFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CheckoutFragmentBinding.inflate(layoutInflater)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.progressLoader.startViewWithAnimation(R.raw.progress)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObservers()
        loadData()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setObservers() {
        checkoutViewModel.success.observe(viewLifecycleOwner) {
            binding.progressLoader.apply {
                pauseAnimation()
                startViewWithAnimation(R.raw.success)
            }
        }

        checkoutViewModel.exit.observe(viewLifecycleOwner) {
            binding.progressLoader.apply {
                pauseAnimation()
                val action =
                    CheckoutFragmentDirections.actionCheckoutFragmentToCatalogFragment()
                findNavController().navigate(action, null)
            }
        }
    }

    private fun loadData() {
        checkoutViewModel.processCheckout()
    }
}
