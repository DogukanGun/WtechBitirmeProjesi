package com.example.wtechbitirmeprojesi.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.room.Room
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentDiscountedProductsBinding
import com.example.wtechbitirmeprojesi.resources.Constants
import com.example.wtechbitirmeprojesi.room.Database

class DiscountedProductsFragment : Fragment() {


    lateinit var binding: FragmentDiscountedProductsBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_discounted_products,container,false)
        if (Constants.db==null){
            val db = Room.databaseBuilder(
                requireContext(),
                Database::class.java, "product"
            ).build()
            Constants.db=db
        }
        return binding.root
    }

    companion object {

    }
}