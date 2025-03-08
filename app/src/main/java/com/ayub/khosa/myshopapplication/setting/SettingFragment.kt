package com.ayub.khosa.myshopapplication.setting

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ayub.khosa.myshopapplication.databinding.FragmentSettingBinding
import com.ayub.khosa.myshopapplication.repository.MainActivityRepository
import com.ayub.khosa.myshopapplication.repository.MyViewModelFactory
import com.ayub.khosa.myshopapplication.utils.PrintLogs

class SettingFragment : Fragment() {

    companion object {
        fun newInstance() = SettingFragment()
    }

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: SettingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val repository: MainActivityRepository by lazy {
            MainActivityRepository(this.context as Context)
        }
        _binding = FragmentSettingBinding.inflate(
            inflater, container, false
        )
        viewModel = ViewModelProvider(
            this,
            MyViewModelFactory(repository)
        ).get(
            SettingViewModel::class.java
        )
        viewModel.user.observe(viewLifecycleOwner, Observer {

            PrintLogs.printD("onCreateView SettingFragment user_id : " + it.user_id)


        })
        viewModel._is_busy.observe(viewLifecycleOwner, Observer {

            if (it) {
                binding.linearLayoutBusybox.visibility = View.VISIBLE
            } else {
                binding.linearLayoutBusybox.visibility = View.GONE
            }

        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(this.context, "Error " + it, Toast.LENGTH_SHORT).show()

        })

        viewModel.onsetting_user_loginClicked()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}