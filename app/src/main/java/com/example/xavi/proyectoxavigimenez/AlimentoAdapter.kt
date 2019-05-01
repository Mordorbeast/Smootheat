package com.example.xavi.proyectoxavigimenez

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_fila_lista_compra.view.*

class AlimentoAdapter( var context: Context,  var alimentos : ArrayList<Alimento>) : BaseAdapter(){


    override fun getView(position: Int, convertView: View?, viewGrup: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val fila = layoutInflater.inflate(R.layout.activity_fila_lista_compra, viewGrup, false)
        
        fila.nombreAlimento.text = alimentos[position].alimento

        fila.eliminar.setOnClickListener{
            //Toast.makeText(context, "sUCCESSFULLY LOGGED IN", Toast.LENGTH_LONG).show()
            alimentos.removeAt(position)
            notifyDataSetChanged()

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