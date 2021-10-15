package com.example.wtechbitirmeprojesi.controller

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
 import android.graphics.Rect
import androidx.fragment.app.viewModels
 import androidx.recyclerview.widget.RecyclerView.ItemDecoration
 import androidx.room.Room
 import com.example.wtechbitirmeprojesi.model.Product
 import com.example.wtechbitirmeprojesi.resources.Constants
 import com.example.wtechbitirmeprojesi.room.Database
 import com.example.wtechbitirmeprojesi.room.ProductsDAO
 import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel
 import kotlinx.coroutines.CoroutineScope
 import kotlinx.coroutines.Dispatchers
 import kotlinx.coroutines.launch
 import kotlinx.coroutines.withContext
 import okhttp3.Dispatcher


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
        var isDatabaseEmpty=false
        CoroutineScope(Dispatchers.IO).launch {
            productsDAO=Constants.db.productDao()
            if (productsDAO.getProductById(0)==null){

                isDatabaseEmpty=true
            }
        }
        if (!isDatabaseEmpty){
            productViewModel.request()
        }



        binding.apply {
            productsList.layoutManager=GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
            productsList.addItemDecoration(
                 ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.card_view_spacing),
                    resources.getInteger(R.integer.card_preview_column))

            )
            recyclerviewAdapter=ProductsRecyclerViewAdapter(emptyList())

            CoroutineScope(Dispatchers.IO).launch {
                val products = productsDAO.getProducts()

                withContext(Dispatchers.Main){
                    if (!isDatabaseEmpty){
                        recyclerviewAdapter=ProductsRecyclerViewAdapter(products)
                    }else{
                        productViewModel.products.observe(viewLifecycleOwner,{ list->
                            if (list.isNotEmpty()){
                                saveToDatabase(list)
                                recyclerviewAdapter=ProductsRecyclerViewAdapter(list)
                            }
                        })
                    }
                }

            }


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

    inner class ItemDecorationAlbumColumns(private val mSizeGridSpacingPx: Int, private val mGridSize: Int) :
        ItemDecoration() {
        private var mNeedLeftSpacing = false
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            val frameWidth =
                ((parent.width - mSizeGridSpacingPx.toFloat() * (mGridSize - 1)) / mGridSize).toInt()
            val padding = parent.width / mGridSize - frameWidth
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
            if (itemPosition < mGridSize) {
                outRect.top = padding
            } else {
                outRect.top = mSizeGridSpacingPx
            }
            if (itemPosition % mGridSize == 0) {
                outRect.left = padding
                outRect.right = padding
                mNeedLeftSpacing = true
            } else if ((itemPosition + 1) % mGridSize == 0) {
                mNeedLeftSpacing = false
                outRect.right = 0
                outRect.left = padding
            } else if (mNeedLeftSpacing) {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx - padding
                if ((itemPosition + 2) % mGridSize == 0) {
                    outRect.right = mSizeGridSpacingPx - padding
                } else {
                    outRect.right = mSizeGridSpacingPx / 2
                }
            } else if ((itemPosition + 2) % mGridSize == 0) {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx - padding
            } else {
                mNeedLeftSpacing = false
                outRect.left = mSizeGridSpacingPx / 2
                outRect.right = mSizeGridSpacingPx / 2
            }
            outRect.bottom = 0
        }
    }
}

