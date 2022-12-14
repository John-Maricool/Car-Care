package com.maricool.carcare.ui.welcome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentWelcomeScreenBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
@AndroidEntryPoint
class WelcomeScreenFragment : Fragment(R.layout.fragment_welcome_screen) {

    private val viewModel: WelcomeViewModel by viewModels()

    private var _binding: FragmentWelcomeScreenBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentWelcomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().finish()
            }
        })
        binding.viewModel = viewModel
        binding.lifecycleOwner = this.viewLifecycleOwner
        navigate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun navigate() {
        viewModel.loginEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_WelcomeScreenFragment_to_loginFragment)
        }
        viewModel.createAccountEvent.observe(viewLifecycleOwner) {
            findNavController().navigate(R.id.action_WelcomeScreenFragment_to_signupFragment)
        }
    }
}