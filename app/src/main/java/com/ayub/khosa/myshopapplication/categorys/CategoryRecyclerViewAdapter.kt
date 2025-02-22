package com.ayub.khosa.myshopapplication.categorys


import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.ayub.khosa.myshopapplication.R
import com.ayub.khosa.myshopapplication.databinding.FragmentCategoryListItemBinding
import com.ayub.khosa.myshopapplication.model.CATEGORY
import com.bumptech.glide.Glide

class CategoryRecyclerViewAdapter :
    RecyclerView.Adapter<CategoryRecyclerViewAdapter.MyViewHolder>() {
    var categoryArrayList = ArrayList<CATEGORY>()

    fun setDataList(data: ArrayList<CATEGORY>) {
        this.categoryArrayList = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
//        val binding =
//            FragmentCategoryListItemBinding.inflate(
//                layoutInflater
//            )

        var binding: FragmentCategoryListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_category_list_item,
            parent,
            false
        )


        return MyViewHolder(binding)
    }

    override fun getItemCount() = categoryArrayList.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val datum: CATEGORY = categoryArrayList.get(position)
        var imageview = holder.binding.root.findViewById<ImageView>(R.id.my_item_imageview)
        // imageview.load(datum.img)

//        Glide.with(imageview.context)
//            .load(datum.img)
//            .placeholder(R.drawable.ic_launcher_background)
//            .into(imageview)

        holder.bind(categoryArrayList[position])
    }

    class MyViewHolder(val binding: FragmentCategoryListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: CATEGORY) {
            binding.model = data
            binding.executePendingBindings()
        }
    }


}