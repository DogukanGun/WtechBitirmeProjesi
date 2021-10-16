package com.example.wtechbitirmeprojesi.controller

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.CardRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentCardBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants
import com.example.wtechbitirmeprojesi.room.Database
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
        if (Constants.db==null){
            val db = Room.databaseBuilder(
                requireContext(),
                Database::class.java, "product"
            ).build()
            Constants.db=db
        }
        cardViewModel.getProduct()
        binding.apply {
            cardList.layoutManager=LinearLayoutManager(requireContext())
            recyclerviewAdapter=CardRecyclerViewAdapter(mutableListOf<Product>(),cardViewModel)

            delete.setOnClickListener {
                recyclerviewAdapter=CardRecyclerViewAdapter(mutableListOf<Product>(),cardViewModel)
            }
            pay.setOnClickListener{

            }
            cardViewModel.loading.observe(viewLifecycleOwner,{result->
                if (!result && cardViewModel.card.value?.isEmpty() != false){
                    var design=inflater.inflate(R.layout.empty_action,null)
                    var ad = AlertDialog.Builder(requireContext())
                    ad.setTitle(resources.getString(R.string.sorry))
                    ad.setView(design)
                    ad.setPositiveButton(resources.getString(R.string.start_to_shopping),DialogInterface.OnClickListener{dialogInterface, i ->
                        val action = CardFragmentDirections.actionCardFragmentToProductsFragment()
                        Constants.navHostFragment.navController.navigate(action)

                    })
                    ad.create().show()

                }

            })
            cardViewModel.card.observe(viewLifecycleOwner,{ card->
                if (card.isNotEmpty()){
                    recyclerviewAdapter=CardRecyclerViewAdapter(card.toMutableList(),cardViewModel)
                }
            })

        }
        return binding.root
    }

    companion object {

    }
}