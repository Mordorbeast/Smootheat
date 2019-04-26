package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_aprende_a_cocinar.*

class Pantalla_aprende_a_cocinar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_aprende_a_cocinar)

        setSupportActionBar(my_toolbar5 as Toolbar)

        var listView = findViewById<ListView>(R.id.listView_aprendeCocinar)

        val customAdptor = CustomAdptor4(this)
        listView.adapter=customAdptor

        listView.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, "You Clicked:"+" "+customAdptor.tituloVideos[position], Toast.LENGTH_SHORT).show()
        }
    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu -> showToast("Menú")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
}

class CustomAdptor4(private val context: Activity): BaseAdapter() {

    var tituloVideos = arrayOf("Como pelar patatas", "Como cortar patatas", "Como cortar jamon")

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val view1 = inflater.inflate(R.layout.fila_list_view_aprende_cocinar,null)

        var ftitulo = view1.findViewById<TextView>(R.id.tituloVideo)

        ftitulo.text = tituloVideos[position]

        return view1
    }

    override fun getItem(position: Int): Any {
        return tituloVideos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return tituloVideos.size
    }



}