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
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.pantalla_recetas.*




class PantallaRecetas : AppCompatActivity(), SearchView.OnQueryTextListener{

    var db = FirebaseFirestore.getInstance()

    val recetas = ArrayList<Receta>()
    //var ingredientesNevera = ArrayList<Alimento>()
    //var contador: Int = 0

    lateinit var customAdptor:RecetasAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_recetas)

        setSupportActionBar(my_toolbar3 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewRecetas)

        //selectDatosNevera()
        selectDatosLista(listView) //, ingredientesNevera

        listView.setOnItemClickListener{ _, _, position, _ ->
            val intent = Intent(this, PantallaRecetas2::class.java)
            intent.putExtra(getString(R.string.intent_puExtra_receta),customAdptor.recetas[position])
            startActivity(intent)
        }

        val searchView = findViewById<SearchView>(R.id.buscador_receta)
        searchView.setOnQueryTextListener(this)
    }

    private fun selectDatosLista(listView: ListView){ //, ingredientesNevera: ArrayList<Alimento>
        db.collection(getString(R.string.bbdd_coleccion_receta))
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaRecetas", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get(getString(R.string.bbdd_campo_nombreReceta)) != null) {
                                recetas.add(
                                    Receta(
                                        doc.getString(getString(R.string.bbdd_campo_nombreReceta))!!,
                                        doc.getString(getString(R.string.bbdd_campo_descripcionCorta))!!,
                                        doc.getString(getString(R.string.bbdd_campo_ingredientes))!!,
                                        doc.getString(getString(R.string.bbdd_campo_pasos))!!,
                                        doc.getString(getString(R.string.bbdd_campo_foto))!!,
                                        doc.getString(getString(R.string.bbdd_campo_video))!!
                                    )
                                )

                                customAdptor = RecetasAdapter(this@PantallaRecetas, recetas) //, ingredientesNevera
                                listView.adapter = customAdptor
                                customAdptor.notifyDataSetChanged()

                            }
                        }
                    }

                    Log.d("PantallaRecetas", "Recetas del array recetas:")
                    for (i in recetas){
                        Log.d("PantallaRecetas", i.nombre)
                    }

                }
            })
    }
/*
    private fun selectDatosNevera(){
        ingredientesNevera.clear()


        db.collection("alimento")
            .whereEqualTo("uso", "nevera")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaRecetas", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get("nombre") != null) {
                                ingredientesNevera.add(
                                    Alimento(
                                        doc.getString("nombre")!!,
                                        doc.getString("tipoAlimento")!!,
                                        doc.getString("uso")!!
                                    )
                                )
                            }
                        }
                    }

                    Log.d("PantallaRecetas", "array ingredientes nevera:")
                    for (i in recetas){
                        Log.d("PantallaRecetas", i.nombre)
                    }

                }
            })
    }
*/
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


