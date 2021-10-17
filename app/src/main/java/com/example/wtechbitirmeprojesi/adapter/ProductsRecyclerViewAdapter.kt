package com.example.wtechbitirmeprojesi.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.controller.fragment.ProductsFragmentDirections
import com.example.wtechbitirmeprojesi.databinding.FragmentProductsRecyclerviewRowBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants
import com.example.wtechbitirmeprojesi.resources.Constants.toggleStatus
import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel
import com.squareup.picasso.Picasso

class ProductsRecyclerViewAdapter(private val resource: Resources, private val context:Context, private val products:List<Product>, private val viewModel:ProductsViewModel): RecyclerView.Adapter<ProductsRecyclerViewAdapter.ProductsCardHolder>(){

    class ProductsCardHolder(val binding:FragmentProductsRecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsCardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentProductsRecyclerviewRowBinding>(layoutInflater,R.layout.fragment_products_recyclerview_row,parent,false)
        return ProductsCardHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductsCardHolder, position: Int) {
        holder.binding.apply {
            if (products.isNotEmpty()){

                val imagePath= products[position].imagePath1
                Picasso.get().load(imagePath).into(image)
                textView.text=products[position].name
                card.setOnClickListener{
                    val action = ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(
                        products[position].id.toString())
                    Constants.navHostFragment.navController.navigate(action)
                }
                cardButton.setOnClickListener {
                    products[position].cardStatus=toggleStatus(products[position].cardStatus)
                    viewModel.updateProductCardStatus(products[position].id,products[position].cardStatus)
                    Toast.makeText(context,resource.getText(R.string.adding_message),Toast.LENGTH_LONG).show()
                }



            }else{
                textView.text
            }
        }


    }



    override fun getItemCount(): Int {
        return products.size;
    }
}