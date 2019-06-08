package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.fila_aprende_cocinar.view.*

class AprendeCocinarAdapter(private val context: Activity): BaseAdapter() {

    var tituloVideos = arrayOf("Como pelar patatas", "Como cortar patatas", "Como cortar jamon")

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = convertView ?: inflater.inflate(R.layout.fila_aprende_cocinar, parent, false)

        fila.tituloVideo.text = tituloVideos[position]

        return fila
    }

    override fun getItem(position: Int): Any {
        return tituloVideos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tituloVideos.size
    }



}