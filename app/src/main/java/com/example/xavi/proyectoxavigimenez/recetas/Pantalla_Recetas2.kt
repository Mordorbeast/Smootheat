package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.Pantalla_lista_compra
import com.example.xavi.proyectoxavigimenez.nevera.Pantalla_nevera
import com.example.xavi.proyectoxavigimenez.recetas.Pantalla_Recetas.Companion.REQUEST_CODE
import kotlinx.android.synthetic.main.pantalla_receta2.*




class Pantalla_Recetas2 : AppCompatActivity() {

    var receta = Receta("","","","","",0,"")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_receta2)

        setSupportActionBar(my_toolbar_receta2 as Toolbar)

        val foto = findViewById<ImageView>(R.id.foto_receta2)
        val ingredientes = findViewById<TextView>(R.id.ingredientes)
        val pasos = findViewById<TextView>(R.id.pasos)
        val video = findViewById<VideoView>(R.id.video_receta2)


        val recetaObtenida = intent.getParcelableExtra<Receta>("receta")
        receta = recetaObtenida

        //foto.setImage = receta.imagen
        ingredientes.text = receta.ingredientes
        pasos.text = receta.pasos
        //video = receta.video
    }

/*
    //no entra en este medodo ns porq y por eso no pasa los datos de una pantalla a otra
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                val recetaObtenida = data!!.getParcelableExtra<Receta>("receta")
                receta = recetaObtenida
            }
        }
    }
*/

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent5 = Intent(this, Pantalla_Recetas::class.java)
        val intent6 = Intent(this, Pantalla_nevera::class.java)
        val intent7 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
        val intent8 = Intent(this, Pantalla_lista_compra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)
        }

        return super.onOptionsItemSelected(item)
    }
}
