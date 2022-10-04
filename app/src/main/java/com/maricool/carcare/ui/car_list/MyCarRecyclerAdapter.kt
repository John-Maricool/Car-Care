package com.maricool.carcare.ui.car_list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.CarDetails
import com.maricool.carcare.databinding.CarListSingleItemBinding
import com.maricool.carcare.utils.SharedPrefsImpl
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class MyCarRecyclerAdapter
@Inject constructor(@ApplicationContext val context: Context, var prefs: SharedPrefsImpl) :
    RecyclerView.Adapter<MyCarRecyclerAdapter.MyCarRecyclerViewHolder>() {


    private var firstTime = 0
    private var selectedItemPos = -1
    private var lastItemSelectedPos = -1
    private var carDetails: List<CarDetails> = listOf()

    inner class MyCarRecyclerViewHolder(val binding: CarListSingleItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(details: CarDetails) {
            binding.carDetails = details
        }

        private val changedColor = context.resources.getColor(R.color.textColorOnBackground, null)
        private val defaultColor = context.resources.getColor(R.color.onBackground, null)

        fun changeBackground() {
            binding.containerCard.background =
                context.resources.getDrawable(R.drawable.card_background_colored, null)
            binding.name.setTextColor(changedColor)
            binding.model.setTextColor(changedColor)
            binding.regNo.setTextColor(changedColor)
            binding.date.setTextColor(changedColor)
        }

        fun defaultBackground() {
            binding.containerCard.background =
                context.resources.getDrawable(R.drawable.card_background, null)
            binding.name.setTextColor(defaultColor)
            binding.model.setTextColor(defaultColor)
            binding.regNo.setTextColor(defaultColor)
            binding.date.setTextColor(defaultColor)
        }

        init {
            firstTime = 1
            binding.containerCard.setOnClickListener {
                firstTime++;
                prefs.saveACarId(carDetails[adapterPosition].carId)
                selectedItemPos = adapterPosition
                if (lastItemSelectedPos == -1)
                    lastItemSelectedPos = selectedItemPos
                else {
                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
                notifyItemChanged(selectedItemPos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCarRecyclerViewHolder {
        val binding = DataBindingUtil.inflate<CarListSingleItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.car_list_single_item,
            parent,
            false
        )
        return MyCarRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyCarRecyclerViewHolder, position: Int) {
        val id = prefs.getSavedCarId()
        if (firstTime == 1) {
            if (carDetails[position].carId == id) {
                lastItemSelectedPos = position
                holder.changeBackground()
            }
        } else {
            if (position == selectedItemPos)
                holder.changeBackground()
            else
                holder.defaultBackground()
        }
        holder.bind(carDetails[position])
    }

    override fun getItemCount(): Int {
        return carDetails.size
    }

    fun setCarList(newCarDetails: List<CarDetails>) {
        carDetails = newCarDetails
        notifyDataSetChanged()
    }
}