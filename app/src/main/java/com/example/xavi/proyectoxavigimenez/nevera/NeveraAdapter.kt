package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fila_nevera.view.*

class NeveraAdapter(private val context: Context, private val alimentos: ArrayList<Alimento>) : BaseAdapter() {

    var db = FirebaseFirestore.getInstance()

    override fun getView(position: Int, convertView: View?, viewGrup: ViewGroup?): View {

        val layoutInflater = LayoutInflater.from(context)
        val fila = convertView ?: layoutInflater.inflate(R.layout.fila_nevera, viewGrup, false)

        fila.alimento.text = alimentos[position].alimento
        fila.tipoAlimento.text = alimentos[position].tipo

        fila.eliminar_nevera.setOnClickListener{
            val nombreDoc = fila.alimento.text.toString() + "_nevera"

            db.collection("alimento").document(nombreDoc)
                .delete()
                .addOnSuccessListener { Log.d("NeveraAdapter", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w("NeveraAdapter", "Error deleting document", e) }

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