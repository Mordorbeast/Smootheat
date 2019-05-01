package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.pantalla_aprende_a_cocinar.*

class Pantalla_aprende_a_cocinar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_aprende_a_cocinar)

        setSupportActionBar(my_toolbar5 as Toolbar)

        var listView = findViewById<ListView>(R.id.listView_aprendeCocinar)

        val customAdptor = AprendeCocinarAdapter(this)
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

