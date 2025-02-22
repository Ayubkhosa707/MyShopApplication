package com.ayub.khosa.myshopapplication.products


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayub.khosa.myshopapplication.R
import com.ayub.khosa.myshopapplication.databinding.FragmentProductListItemBinding
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.bumptech.glide.Glide

class ProductRecyclerViewAdapter :
    RecyclerView.Adapter<ProductRecyclerViewAdapter.MyViewHolder>() {
    var productArrayList = ArrayList<PRODUCT>()

    fun setDataList(data: ArrayList<PRODUCT>) {
        this.productArrayList = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        //   val binding = com.ayub.khosa.myshopapplication.databinding.FragmentProductListItemBinding.inflate(layoutInflater)

        var binding: FragmentProductListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_product_list_item,
            parent,
            false
        )


        return MyViewHolder(binding)
    }

    override fun getItemCount() = productArrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val datum: PRODUCT = productArrayList.get(position)
        var imageview = holder.binding.root.findViewById<ImageView>(R.id.my_item_imageview)
        // imageview.load(datum.img)

//        Glide.with(imageview.context)
//            .load(datum.img)
//            .placeholder(R.drawable.ic_launcher_background)
//            .into(imageview)

        holder.bind(productArrayList[position])
    }

    class MyViewHolder(val binding: FragmentProductListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: PRODUCT) {
            binding.model = data
            binding.executePendingBindings()
        }
    }


}