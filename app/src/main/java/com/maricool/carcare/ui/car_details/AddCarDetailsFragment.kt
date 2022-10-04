package com.maricool.carcare.ui.car_details

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentAddCarDetailsBinding
import com.maricool.carcare.ui.MainActivity
import com.maricool.carcare.utils.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddCarDetailsFragment : Fragment(R.layout.fragment_add_car_details) {

    private val viewModel: AddCarDetailsViewModel by viewModels()
    private var _binding: FragmentAddCarDetailsBinding? = null
    private val binding get() = _binding!!

    private val args: AddCarDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.resultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    if (result.data?.data == null){
                        viewModel.updateImageNotUploaded()
                    }else{
                        viewModel.updateImageUploaded()
                        viewModel.setNewImage(result.data!!.data!!)
                    }
                }
            }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddCarDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.executePendingBindings()

        viewModel.signInDetails = args.details
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().navigate(R.id.action_addCarDetailsFragment_to_signupFragment)
            }
        })
        setUpObservers()
    }
    private fun setUpObservers() {
        viewModel.snackBarText.observe(viewLifecycleOwner,
            EventObserver { text ->
                view?.showSnackBar(text)
                view?.forceHideKeyboard()
            })

        viewModel.loading.observe(viewLifecycleOwner){
            (activity as MainActivity).showGlobalProgressBar(it)
        }
        viewModel.error.observe(viewLifecycleOwner){
            if (it is InternetError){
                view?.showSnackBar(it.error)
            }
        }
        viewModel.success.observe(viewLifecycleOwner){
            if (it != null)
                requireActivity().showToast(it)
            findNavController().navigate(R.id.action_addCarDetailsFragment_to_homeFragment)
        }
    }

}