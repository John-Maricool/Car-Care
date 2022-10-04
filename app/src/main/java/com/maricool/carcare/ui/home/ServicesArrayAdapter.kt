package com.maricool.carcare.ui.home

import android.widget.ArrayAdapter
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.maricool.carcare.R
import com.maricool.carcare.data.entities.ServicesItem
import dagger.hilt.android.qualifiers.ApplicationContext


class ServicesArrayAdapter(@ApplicationContext context: Context, data: ArrayList<ServicesItem>): ArrayAdapter<ServicesItem>(context, 0, data) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var listItem = convertView
        if (listItem == null){
            listItem = LayoutInflater.from(context).inflate(R.layout.home_single_list_item, parent, false)
        }
        val data = getItem(position)
        val img = listItem!!.findViewById<ImageView>(R.id.request_service_img)
        val title = listItem.findViewById<TextView>(R.id.request_service_title)
        val desc = listItem.findViewById<TextView>(R.id.request_service_desc)

        img.setImageResource(data!!.img)
        title.setText(data.title)
        desc.setText(data.desc)
        return listItem
    }
}


