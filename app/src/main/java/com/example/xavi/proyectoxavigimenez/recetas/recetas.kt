package com.example.xavi.proyectoxavigimenez.recetas

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.pantalla_recetas.*
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.xavi.proyectoxavigimenez.R

class recetas : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_recetas)

        setSupportActionBar(my_toolbar3 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewRecetas)

        val customAdptor = RecetasAdapter(this)
        listView.adapter=customAdptor

        listView.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, "You Clicked:"+" "+customAdptor.nombreReceta[position], Toast.LENGTH_SHORT).show()
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


