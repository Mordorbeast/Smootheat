package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.pantalla_receta2.*






class PantallaRecetas2 : AppCompatActivity() {

    private var receta = Receta("","","","","","")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.xavi.proyectoxavigimenez.R.layout.pantalla_receta2)

        nombreReceta_receta2.requestFocus()

        setSupportActionBar(my_toolbar_receta2 as Toolbar)

        val recetaObtenida = intent.getParcelableExtra<Receta>(getString(R.string.intent_puExtra_receta))
        receta = recetaObtenida

        nombreReceta_receta2.text = receta.nombre

        Glide.with(this)
            .load(receta.imagen)
            .into(foto_receta2)

        ingredientes.text = receta.ingredientes
        pasos.text = receta.pasos

        reproducirVideo_receta2.setOnClickListener{
            val mediaC = MediaController(this)
            val videoPath = receta.video
            val uri = Uri.parse(videoPath)
            video_receta2.setVideoURI(uri)
            video_receta2.setMediaController(mediaC)
            mediaC.setAnchorView(video_receta2)
            video_receta2.start()
        }
    }


    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.xavi.proyectoxavigimenez.R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivity(Intent(this, PantallaRecetas::class.java))
            R.id.nevera_actionbar -> startActivity(Intent(this, PantallaNevera::class.java))
            R.id.aprendeCocinar_actionbar -> startActivity(Intent(this, PantallaAprendeACocinar::class.java))
            R.id.listaCompra_actionbar -> startActivity(Intent(this, PantallaListaCompra::class.java))
            R.id.cerrarSesion_actionbar -> {
                FirebaseAuth.getInstance().signOut()
                startActivity(Intent(this, PantallaLogin::class.java))
            }
        }

        return super.onOptionsItemSelected(item)
    }
}
