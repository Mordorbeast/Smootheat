package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.xavi.proyectoxavigimenez.Alimento
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fila_lista_compra.view.*
import java.util.*


class ListaCompraAdapter(var context: Context, private var alimentos : ArrayList<Alimento>) : BaseAdapter(){

    var db = FirebaseFirestore.getInstance()

    override fun getView(position: Int, convertView: View?, viewGroup: ViewGroup): View {
        val layoutInflater = LayoutInflater.from(context)
        val fila = convertView ?: layoutInflater.inflate(com.example.xavi.proyectoxavigimenez.R.layout.fila_lista_compra, viewGroup, false)

        fila.nombreAlimento.text = alimentos[position].alimento

        fila.eliminar_listaCompra.setOnClickListener{
            val nombreDoc = fila.nombreAlimento.text.toString() + "_listaCompra"

            db.collection("alimento").document(nombreDoc)
                .delete()
                .addOnSuccessListener { Log.d("ListaCompraAdapter", "DocumentSnapshot successfully deleted!") }
                .addOnFailureListener { e -> Log.w("ListaCompraAdapter", "Error deleting document", e) }

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
