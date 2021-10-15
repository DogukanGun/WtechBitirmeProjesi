package com.example.wtechbitirmeprojesi.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBinderMapper
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentProductDetailBinding
import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel

class ProductDetailFragment : Fragment() {

    val productViewModel: ProductsViewModel by viewModels()

    lateinit var binding:FragmentProductDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false)
        val bundle = arguments
        if(bundle!=null){
            val args  = ProductDetailFragmentArgs.fromBundle(bundle)
            val id = args.id
        }
        return binding.root
    }

    companion object {

    }
}