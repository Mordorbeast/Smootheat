package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.fila_nevera.view.*

class NeveraAdapter(private val context: Context, private val alimentos: ArrayList<Alimento>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGrup: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(context)
        val fila = layoutInflater.inflate(R.layout.fila_nevera, viewGrup, false)

        fila.alimento.text = alimentos[position].alimento

        fila.tipoAlimento.text = alimentos[position].tipo

        fila.eliminar.setOnClickListener{
            alimentos.removeAt(position)
            notifyDataSetChanged()
        }
        return fila

    }

    override fun getItem(position: Int): Any {
        return alimentos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()

    }

    override fun getCount(): Int {
        return alimentos.size
    }

}