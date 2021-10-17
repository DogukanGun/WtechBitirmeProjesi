package com.example.wtechbitirmeprojesi.controller.fragment

 import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
 import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.ProductsRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentProductsBinding
 import androidx.fragment.app.viewModels
 import androidx.room.Room
 import com.example.wtechbitirmeprojesi.model.Product
 import com.example.wtechbitirmeprojesi.resources.Constants
 import com.example.wtechbitirmeprojesi.resources.ItemDecorationAlbumColumns
 import com.example.wtechbitirmeprojesi.room.Database
 import com.example.wtechbitirmeprojesi.room.ProductsDAO
 import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel
 import kotlinx.coroutines.CoroutineScope
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.launch


class ProductsFragment : Fragment() {
    lateinit var binding:FragmentProductsBinding
    private val productViewModel:ProductsViewModel by viewModels()
    lateinit var productsDAO: ProductsDAO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_products,container,false)

        val db = Room.databaseBuilder(
            requireContext(),
            Database::class.java, "product"
        ).build()
        Constants.db=db
        productsDAO=db.productDao()
        productViewModel.getProducts()
        var products: List<Product> = emptyList()
        CoroutineScope(Dispatchers.IO).launch {
            products = productsDAO.getProducts()
        }



        binding.apply {
            productsList.layoutManager=GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
            productsList.addItemDecoration(
                 ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.card_view_spacing),
                    resources.getInteger(R.integer.card_preview_column))

            )
            recyclerviewAdapter=ProductsRecyclerViewAdapter(resources,requireContext(),emptyList(),productViewModel)

            Constants.noInternetConnection.observe(viewLifecycleOwner,{
                if (it){
                    recyclerviewAdapter=ProductsRecyclerViewAdapter(resources,requireContext(),products,productViewModel)

                }

            })
            productViewModel.products.observe(viewLifecycleOwner,{ list->
                if (list.isNotEmpty()){
                    if (products.isEmpty()){
                        saveToDatabase(list)
                    }
                    recyclerviewAdapter=ProductsRecyclerViewAdapter(resources,requireContext(),list,productViewModel)
                }
            })



        }
        return binding.root
    }

    fun saveToDatabase(products:List<Product>){
        CoroutineScope(Dispatchers.IO).launch {
            for (index in products){
                productsDAO.addProduct(index)
            }
        }

    }
    companion object {

    }


}

