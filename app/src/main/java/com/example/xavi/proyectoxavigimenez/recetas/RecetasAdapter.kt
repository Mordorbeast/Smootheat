package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.xavi.proyectoxavigimenez.Receta
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.fila_receta.view.*
import java.util.*



class RecetasAdapter(private val context: Context, var recetas : ArrayList<Receta>): BaseAdapter() {

    private var auxArray2: ArrayList<Receta> = recetas
    private var auxArray: ArrayList<Receta> = arrayListOf(Receta("","","","","",""))
    var storage = FirebaseStorage.getInstance()


    init {
        auxArray.clear()
        auxArray.addAll(recetas)
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = convertView ?: inflater.inflate(com.example.xavi.proyectoxavigimenez.R.layout.fila_receta,parent, false)

        Glide.with(context)
            .load(auxArray2[position].imagen)
            .into(fila.foto_receta)

        fila.nombreReceta_receta.text = auxArray2[position].nombre
        fila.descripcionCorta.text = auxArray2[position].descCorta


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
                if(receta.nombre.toLowerCase(Locale.getDefault()).contains(palabras)){ //Locale.getDefault()
                    auxArray2.add(receta)
                }
            }
        }

        notifyDataSetChanged()
    }

}