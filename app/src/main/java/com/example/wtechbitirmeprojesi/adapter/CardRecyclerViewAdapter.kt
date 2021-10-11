package com.example.wtechbitirmeprojesi.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentCardRecyclerviewRowBinding
import com.example.wtechbitirmeprojesi.databinding.FragmentDiscountedProductsBinding
import com.example.wtechbitirmeprojesi.model.Product

class CardRecyclerViewAdapter(private val card:List<Product>):RecyclerView.Adapter<CardRecyclerViewAdapter.CardHolder>() {

    class CardHolder(val binding: FragmentCardRecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentCardRecyclerviewRowBinding>(layoutInflater, R.layout.fragment_card_recyclerview_row,parent,false)
        return CardHolder(binding)
    }

    override fun onBindViewHolder(holder: CardHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return card.size
    }


}