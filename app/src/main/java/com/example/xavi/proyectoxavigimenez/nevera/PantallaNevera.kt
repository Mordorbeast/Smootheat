package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.content_pantalla_nevera_2.*
import kotlinx.android.synthetic.main.pantalla_nevera.*

class PantallaNevera : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    val alimentos = ArrayList<Alimento>()

    lateinit var customAdptor:NeveraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_nevera)

        setSupportActionBar(my_toolbar4 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewNevera)

        selectDatosLista(listView)

        botonFlotante.setOnClickListener {
            startActivity(Intent(this, AddFilaNevera::class.java))
        }
    }

    private fun selectDatosLista(listView: ListView){
        db.collection(getString(R.string.bbdd_coleccion_alimento))
            .whereEqualTo(getString(R.string.bbdd_campo_uso), getString(R.string.bbdd_campo_uso_nevera))
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaNevera", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get(getString(R.string.bbdd_campo_nombre)) != null || doc.get(getString(R.string.bbdd_campo_tipoAlimento)) != null) {
                                alimentos.add(
                                    Alimento(
                                        doc.getString(getString(R.string.bbdd_campo_nombre))!!,
                                        doc.getString(getString(R.string.bbdd_campo_tipoAlimento))!!,
                                        doc.getString(getString(R.string.bbdd_campo_uso))!!
                                    )
                                )

                                customAdptor = NeveraAdapter(this@PantallaNevera, alimentos)
                                listView.adapter = customAdptor
                                customAdptor.notifyDataSetChanged()
                            }
                        }
                    }

                    Log.d("PantallaListaCompra", "Alimentos del array alimentos:")
                    for (i in alimentos){
                        Log.d("PantallaListaCompra", "Alimento: ${i.alimento}")
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
