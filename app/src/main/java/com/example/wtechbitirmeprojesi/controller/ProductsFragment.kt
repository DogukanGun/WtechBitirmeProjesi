package com.example.wtechbitirmeprojesi.controller

import android.R.attr
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.adapter.ProductsRecyclerViewAdapter
import com.example.wtechbitirmeprojesi.databinding.FragmentProductsBinding
import android.R.attr.spacing
import android.graphics.Rect
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import com.example.wtechbitirmeprojesi.viewModel.ProductsViewModel


class ProductsFragment : Fragment() {
    lateinit var binding:FragmentProductsBinding
    private val productViewModel:ProductsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_products,container,false)

        //room kayiti yap
        productViewModel.request()
        binding.apply {
            productsList.layoutManager=GridLayoutManager(requireContext(),2,RecyclerView.VERTICAL,false)
            productsList.addItemDecoration(
                 ItemDecorationAlbumColumns(
                    resources.getDimensionPixelSize(R.dimen.card_view_spacing),
                    resources.getInteger(R.integer.card_preview_column))

            )
            recyclerviewAdapter=ProductsRecyclerViewAdapter(emptyList())

            productViewModel.products.observe(viewLifecycleOwner,{ list->
                if (list.isNotEmpty()){
                    recyclerviewAdapter=ProductsRecyclerViewAdapter(list)
                }
            })





        }



        return binding.root
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

