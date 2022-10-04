package com.maricool.carcare.ui.car_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentLoginBinding
import com.maricool.carcare.databinding.FragmentMyCarBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MyCarFragment : Fragment(R.layout.fragment_my_car) {

    private val viewModel: MyCarViewModel by viewModels()

    private var _binding: FragmentMyCarBinding? = null

    @Inject
    lateinit var adapter: MyCarRecyclerAdapter
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMyCarBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.adapter = adapter
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })

        binding.shapeableImageView.setOnClickListener{
            findNavController().navigateUp()
        }
    }
}

