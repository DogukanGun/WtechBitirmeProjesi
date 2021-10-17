package com.example.wtechbitirmeprojesi.controller.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.wtechbitirmeprojesi.controller.fragment.ProductsFragmentDirections
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.product_detail_adepters.ProductDetailRecyclerViewAdapterForImages
import com.example.wtechbitirmeprojesi.databinding.FragmentProductDetailBinding
import com.example.wtechbitirmeprojesi.viewModel.ProductDetailViewModel
import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wtechbitirmeprojesi.adapter.product_detail_adepters.ProductDetailRecyclerViewAdapter
import androidx.recyclerview.widget.DividerItemDecoration





class ProductDetailFragment : Fragment() {

    val productDetailViewModel: ProductDetailViewModel by viewModels()

    lateinit var binding:FragmentProductDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_product_detail,container,false)
        val bundle = arguments
        binding.apply {
            productImages.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

            productInformation.layoutManager=LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            productInformation.addItemDecoration(
                DividerItemDecoration(
                    productInformation.getContext(),
                    DividerItemDecoration.VERTICAL
                )
            )

            if(bundle!=null){
                val args  =
                    ProductDetailFragmentArgs.fromBundle(
                        bundle
                    )
                if (args.id != null){
                    val id = Integer.parseInt(args.id!!)
                    productDetailViewModel.getProduct(id)

                    productDetailViewModel.product.observe(viewLifecycleOwner,{
                        if (it!=null && it.imagePath1.isNotEmpty()){
                            recyclerviewProductImagesAdapter=
                                ProductDetailRecyclerViewAdapterForImages(listOf(it.imagePath1,it.imagePath2,it.imagePath3))
                            val map :MutableMap<String,String> = mutableMapOf()
                            map.put(resources.getString(R.string.cpu),it.cpu)
                            map.put(resources.getString(R.string.hddCapacity),it.hddCapacity)
                            map.put(resources.getString(R.string.memoryType),it.memoryType)
                            map.put(resources.getString(R.string.name),it.name)
                            map.put(resources.getString(R.string.price),it.price)
                            map.put(resources.getString(R.string.ramCapacity),it.ramCapacity)
                            map.put(resources.getString(R.string.screenResolution),it.screenResolution)
                            map.put(resources.getString(R.string.screenSize),it.screenSize)
                            map.put(resources.getString(R.string.ssdCapacity),it.ssdCapacity)
                            recyclerviewProductInformation= ProductDetailRecyclerViewAdapter((map))
                        }
                    })
                }else{
                    Toast.makeText(requireContext(),resources.getText(R.string.not_found_message),Toast.LENGTH_LONG).show()

                }


            }

        }

        return binding.root
    }

    companion object {

    }
}