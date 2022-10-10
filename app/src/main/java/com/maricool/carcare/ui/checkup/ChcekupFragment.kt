package com.maricool.carcare.ui.checkup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.maricool.carcare.R
import com.maricool.carcare.databinding.FragmentChcekupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChcekupFragment : Fragment(R.layout.fragment_chcekup) {

    private val viewModel: ChcekupViewModel by activityViewModels()

    private var _binding: FragmentChcekupBinding? = null
    private val binding get() = _binding!!
    lateinit var pickerBuilt: MaterialDatePicker<Long>

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
        viewModel.isNow.observe(viewLifecycleOwner) {
            if (!it) {
               showDatePicker()
            }else{
                showTimePicker()
            }
        }
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.executePendingBindings()
        /*viewModel.car.observe(viewLifecycleOwner) {
            if (it != null)
                binding.carDetails = it
        }*/
        binding.shapeableImageView.setOnClickListener {
            findNavController().navigate(R.id.action_chcekupFragment_to_requestServiceFragment)
        }
        binding.arrowSign.setOnClickListener {
            findNavController().navigate(R.id.myCarFragment)
        }
        binding.confirm.setOnClickListener {
            findNavController().navigate(R.id.confirmDetailsFragment)
        }
    }

    private fun showDatePicker(){
        val picker = MaterialDatePicker.Builder
            .datePicker()
        picker.setSelection(MaterialDatePicker.todayInUtcMilliseconds())

        pickerBuilt = picker.build()
        pickerBuilt.show(this.childFragmentManager, "MainActivity")
        pickerBuilt.addOnPositiveButtonClickListener {
            viewModel.selectedDate = pickerBuilt.headerText
            showTimePicker()
        }
    }

    private fun showTimePicker() {
        val materialTimePicker: MaterialTimePicker = MaterialTimePicker.Builder()
            .setTitleText("SELECT YOUR TIMING")
            .setHour(12)
            .setMinute(10)
            .setTimeFormat(TimeFormat.CLOCK_12H)
            .build()
        materialTimePicker.show(this.childFragmentManager, "MainActivity")

        materialTimePicker.addOnPositiveButtonClickListener {

            val pickedHour: Int = materialTimePicker.hour
            val pickedMinute: Int = materialTimePicker.minute
            viewModel.selectedTime = formatTime(pickedHour, pickedMinute)
            Toast.makeText(context, viewModel.selectedTime, Toast.LENGTH_SHORT).show()
        }
    }

    private fun formatTime(pickedHour: Int, pickedMinute:Int): String{
        return  when {
            pickedHour > 12 -> {
                if (pickedMinute < 10) {
                    "${pickedHour - 12}:0${pickedMinute} pm"
                } else {
                    "${pickedHour - 12}:${pickedMinute} pm"
                }
            }
            pickedHour == 12 -> {
                if (pickedMinute < 10) {
                    "${pickedHour}:0${pickedMinute} pm"
                } else {
                    "${pickedHour}:${pickedMinute} pm"
                }
            }
            pickedHour == 0 -> {
                if (pickedMinute < 10) {
                    "${pickedHour + 12}:0${pickedMinute} am"
                } else {
                    "${pickedHour + 12}:${pickedMinute} am"
                }
            }
            else -> {
                if (pickedMinute < 10) {
                    "${pickedHour}:0${pickedMinute} am"
                } else {
                    "${pickedHour}:${pickedMinute} am"
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}