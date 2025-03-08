package com.ayub.khosa.myshopapplication.categorys

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
import com.ayub.khosa.myshopapplication.databinding.FragmentCategoryListBinding
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.repository.MyViewModelFactory
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class CategoryFragment : Fragment() {
    companion object {
        fun newInstance() = CategoryFragment()
    }
    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: CategorysViewModel
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val repository: MainActivityRepository by lazy {
            MainActivityRepository(this.context as Context)
        }
        _binding = FragmentCategoryListBinding.inflate(
            inflater, container, false
        )

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this.context)
            val decoration = DividerItemDecoration(this.context, VERTICAL)
            addItemDecoration(decoration)
        }


        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(repository)
        ).get(CategorysViewModel::class.java)




        viewModel.categorysItems.observe(viewLifecycleOwner, Observer {


            try {
                Toast.makeText(
                    this.context,
                    " onCreateView categorysItems size " + it.categorys.size,
                    Toast.LENGTH_SHORT
                ).show()
                PrintLogs.printD("onCreateView CategoryFragment size:  " + it.categorys.size)
                val adapter = CategoryRecyclerViewAdapter(it)

                binding.recyclerView.adapter = adapter


            } catch (e: Exception) {
                PrintLogs.printD("onCreateView  " + e.message)
            }

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
        viewModel.getAllCategoryDB()

        this.binding.lifecycleOwner = this
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}