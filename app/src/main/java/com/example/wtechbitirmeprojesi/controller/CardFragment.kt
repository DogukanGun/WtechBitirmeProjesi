package com.example.wtechbitirmeprojesi.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.CardRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentCardBinding
import com.example.wtechbitirmeprojesi.viewModel.CardViewModel


class CardFragment : Fragment() {

    lateinit var binding: FragmentCardBinding

    val cardViewModel:CardViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_card,container,false)
        binding.apply {
            cardList.layoutManager=LinearLayoutManager(requireContext())
            recyclerviewAdapter=CardRecyclerViewAdapter(emptyList())

            delete.setOnClickListener {
                recyclerviewAdapter=CardRecyclerViewAdapter(emptyList())
            }
            pay.setOnClickListener{

            }
            cardViewModel.card.observe(viewLifecycleOwner,{ card->
                if (card.isNotEmpty()){
                    recyclerviewAdapter=CardRecyclerViewAdapter(card)
                }
            })

        }
        return binding.root
    }

    companion object {

    }
}