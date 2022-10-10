package com.maricool.carcare.ui.confirm_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentChcekupBinding
import com.maricool.carcare.databinding.FragmentConfirmDetailsBinding
import com.maricool.carcare.ui.checkup.ChcekupViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConfirmDetailsFragment : Fragment(R.layout.fragment_confirm_details) {

    private val viewModel: ChcekupViewModel by activityViewModels()

    private var _binding: FragmentConfirmDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        })
        val ans = viewModel.issue.value
        if (ans != null) {
            Log.d("check", ans)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}