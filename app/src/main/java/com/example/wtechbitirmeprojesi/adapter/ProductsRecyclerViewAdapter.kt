package com.example.wtechbitirmeprojesi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentProductsRecyclerviewRowBinding

class ProductsRecyclerViewAdapter(): RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsCardHolder>(){


    class ProductsCardHolder(val binding:FragmentProductsRecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentProductsRecyclerviewRowBinding>(layoutInflater,R.layout.fragment_products_recyclerview_row,parent,false)
        return ProductsCardHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsCardHolder, position: Int) {


    }

    override fun getItemCount(): Int {
        return 20;
    }
}