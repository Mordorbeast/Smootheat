package com.example.xavi.proyectoxavigimenez.menu

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.elemento_gridlayout_menu_principal.view.*

class MenuAdapter(var context: Context ,var textos : ArrayList<String>) : BaseAdapter() {

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val elemento = convertView ?: layoutInflater.inflate(R.layout.elemento_gridlayout_menu_principal, viewGroup, false)

        elemento.boton.text = textos[position]

        elemento.boton.setOnClickListener{
            when(position){
                0 -> {
                    context.startActivity(Intent(context, PantallaRecetas::class.java))
                }
                1 -> {
                    context.startActivity(Intent(context, PantallaNevera::class.java))
                }
                2 -> {
                    context.startActivity(Intent(context, PantallaAprendeACocinar::class.java))
                }
                3 -> {
                    context.startActivity(Intent(context, PantallaListaCompra::class.java))
                }
            }
        }

        return elemento
    }

    override fun getItem(position: Int): Any {
        return textos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return textos.size
    }

}