package com.example.wtechbitirmeprojesi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentProductsRecyclerviewRowBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.squareup.picasso.Picasso

class ProductsRecyclerViewAdapter(private val products:List<Product>): RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsCardHolder>(){

    class ProductsCardHolder(val binding:FragmentProductsRecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentProductsRecyclerviewRowBinding>(layoutInflater,R.layout.fragment_products_recyclerview_row,parent,false)
        return ProductsCardHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsCardHolder, position: Int) {
        if (products.isNotEmpty()){
            holder.binding.apply {
                val imagePath= products[position].imagePath1
                Picasso.get().load(imagePath).into(imageView)
                textView.text=products[position].name
            }
        }

    }

    override fun getItemCount(): Int {
        return products.size;
    }
}