package com.ayub.khosa.myshopapplication.utils

import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


object DataBindingAdapters {


    @BindingAdapter("load_image")
    @JvmStatic
    fun loadImage(imageView: ImageView?, url: String) {

        if (imageView != null) {
            Glide.with(imageView.context)
                .load(url)
                .into(imageView)
        }

    }

    @BindingAdapter("app:onClickLogin")
    @JvmStatic
    fun setOnClick(view: View, clickListener: View.OnClickListener?) {
        view.setOnClickListener(clickListener)
        view.isClickable = true
    }


}
