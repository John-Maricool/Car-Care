package com.maricool.carcare.ui.car_list

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.maricool.carcare.data.entities.CarDetails

@BindingAdapter("setCarsAdapter")
fun setCarsAdapter(
    recyclerView: RecyclerView,
    adapter: MyCarRecyclerAdapter
) {
    adapter.let {
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = it
    }
}


@BindingAdapter("submitCarsList")
fun submitCarsList(recyclerView: RecyclerView, list: List<CarDetails>?) {
    val adapter = recyclerView.adapter as MyCarRecyclerAdapter
    if (list != null)
        adapter.setCarList(list)
}