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
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.content_pantalla_nevera_2.*
import kotlinx.android.synthetic.main.pantalla_nevera.*

class PantallaNevera : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()
/*
    val alimento1 = Alimento("patatas", "hortaliza", "nevera")
    val alimento2 = Alimento("lomo", "carne", "nevera")
    val alimento3 = Alimento("pepino", "verdura", "nevera")
    val alimento4 = Alimento("manzana", "fruta", "nevera")
    val alimento5 = Alimento("ternera", "carne", "nevera")
    val alimento6 = Alimento("platano", "fruta", "nevera")

    val alimentos = arrayListOf<Alimento>(alimento1,alimento2,alimento3,alimento4,alimento5,alimento6)

    val customAdptor = NeveraAdapter(this, alimentos)
*/

    val alimentos = ArrayList<Alimento>()

    lateinit var customAdptor:NeveraAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_nevera)

        setSupportActionBar(my_toolbar4 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewNevera)

        selectDatosLista(listView)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento2")
        var alimentoAdd = intent.getStringExtra("alimento2")


        botonFlotante.setOnClickListener {
            val intent = Intent(this, AddFilaNevera::class.java)
            startActivityForResult(intent,AddFilaNevera.REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AddFilaNevera.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                val name = data!!.getParcelableExtra<Alimento>("alimento2").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento2"))
                customAdptor.notifyDataSetChanged()
                //Toast.makeText(this, getString(R.string.toast_addAlimento) + name, Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun selectDatosLista(listView: ListView){
        db.collection("alimento")
            .whereEqualTo("uso", "nevera")
            .addSnapshotListener(object : EventListener<QuerySnapshot> {
                override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                    if (e != null) {
                        Log.w("PantallaNevera", "Listen failed.", e)
                        return
                    }

                    if (value != null) {
                        for (doc in value) {
                            if (doc.get("nombre") != null || doc.get("tipo") != null) {
                                alimentos.add(
                                    Alimento(
                                        doc.getString("nombre")!!,
                                        doc.getString("tipoAlimento")!!,
                                        doc.getString("uso")!!
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
        val intent6 = Intent(this, PantallaNevera::class.java)
        val intent7 = Intent(this, PantallaAprendeACocinar::class.java)
        val intent8 = Intent(this, PantallaListaCompra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivity(Intent(this, PantallaRecetas::class.java))
            R.id.nevera_actionbar -> startActivity(intent6)
            R.id.aprendeCocinar_actionbar -> startActivity(intent7)
            R.id.listaCompra_actionbar -> startActivity(intent8)
        }

        return super.onOptionsItemSelected(item)
    }

}
