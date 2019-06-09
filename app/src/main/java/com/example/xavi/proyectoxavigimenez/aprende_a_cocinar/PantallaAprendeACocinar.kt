package com.example.xavi.proyectoxavigimenez.aprende_a_cocinar

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.xavi.proyectoxavigimenez.AprendeCocinar
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.pantalla_aprende_a_cocinar.*

class PantallaAprendeACocinar : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    val videos = ArrayList<AprendeCocinar>()

    lateinit var customAdptor: AprendeCocinarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_aprende_a_cocinar)

        setSupportActionBar(my_toolbar5 as Toolbar)

        val listView = findViewById<ListView>(R.id.listView_aprendeCocinar)

        selectDatosLista(listView)


    }

    private fun selectDatosLista(listView: ListView){
        db.collection("aprendeCocinar")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaAprendeCocinar", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get("tituloVideo") != null) {
                                videos.add(
                                    AprendeCocinar(
                                        doc.getString("tituloVideo")!!,
                                        doc.getString("video")!!
                                        //doc.getString("video")!!
                                    )
                                )


                                customAdptor = AprendeCocinarAdapter(this@PantallaAprendeACocinar, videos)
                                listView.adapter = customAdptor
                                customAdptor.notifyDataSetChanged()

                            }
                        }
                    }

                    Log.d("PantallaRecetas", "Recetas del array recetas:")
                    for (i in videos){
                        Log.d("PantallaRecetas","$i")
                    }

                }
            })
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

