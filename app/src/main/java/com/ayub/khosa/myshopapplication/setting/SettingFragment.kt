package com.ayub.khosa.myshopapplication.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ayub.khosa.myshopapplication.databinding.FragmentSettingBinding

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
        _binding = FragmentSettingBinding.inflate(
            inflater, container, false
        )
        //  return inflater.inflate(R.layout.fragment_setting, container, false)
        viewModel =
            ViewModelProvider(this).get<SettingViewModel>(SettingViewModel::class.java)

        viewModel.user.value?.first_name = "  AYUB"
        viewModel.user.value?.last_name = "  KHOSA"





        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}