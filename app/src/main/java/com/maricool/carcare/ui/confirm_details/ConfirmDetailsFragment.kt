package com.maricool.carcare.ui.confirm_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentChcekupBinding
import com.maricool.carcare.databinding.FragmentConfirmDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmDetailsFragment : Fragment(R.layout.fragment_confirm_details) {

    private val viewModel: ConfirmDetailsViewModel by viewModels()

    private var _binding: FragmentConfirmDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}