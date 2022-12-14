package com.maricool.carcare.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.maricool.carcare.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RewardsFragment : Fragment(R.layout.fragment_rewards) {

    companion object {
        fun newInstance() = RewardsFragment()
    }

    private lateinit var viewModel: RewardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_rewards, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RewardsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}