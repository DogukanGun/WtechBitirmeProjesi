package com.example.wtechbitirmeprojesi.controller.fragment

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wtechbitirmeprojesi.controller.fragment.CardFragmentDirections
 import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
 import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.CardRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentCardBinding
import com.example.wtechbitirmeprojesi.model.Product
import com.example.wtechbitirmeprojesi.resources.Constants
 import com.example.wtechbitirmeprojesi.viewModel.CardViewModel


class CardFragment : Fragment() {

    lateinit var binding: FragmentCardBinding

    var recyclerViewList:List<Product> = emptyList()
    private val cardViewModel:CardViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_card,container,false)

        cardViewModel.getProduct()
        binding(inflater)
        return binding.root
    }

    private fun binding(inflater: LayoutInflater) {
        binding.apply {
            cardList.layoutManager = LinearLayoutManager(requireContext())
            recyclerviewAdapter = CardRecyclerViewAdapter(resources,requireContext(),mutableListOf<Product>(), cardViewModel)

            delete.setOnClickListener {
                deleteAllProductsInList()
                recyclerviewAdapter =
                    CardRecyclerViewAdapter(resources,requireContext(),mutableListOf<Product>(), cardViewModel)
                createAlertDialog(inflater)
            }
            pay.setOnClickListener {

            }
            cardViewModel.loading.observe(viewLifecycleOwner, { result ->
                if (!result && cardViewModel.card.value?.isEmpty() != false) {
                    createAlertDialog(inflater)
                }

            })
            cardViewModel.card.observe(viewLifecycleOwner, { cardList ->
                if (cardList != null && cardList.isNotEmpty()) {
                    recyclerViewList = cardList
                    recyclerviewAdapter =
                        CardRecyclerViewAdapter(resources,requireContext(),cardList.toMutableList(), cardViewModel)
                }
            })

        }
    }

    private fun deleteAllProductsInList() {
        for (index in recyclerViewList) {
            cardViewModel.updateProductCardStatus(index.id, 0)
        }
    }

    private fun createAlertDialog(inflater: LayoutInflater) {
        var design = inflater.inflate(R.layout.empty_action, null)
        var ad = AlertDialog.Builder(requireContext())
        ad.setTitle(resources.getString(R.string.sorry))
        ad.setView(design)
        ad.setPositiveButton(
            resources.getString(R.string.start_to_shopping)
        ) { dialogInterface, i ->
            val action =
                CardFragmentDirections.actionCardFragmentToProductsFragment()
            Constants.navHostFragment.navController.navigate(action)

        }
        ad.create().show()
    }

    companion object {

    }
}