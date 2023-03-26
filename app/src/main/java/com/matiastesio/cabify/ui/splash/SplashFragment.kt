package com.matiastesio.cabify.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.matiastesio.cabify.MainActivity
import com.matiastesio.cabify.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private val activityScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.hide()
        val vista = requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)
        vista.visibility = View.GONE

        activityScope.launch {
            delay(1000)
            val action =
                SplashFragmentDirections.actionSplashFragmentToProductListFragment()
            findNavController().navigate(action)
            vista.visibility = View.VISIBLE
        }
    }

    override fun onPause() {
        activityScope.cancel()
        super.onPause()
    }
}