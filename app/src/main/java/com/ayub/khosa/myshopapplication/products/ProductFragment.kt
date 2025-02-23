package com.ayub.khosa.myshopapplication.products

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.LinearLayoutCompat.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayub.khosa.myshopapplication.R
import com.ayub.khosa.myshopapplication.categorys.CategoryFragment
import com.ayub.khosa.myshopapplication.databinding.FragmentProductListBinding
import com.ayub.khosa.myshopapplication.model.PRODUCT
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class ProductFragment : Fragment() {
    companion object {
        fun newInstance() = ProductFragment()
    }

    private var _binding: FragmentProductListBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductListBinding.inflate(
            inflater, container, false
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration = DividerItemDecoration(this.context, VERTICAL)
            addItemDecoration(decoration)
        }

        val viewModel =
            ViewModelProvider(this).get<ProductsViewModel>(ProductsViewModel::class.java)

//        var product = PRODUCT(
//            "Guess 1875",
//            "https://ayubkhosa.com/ecommerce-website-master/images//watch1.jpg",
//            "Watch",
//            "watch here is",
//            "3000"
//        )
//        val data = kotlin.collections.ArrayList<PRODUCT>()
//        data.add(product)

   //     viewModel.setAdapterData(data)
        this.binding.lifecycleOwner = this
        this.binding.viewModel = viewModel
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}