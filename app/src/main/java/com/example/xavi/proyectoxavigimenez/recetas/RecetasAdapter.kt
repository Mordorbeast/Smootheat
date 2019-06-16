package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.Receta
import kotlinx.android.synthetic.main.fila_receta.view.*
import java.util.*



class RecetasAdapter(private val context: Context, var recetas : ArrayList<Receta>): BaseAdapter() {

    private var auxArrayRecetas: ArrayList<Receta> = arrayListOf()
    private var auxArrayRecetas2: ArrayList<Receta> = recetas
    private var auxArrayRecetas3: ArrayList<Receta> = arrayListOf()
    var ingredientesNevera: ArrayList<Alimento> = arrayListOf()

   // private var auxArrayNevera: ArrayList<Alimento> = arrayListOf()
    //private var auxArrayNevera2: ArrayList<Alimento> = ingredientesNevera


    init {
        auxArrayRecetas.clear()
        auxArrayRecetas.addAll(recetas)

        //auxArrayNevera.clear()
        //auxArrayNevera2.addAll(ingredientesNevera)
    }

/*
    init {
        auxArrayRecetas.clear()
        auxArrayRecetas.addAll(recetas)
        ingredientesReceta.clear()

        var auxArrayString: List<String>

        for (i in 0 until auxArrayRecetas.size){
            auxArrayString = auxArrayRecetas[i].ingredientes.split(",")
            for (j in 0 until auxArrayString.size){
                ingredientesReceta.add(
                    Alimento(
                        auxArrayString[j],
                        "",
                        "")
                )
            }
        }
    }
*/

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = LayoutInflater.from(context)
        val fila = convertView ?: inflater.inflate(com.example.xavi.proyectoxavigimenez.R.layout.fila_receta,parent, false)

        Glide.with(context)
            .load(auxArrayRecetas2[position].imagen)
            .into(fila.foto_receta)

        fila.nombreReceta_receta.text = auxArrayRecetas2[position].nombre
        fila.descripcionCorta.text = auxArrayRecetas2[position].descCorta

        return fila
    }

    override fun getItem(position: Int): Any {
        return auxArrayRecetas2[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return auxArrayRecetas2.size
    }

    fun filtroBuscador(palabrasBuscador:String ){
        val palabras = palabrasBuscador.toLowerCase()

        auxArrayRecetas2.clear()

        if(palabras.isEmpty()){
            auxArrayRecetas2.addAll(auxArrayRecetas3)
        }else{
            for (receta in auxArrayRecetas3){
                if(receta.nombre.toLowerCase().contains(palabras)){
                    auxArrayRecetas2.add(receta)
                }
            }
        }

        notifyDataSetChanged()
    }

    fun filtroNevera(neveraIngredientes: ArrayList<Alimento>){
        var ingredientesReceta: List<String>
        var contador: Int
        ingredientesNevera = neveraIngredientes

        auxArrayRecetas2.clear()
        auxArrayRecetas3.clear()

        if (!ingredientesNevera.isEmpty()) {
            for (k in 0 until auxArrayRecetas.size) {
                ingredientesReceta = auxArrayRecetas[k].ingredientes.split(",")
                contador = 0

                for (i in 0 until ingredientesReceta.size) {

                    for (j in 0 until ingredientesNevera.size) {
                        if (ingredientesReceta[i] == ingredientesNevera[j].alimento) {
                            contador += 1
                        }
                        if (contador == ingredientesReceta.size) {
                            if(!auxArrayRecetas2.contains(auxArrayRecetas[k])){
                                auxArrayRecetas2.add(auxArrayRecetas[k])
                                auxArrayRecetas3.add(auxArrayRecetas[k])
                            }
                        }
                    }
                }
            }
        }
        notifyDataSetChanged()
    }

}