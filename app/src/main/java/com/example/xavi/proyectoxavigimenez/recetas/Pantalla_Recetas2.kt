package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import kotlinx.android.synthetic.main.pantalla_receta2.*




class Pantalla_Recetas2 : AppCompatActivity() {

    var receta = Receta("","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_receta2)

        nombreReceta_receta2.requestFocus()

        setSupportActionBar(my_toolbar_receta2 as Toolbar)

        val foto = findViewById<ImageView>(R.id.foto_receta2)
        val ingredientes = findViewById<TextView>(R.id.ingredientes)
        val pasos = findViewById<TextView>(R.id.pasos)
        val video = findViewById<VideoView>(R.id.video_receta2)


        val recetaObtenida = intent.getParcelableExtra<Receta>("receta")
        receta = recetaObtenida

        nombreReceta_receta2.text = receta.nombre
        //foto.setImage = receta.imagen
        ingredientes.text = receta.ingredientes
        pasos.text = receta.pasos
        //video = receta.video

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
