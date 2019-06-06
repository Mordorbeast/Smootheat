package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.annotation.Nullable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.android.synthetic.main.content_pantalla_lista_compra.*
import kotlinx.android.synthetic.main.pantalla_lista_compra.*


class PantallaListaCompra : AppCompatActivity() {

    var db = FirebaseFirestore.getInstance()

    val docRef = db.collection("alimento").document("y79liJQes7xEbT37ylZh")


    val alimento1 = Alimento("patatas", "", "listaCompra")
    val alimento2 = Alimento("arroz", "", "listaCompra")
    val alimento3 = Alimento("pepino", "", "listaCompra")
    val alimento4 = Alimento("manzana", "", "listaCompra")
    val alimento5 = Alimento("galletas", "", "listaCompra")
    val alimento6 = Alimento("platano", "", "listaCompra")

    val alimentos = arrayListOf(alimento1,alimento2,alimento3,alimento4,alimento5,alimento6)


    val customAdptor = ListaCompraAdapter(this, alimentos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_lista_compra)

        setSupportActionBar(my_toolbar2 as Toolbar)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento1")
        var productAdd = intent.getStringExtra("alimento1")

        val listView = findViewById<ListView>(R.id.listViewCompra)

        listView.adapter = customAdptor

        botonFlotante.setOnClickListener { view ->
            val intent = Intent(this, AddFilaListaCompra::class.java)
            startActivityForResult(intent,AddFilaListaCompra.REQUEST_CODE)
        }


        docRef.addSnapshotListener(object : EventListener<DocumentSnapshot> {
            override fun onEvent(
                @Nullable snapshot: DocumentSnapshot?,
                @Nullable e: FirebaseFirestoreException?
            ) {
                if (e != null) {
                    Log.w("PantallaListaCompra", "Listen failed.", e)
                    return
                }

                if (snapshot != null && snapshot.exists()) {
                    Log.d("PantallaListaCompra", "Current data: " + snapshot.data!!)
                } else {
                    Log.d("PantallaListaCompra", "Current data: null")
                }
            }
        })


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
