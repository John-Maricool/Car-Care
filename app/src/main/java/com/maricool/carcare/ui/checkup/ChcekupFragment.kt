package com.maricool.carcare.ui.checkup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentChcekupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChcekupFragment : Fragment(R.layout.fragment_chcekup) {

    private val viewModel: ChcekupViewModel by viewModels()

    private var _binding: FragmentChcekupBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChcekupBinding.inflate(inflater, container, false)
        viewModel.getCar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_chcekupFragment_to_requestServiceFragment)
            }
        })
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        viewModel.car.observe(viewLifecycleOwner) {
            if (it != null)
                binding.carDetails = it
        }
        binding.shapeableImageView.setOnClickListener {
            findNavController().navigate(R.id.action_chcekupFragment_to_requestServiceFragment)
        }
        binding.arrowSign.setOnClickListener {
            findNavController().navigate(R.id.myCarFragment)
        }
        binding.confirm.setOnClickListener{
            findNavController().navigate(R.id.confirmDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}