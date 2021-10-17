package com.example.wtechbitirmeprojesi.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.DiscountedProductRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentDiscountedProductsBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants
import com.example.wtechbitirmeprojesi.resources.ItemDecorationAlbumColumns
import com.example.wtechbitirmeprojesi.room.Database
import com.example.wtechbitirmeprojesi.viewModel.DiscountedProductViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

class DiscountedProductsFragment : Fragment() {


    lateinit var binding: FragmentDiscountedProductsBinding
    val discountedProductsViewModel:DiscountedProductViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discounted_products,container,false)
        discountedProductsViewModel.getProducts()
        binding.apply {
             discountedProductsList.layoutManager=
                GridLayoutManager(requireContext(),2, RecyclerView.VERTICAL,false)
            discountedProductsList.addItemDecoration(
                ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.card_view_spacing),
                    resources.getInteger(R.integer.card_preview_column))

            )
            recyclerviewAdapter= DiscountedProductRecyclerViewAdapter(emptyList(),discountedProductsViewModel)
             discountedProductsViewModel.products.observe(viewLifecycleOwner,{
                products->
                if (products.isNotEmpty()){
                    recyclerviewAdapter= DiscountedProductRecyclerViewAdapter(products,discountedProductsViewModel)
                }
            })

        }
        return binding.root
    }

    companion object {

    }
}