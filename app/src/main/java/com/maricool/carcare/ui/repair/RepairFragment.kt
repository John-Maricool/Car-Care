package com.maricool.carcare.ui.repair

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentRepairBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepairFragment : Fragment(R.layout.fragment_repair) {

    private val viewModel: RepairViewModel by viewModels()

    private var _binding: FragmentRepairBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRepairBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}