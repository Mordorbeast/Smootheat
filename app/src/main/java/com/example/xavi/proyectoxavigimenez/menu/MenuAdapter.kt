package com.example.xavi.proyectoxavigimenez.menu

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.Pantalla_lista_compra
import com.example.xavi.proyectoxavigimenez.nevera.Pantalla_nevera
import com.example.xavi.proyectoxavigimenez.recetas.Pantalla_Recetas
import kotlinx.android.synthetic.main.elemento_gridlayout_menu_principal.view.*

class MenuAdapter(var context: Context ,var textos : ArrayList<String>) : BaseAdapter() {

    var botonApretado:Int = 0

    @SuppressLint("ViewHolder", "InflateParams")
    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(context)
        val elemento = layoutInflater.inflate(R.layout.elemento_gridlayout_menu_principal, null)

        elemento.boton.text = textos[position]

        elemento.boton.setOnClickListener{
            when(position){
                0 -> {
                    val intent1 = Intent(context, Pantalla_Recetas::class.java)
                    context.startActivity(intent1)
                }
                1 -> {
                    val intent2 = Intent(context, Pantalla_nevera::class.java)
                    context.startActivity(intent2)
                }
                2 -> {
                    val intent3 = Intent(context, Pantalla_aprende_a_cocinar::class.java)
                    context.startActivity(intent3)
                }
                3 -> {
                    val intent4 = Intent(context, Pantalla_lista_compra::class.java)
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