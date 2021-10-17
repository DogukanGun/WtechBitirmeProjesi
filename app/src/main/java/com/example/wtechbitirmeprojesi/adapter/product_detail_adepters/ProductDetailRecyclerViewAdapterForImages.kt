package com.example.wtechbitirmeprojesi.adapter.product_detail_adepters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wtechbitirmeprojesi.R
import com.example.wtechbitirmeprojesi.databinding.FragmentProductDetailImagesRowBinding
import com.squareup.picasso.Picasso

class ProductDetailRecyclerViewAdapterForImages(private val imagesPath:List<String>):RecyclerView.Adapter<ProductDetailRecyclerViewAdapterForImages.ImagesHolder>()  {
    class ImagesHolder(val binding: FragmentProductDetailImagesRowBinding): RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<FragmentProductDetailImagesRowBinding>(layoutInflater, R.layout.fragment_product_detail_images_row,parent,false)
        return ImagesHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesHolder, position: Int) {
        holder.binding.apply {
            if (!imagesPath[position].isNullOrBlank()){
                Picasso.get().load(imagesPath[position]).into(imageView)

            }
        }
    }

    override fun getItemCount(): Int {
        return imagesPath.size
    }
}