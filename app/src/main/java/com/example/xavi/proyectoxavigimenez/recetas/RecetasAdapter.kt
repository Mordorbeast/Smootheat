package com.example.xavi.proyectoxavigimenez.recetas

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import kotlinx.android.synthetic.main.fila_receta.view.*

class RecetasAdapter(private val context: Context, var recetas : ArrayList<Receta>): BaseAdapter() {



    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = inflater.inflate(R.layout.fila_receta,null)



        fila.foto_receta2.setImageResource(recetas[position].imagen)
        fila.nombreReceta.text = recetas[position].nombre
        fila.autor.text = recetas[position].autor
        //fila.descripcion.text = recetas[position].descBrebe


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