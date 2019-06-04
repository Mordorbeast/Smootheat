package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.xavi.proyectoxavigimenez.R

class AprendeCocinarAdapter(private val context: Activity): BaseAdapter() {

    var tituloVideos = arrayOf("Como pelar patatas", "Como cortar patatas", "Como cortar jamon")

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater

        val view1 = convertView ?: inflater.inflate(R.layout.fila_aprende_cocinar, parent, false)

        val ftitulo = view1.findViewById<TextView>(R.id.tituloVideo)

        ftitulo.text = tituloVideos[position]

        return view1
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