package com.example.xavi.proyectoxavigimenez.recetas

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.xavi.proyectoxavigimenez.R

class RecetasAdapter(private val context: Activity): BaseAdapter() {

    var nombreReceta = arrayOf("patatas", "alcachofa", "pepinos")

    var autor = arrayOf("xavi", "pablo", "uri")

    var image = intArrayOf(
        R.drawable.abc_ab_share_pack_mtrl_alpha,
        R.drawable.abc_btn_check_to_on_mtrl_015,
        R.drawable.abc_btn_radio_to_on_mtrl_000
    )

    //var puntuacion = arrayOf("10", "5", "3")

    //var dificultad = arrayOf("3", "2", "1")

    var descripcion = arrayOf("descripcion de esta receta", "descripcion de esta receta", "descripcion de esta receta")


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val view1 = inflater.inflate(R.layout.fila_receta,null)
        val fimage = view1.findViewById<ImageView>(R.id.foto)
        var fnombre = view1.findViewById<TextView>(R.id.nombreReceta)
        var fautor = view1.findViewById<TextView>(R.id.autor)
        //val fpunt = view1.findViewById<TextView>(R.id.puntuacion)
        //var fdif = view1.findViewById<TextView>(R.id.dificultad)
        var fdesc = view1.findViewById<TextView>(R.id.descripcion)
        fimage.setImageResource(image[position])
        fnombre.text = nombreReceta[position]
        fautor.text = autor[position]
        //fpunt.text = puntuacion[position]
        //fdif.text = dificultad[position]
        fdesc.text = descripcion[position]
        return view1
    }

    override fun getItem(position: Int): Any {
        return descripcion[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return descripcion.size
    }



}