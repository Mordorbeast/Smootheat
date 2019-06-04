package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import kotlinx.android.synthetic.main.fila_receta.view.*

class RecetasAdapter(private val context: Context, var recetas : ArrayList<Receta>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = convertView ?: inflater.inflate(R.layout.fila_receta,parent, false)


        fila.foto_receta2.setImageResource(recetas[position].imagen)
        fila.receta2_nombreReceta.text = recetas[position].nombre
        fila.descripcion.text = recetas[position].descCorta


        return fila
    }

    override fun getItem(position: Int): Any {
        return recetas[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return recetas.size
    }



}