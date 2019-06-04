package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.pantalla_aprende_a_cocinar.*

class PantallaAprendeACocinar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_aprende_a_cocinar)

        setSupportActionBar(my_toolbar5 as Toolbar)

        val listView = findViewById<ListView>(R.id.listView_aprendeCocinar)

        val customAdptor = AprendeCocinarAdapter(this)
        listView.adapter=customAdptor

        listView.setOnItemClickListener{ _, _, position, _ ->
            Toast.makeText(this, "You Clicked: "+customAdptor.tituloVideos[position], Toast.LENGTH_SHORT).show()
        }
    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent5 = Intent(this, PantallaRecetas::class.java)
        val intent6 = Intent(this, PantallaNevera::class.java)
        val intent7 = Intent(this, PantallaAprendeACocinar::class.java)
        val intent8 = Intent(this, PantallaListaCompra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivity(intent5)
            R.id.nevera_actionbar -> startActivity(intent6)
            R.id.aprendeCocinar_actionbar -> startActivity(intent7)
            R.id.listaCompra_actionbar -> startActivity(intent8)
        }

        return super.onOptionsItemSelected(item)
    }
}

