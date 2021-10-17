package com.example.wtechbitirmeprojesi.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentCardRecyclerviewRowBinding
import com.example.wtechbitirmeprojesi.databinding.FragmentDiscountedProductsBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants.toggleStatus
import com.example.wtechbitirmeprojesi.viewModel.CardViewModel
import com.squareup.picasso.Picasso

class CardRecyclerViewAdapter(private val card:MutableList<Product>,private val cardViewModel: CardViewModel):RecyclerView.Adapter<CardRecyclerViewAdapter.CardHolder>() {

    class CardHolder(val binding: FragmentCardRecyclerviewRowBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardHolder {
        val layoutInflater=LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentCardRecyclerviewRowBinding>(layoutInflater, R.layout.fragment_card_recyclerview_row,parent,false)
        return CardHolder(binding)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CardHolder, position: Int) {
        holder.binding.apply {
            val imagePath= card[position].imagePath1
            Picasso.get().load(imagePath).into(imageView2)
            textView3.text=card[position].name.subSequence(0,15)
            textView5.text=card[position].price
            remove.setOnClickListener {
                cardViewModel.updateProductCardStatus(card[position].id,0);
                card.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemCount(): Int {
        return card.size
    }


}