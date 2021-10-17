package com.example.wtechbitirmeprojesi.adapter.product_detail_adepters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
 import com.example.wtechbitirmeprojesi.databinding.FragmentProductsDetailInformationRowBinding
import java.util.*
import java.util.stream.Collector

class ProductDetailRecyclerViewAdapter (val informationList:Map<String,String>):RecyclerView.Adapter<ProductDetailRecyclerViewAdapter.RowHolder>(){
    class RowHolder(val binding: FragmentProductsDetailInformationRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentProductsDetailInformationRowBinding>(layoutInflater, R.layout.fragment_products_detail_information_row,parent,false)
        return RowHolder(binding)
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.binding.apply {
            if (informationList.isNotEmpty()){
                key.text=informationList.keys.toList()[position]
                value.text=informationList.values.toTypedArray()[position]

            }
        }
    }

    override fun getItemCount(): Int {
        return informationList.entries.size
    }
}