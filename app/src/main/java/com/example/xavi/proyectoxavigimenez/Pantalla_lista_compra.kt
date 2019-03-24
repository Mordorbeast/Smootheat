package com.example.xavi.proyectoxavigimenez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView


class Pantalla_lista_compra : AppCompatActivity() {

    var listView : ListView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_lista_compra)

        listView = findViewById(R.id.listView)
        var adapter = ListAdapter(this, generateData())
        listView?.adapter = adapter

        adapter.notifyDataSetChanged()
    }

    private fun generateData(): ArrayList<ListViewAlimentos> {
        var result = ArrayList<ListViewAlimentos>()

        for (i in 0..9){
            var alimento = ListViewAlimentos("Patatas")
        }

        return result
    }
}
