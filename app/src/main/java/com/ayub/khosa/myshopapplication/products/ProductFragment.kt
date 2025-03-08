package com.ayub.khosa.myshopapplication.products

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.LinearLayoutCompat.VERTICAL
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ayub.khosa.myshopapplication.databinding.FragmentProductListBinding
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.repository.MyViewModelFactory
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
        val repository: MainActivityRepository by lazy {
            MainActivityRepository(this.context as Context)
        }

        _binding = FragmentProductListBinding.inflate(
            inflater, container, false
        )
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration = DividerItemDecoration(this.context, VERTICAL)
            addItemDecoration(decoration)
        }


        val viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(repository)
        ).get(ProductsViewModel::class.java)



        viewModel.productsItems.observe(viewLifecycleOwner, Observer {

            Toast.makeText(
                this.context,
                "productsItems size " + it.products.size,
                Toast.LENGTH_SHORT
            ).show()

            PrintLogs.printD("onCreateView ProductFragment size : " + it.products.size)
            val adapter: ProductRecyclerViewAdapter = ProductRecyclerViewAdapter(it)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

        })
        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, "Error " + it, Toast.LENGTH_SHORT).show()

        })
        viewModel._is_busy.observe(viewLifecycleOwner, Observer {

            if (it) {
                binding.linearLayoutBusybox.visibility = View.VISIBLE
            } else {
                binding.linearLayoutBusybox.visibility = View.GONE
            }

        })
        viewModel.getAllProductsDB()

        this.binding.lifecycleOwner = this
        this.binding.viewModel = viewModel
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}