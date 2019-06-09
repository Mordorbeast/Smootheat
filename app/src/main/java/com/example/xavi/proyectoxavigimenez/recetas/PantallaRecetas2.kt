package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.MediaController
import com.bumptech.glide.Glide
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import kotlinx.android.synthetic.main.pantalla_receta2.*






class PantallaRecetas2 : AppCompatActivity() {

    private var receta = Receta("","","","","","")
    private var position = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.xavi.proyectoxavigimenez.R.layout.pantalla_receta2)

        nombreReceta_receta2.requestFocus()

        setSupportActionBar(my_toolbar_receta2 as Toolbar)

        val recetaObtenida = intent.getParcelableExtra<Receta>("receta")
        receta = recetaObtenida

        nombreReceta_receta2.text = receta.nombre

        Glide.with(this)
            .load(receta.imagen)
            .into(foto_receta2)

        ingredientes.text = receta.ingredientes
        pasos.text = receta.pasos

        setUpVideoView()
    }

    private fun setUpVideoView(){
        // Prepara la URI del vídeo que será reproducido.
        val uriPath = receta.video
        val uri = Uri.parse(uriPath)

        // Se crean los controles multimedia.
        val mediaController = MediaController(this)

        // Asigna los controles multimedia a la VideoView.
        video_receta2.setMediaController(mediaController)


        try {
            // Asigna la URI del vídeo que será reproducido a la vista.
            video_receta2.setVideoURI(uri)
            // Se asigna el foco a la VideoView.
            video_receta2.requestFocus()
        } catch (e: Exception) {
            Log.e("Error", e.message)
            e.printStackTrace()
        }


        /*
         * Se asigna un listener que nos informa cuando el vídeo
         * está listo para ser reproducido.
         */
        video_receta2.setOnPreparedListener(videoViewListener)
    }

    private val videoViewListener = MediaPlayer.OnPreparedListener { mediaPlayer ->

         /*
         * Se indica al reproductor multimedia que el vídeo
         * se reproducirá en un loop (on repeat).
         */
        mediaPlayer.isLooping = true

        if (position == 0) {
            /*
                 * Si tenemos una posición en savedInstanceState,
                 * el vídeo debería comenzar desde aquí.
                 */
            video_receta2.start()
        } else {
            /*
                 * Si venimos de un Activity "resumed",
                 * la reproducción del vídeo será pausada.
                 */
            video_receta2.pause()
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        /* Usamos onSaveInstanceState para guardar la posición de
           reproducción del vídeo en caso de un cambio de orientación. */
        savedInstanceState.putInt(
            "Position",
            video_receta2.currentPosition
        )
        video_receta2.pause()
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        /*
         * Usamos onRestoreInstanceState para reproducir el vídeo
         * desde la posición guardada.
         */
        position = savedInstanceState.getInt("Position")
        video_receta2.seekTo(position)
    }


    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(com.example.xavi.proyectoxavigimenez.R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent5 = Intent(this, PantallaRecetas::class.java)
        val intent6 = Intent(this, PantallaNevera::class.java)
        val intent7 = Intent(this, PantallaAprendeACocinar::class.java)
        val intent8 = Intent(this, PantallaListaCompra::class.java)

        when(item?.itemId) {
            com.example.xavi.proyectoxavigimenez.R.id.recetas_actionbar -> startActivity(intent5)
            com.example.xavi.proyectoxavigimenez.R.id.nevera_actionbar -> startActivity(intent6)
            com.example.xavi.proyectoxavigimenez.R.id.aprendeCocinar_actionbar -> startActivity(intent7)
            com.example.xavi.proyectoxavigimenez.R.id.listaCompra_actionbar -> startActivity(intent8)
        }

        return super.onOptionsItemSelected(item)
    }
}
