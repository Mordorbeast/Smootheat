package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.android.synthetic.main.content_pantalla_lista_compra.*
import kotlinx.android.synthetic.main.pantalla_lista_compra.*


class PantallaListaCompra : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    val alimentos = ArrayList<Alimento>()

    lateinit var customAdptor:ListaCompraAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.xavi.proyectoxavigimenez.R.layout.pantalla_lista_compra)

        setSupportActionBar(my_toolbar2 as Toolbar)

        val listView = findViewById<ListView>(com.example.xavi.proyectoxavigimenez.R.id.listViewCompra)

        selectDatosLista(listView)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento1")
        var productAdd = intent.getStringExtra("alimento1")


        botonFlotante.setOnClickListener {
            val intent = Intent(this, AddFilaListaCompra::class.java)
            startActivityForResult(intent,AddFilaListaCompra.REQUEST_CODE)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AddFilaListaCompra.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                val name = data!!.getParcelableExtra<Alimento>("alimento1").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento1"))
                customAdptor.notifyDataSetChanged()
                //Toast.makeText(this, getString(R.string.toast_addAlimento) + name, Toast.LENGTH_SHORT).show()

            }
        }
    }


private fun selectDatosLista(listView: ListView){
    db.collection("alimento")
        .whereEqualTo("uso", "listaCompra")
        .addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(value: QuerySnapshot?, e: FirebaseFirestoreException?) {
                if (e != null) {
                    Log.w("PantallaListaCompra", "Listen failed.", e)
                    return
                }

                if (value != null) {
                    for (doc in value) {
                        if (doc.get("nombre") != null) {

                            alimentos.add(
                                Alimento(
                                    doc.getString("nombre")!!,
                                    "",
                                    doc.getString("uso")!!
                                )
                            )


                            customAdptor = ListaCompraAdapter(this@PantallaListaCompra, alimentos)
                            listView.adapter = customAdptor
                            customAdptor.notifyDataSetChanged() //actualiza la listView

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
