package com.ayub.khosa.myshopapplication.categorys

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayub.khosa.myshopapplication.databinding.FragmentCategoryListBinding

class CategoryFragment : Fragment() {
    companion object {
        fun newInstance() = CategoryFragment()
    }

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCategoryListBinding.inflate(
            inflater, container, false
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration = DividerItemDecoration(this.context, VERTICAL)
            addItemDecoration(decoration)
        }

        val viewModel =
            ViewModelProvider(this).get<CategorysViewModel>(CategorysViewModel::class.java)


//        var category =
//            CATEGORY("Shoes", "https://ayubkhosa.com/ecommerce-website-master/images/shoes.jpg")
//        val data = kotlin.collections.ArrayList<CATEGORY>()
//        data.add(category)
//
//        viewModel.setAdapterData(data)

        this.binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}