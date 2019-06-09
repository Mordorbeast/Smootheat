package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.pantalla_recetas.*




class PantallaRecetas : AppCompatActivity(), SearchView.OnQueryTextListener{


    var db = FirebaseFirestore.getInstance()
    var storage = FirebaseStorage.getInstance()

    val recetas = ArrayList<Receta>()

    lateinit var customAdptor:RecetasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_recetas)

        setSupportActionBar(my_toolbar3 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewRecetas)

        selectDatosLista(listView)

        listView.setOnItemClickListener{ _, _, position, _ ->
            val intent = Intent(this, PantallaRecetas2::class.java)
            intent.putExtra("receta",customAdptor.recetas[position])
            startActivity(intent)
        }

        val searchView = findViewById<SearchView>(R.id.buscador_receta)
        searchView.setOnQueryTextListener(this)
    }

    private fun selectDatosLista(listView: ListView){
        db.collection("receta")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaRecetas", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get("nombreReceta") != null) {
                                recetas.add(
                                    Receta(
                                        doc.getString("nombreReceta")!!,
                                        doc.getString("descripcionCorta")!!,
                                        doc.getString("ingredientes")!!,
                                        doc.getString("pasos")!!,
                                        doc.getString("foto")!!,
                                        doc.getString("video")!!
                                    )
                                )


                                customAdptor = RecetasAdapter(this@PantallaRecetas, recetas)
                                listView.adapter = customAdptor
                                customAdptor.notifyDataSetChanged()

                            }
                        }
                    }

                    Log.d("PantallaRecetas", "Recetas del array recetas:")
                    for (i in recetas){
                        Log.d("PantallaRecetas","$i")
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


