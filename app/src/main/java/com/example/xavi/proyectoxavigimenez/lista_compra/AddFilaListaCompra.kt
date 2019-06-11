package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_fila_lista_compra.*


class AddFilaListaCompra : AppCompatActivity() {
    private var alimentoOk = false

    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.xavi.proyectoxavigimenez.R.layout.add_fila_lista_compra)

        setSupportActionBar(my_toolbar7 as Toolbar)

        addAlimento_fila_nevera.setOnClickListener{

            val aliment = alimento.text.toString()

            if(aliment.trim() == "" || aliment.isEmpty()){
                alimento.error = getString(com.example.xavi.proyectoxavigimenez.R.string.error_noVacio)
            }else{
                alimentoOk = true
            }

            if(alimentoOk){
                val data = HashMap<String, String>()
                data[getString(R.string.bbdd_campo_nombre)] = aliment
                data[getString(R.string.bbdd_campo_tipoAlimento)] = ""
                data[getString(R.string.bbdd_campo_uso)] = getString(R.string.bbdd_campo_uso_listaCompra)

                val nombreDoc = aliment + getString(R.string.bbdd_pk_alimneto_listacompra)

                db.collection(getString(R.string.bbdd_coleccion_alimento)).document(nombreDoc).set(data)

                finish()
            }
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

