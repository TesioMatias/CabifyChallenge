package com.matiastesio.cabify.ui.catalog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import androidx.recyclerview.widget.LinearLayoutManager
import com.matiastesio.cabify.MainActivity
import com.matiastesio.cabify.R
import com.matiastesio.cabify.data.model.CatalogItemModel
import com.matiastesio.cabify.databinding.CatalogFragmentBinding
import com.matiastesio.cabify.ui.catalog.list.CatalogAdapter
import com.matiastesio.cabify.ui.catalog.list.OnClickHandler
import com.matiastesio.cabify.utils.startViewWithAnimation
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CatalogFragment : Fragment(), OnClickHandler {

    private lateinit var binding: CatalogFragmentBinding
    private val productViewModel: CatalogViewModel by viewModels()
    private lateinit var productAdapter: CatalogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CatalogFragmentBinding.inflate(layoutInflater)
        (activity as MainActivity).supportActionBar?.show()
        setObservers()
        binding.progressLoader.startViewWithAnimation(R.raw.progress)
        setList()
        loadData()
        return binding.root
    }

    private fun loadData() {
        productViewModel.getProducts()
    }

    private fun setList() {
        productAdapter = CatalogAdapter(onClickHandler = this)
        binding.productsRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            adapter = productAdapter
        }
    }

    private fun setObservers() {
        productViewModel.catalogItems.observe(viewLifecycleOwner) {
            setCatalogItems(it)
        }

        productViewModel.isLoading.observe(viewLifecycleOwner) {
            if (it) {
                binding.productsRecyclerView.visibility = GONE
                binding.progressLoader.apply {
                    visibility = VISIBLE
                    playAnimation()
                }
            } else {
                binding.progressLoader.apply {
                    pauseAnimation()
                    visibility = GONE
                }
                binding.productsRecyclerView.visibility = VISIBLE
            }
        }
    }

    private fun setCatalogItems(products: List<CatalogItemModel>) {
        if (products.isEmpty()) {
            binding.progressLoader.startViewWithAnimation(R.raw.empylist)
            binding.productsRecyclerView.visibility = GONE
            binding.emptyStateTitle.visibility = VISIBLE
        } else {
            binding.productsRecyclerView.visibility = VISIBLE
            binding.emptyStateTitle.visibility = GONE
            productAdapter.setItems(products.toMutableList())
        }
    }

    override fun click(code: String) {
        val action =
            CatalogFragmentDirections.actionCatalogFragmentToDetailsFragment(
                code = code
            )

        findNavController().navigate(action, null)
    }
}