package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import com.example.xavi.proyectoxavigimenez.AprendeCocinar
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.pantalla_aprende_a_cocinar.*

class PantallaAprendeACocinar : AppCompatActivity(), SearchView.OnQueryTextListener {

    var db = FirebaseFirestore.getInstance()

    val videos = ArrayList<AprendeCocinar>()

    lateinit var customAdptor: AprendeCocinarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_aprende_a_cocinar)

        setSupportActionBar(my_toolbar5 as Toolbar)

        val listView = findViewById<ListView>(R.id.listView_aprendeCocinar)

        selectDatosLista(listView)

        val searchView = findViewById<SearchView>(R.id.buscador_aprendeCocinar)
        searchView.setOnQueryTextListener(this)
    }

    private fun selectDatosLista(listView: ListView){
        db.collection(getString(R.string.bbdd_coleccion_aprendeCocinar))
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaAprendeCocinar", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get(getString(R.string.bbdd_campo_tituloVideo)) != null) {
                                videos.add(
                                    AprendeCocinar(
                                        doc.getString(getString(R.string.bbdd_campo_tituloVideo))!!,
                                        doc.getString(getString(R.string.bbdd_campo_video))!!
                                    )
                                )

                                customAdptor = AprendeCocinarAdapter(this@PantallaAprendeACocinar, videos)
                                listView.adapter = customAdptor
                                customAdptor.notifyDataSetChanged()

                            }
                        }
                    }

                    Log.d("PantallaAprendeCocinar", "Videos del array videos:")
                    for (i in videos){
                        Log.d("PantallaAprendeCocinar", i.tituloVideo)
                    }

                }
            })
    }

    //searchView
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        customAdptor.filtro(newText)
        return false
    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
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

