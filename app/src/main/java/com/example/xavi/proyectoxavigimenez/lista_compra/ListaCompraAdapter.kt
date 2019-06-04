package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.fila_lista_compra.view.*

class ListaCompraAdapter(var context: Context, var alimentos : ArrayList<Alimento>) : BaseAdapter(){

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val fila = convertView ?: layoutInflater.inflate(R.layout.fila_lista_compra, viewGroup, false)
        
        fila.nombreAlimento.text = alimentos[position].alimento

        fila.eliminar.setOnClickListener{
            alimentos.removeAt(position)
            notifyDataSetChanged() //actualiza la list view

        }

        return fila
    }

    override fun getItem(i: Int): Any {
        return alimentos[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return alimentos.size
    }


}