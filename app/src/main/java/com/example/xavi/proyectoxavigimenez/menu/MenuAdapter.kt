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

    var botonApretado:Int = 0

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val elemento = layoutInflater.inflate(R.layout.elemento_gridlayout_menu_principal, null)

        elemento.boton.text = textos[position]

        elemento.boton.setOnClickListener{
            when(position){
                0 -> {
                    val intent1 = Intent(context, PantallaRecetas::class.java)
                    context.startActivity(intent1)
                }
                1 -> {
                    val intent2 = Intent(context, PantallaNevera::class.java)
                    context.startActivity(intent2)
                }
                2 -> {
                    val intent3 = Intent(context, PantallaAprendeACocinar::class.java)
                    context.startActivity(intent3)
                }
                3 -> {
                    val intent4 = Intent(context, PantallaListaCompra::class.java)
                    context.startActivity(intent4)
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