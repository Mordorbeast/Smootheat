package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.AprendeCocinar
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.fila_aprende_cocinar.view.*
import java.util.*

class AprendeCocinarAdapter(private val context: Activity,var videos : ArrayList<AprendeCocinar>): BaseAdapter() {

    private var auxArray: ArrayList<AprendeCocinar> = arrayListOf(AprendeCocinar("",""))
    private var auxArray2: ArrayList<AprendeCocinar> = videos


    init {
        auxArray.clear()
        auxArray.addAll(videos)
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = convertView ?: inflater.inflate(R.layout.fila_aprende_cocinar, parent, false)

        fila.tituloVideo.text = auxArray2[position].tituloVideo

        return fila
    }

    override fun getItem(position: Int): Any {
        return auxArray2[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return auxArray2.size
    }

    fun filtro(palabrasBuscador:String ){
        val palabras = palabrasBuscador.toLowerCase(Locale.getDefault()) //Locale.getDefault()

        auxArray2.clear()

        if(palabras.isEmpty()){
            auxArray2.addAll(auxArray)
        }else{
            for (receta in auxArray){
                if(receta.tituloVideo.toLowerCase(Locale.getDefault()).contains(palabras)){ //Locale.getDefault()
                    auxArray2.add(receta)
                }
            }
        }

        notifyDataSetChanged()
    }

}