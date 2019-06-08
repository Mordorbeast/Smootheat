package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_fila_lista_compra.*


class AddFilaListaCompra : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 3
    }

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
                data["nombre"] = aliment
                data["tipoAlimento"] = ""
                data["uso"] = "listaCompra"

                val nombreDoc = aliment + "_listaCompra"

                db.collection("alimento").document(nombreDoc).set(data)

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
        val intent5 = Intent(this, PantallaRecetas::class.java)
        val intent6 = Intent(this, PantallaNevera::class.java)
        val intent7 = Intent(this, PantallaAprendeACocinar::class.java)
        val intent8 = Intent(this, PantallaListaCompra::class.java)

        when(item?.itemId) {
            com.example.xavi.proyectoxavigimenez.R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            com.example.xavi.proyectoxavigimenez.R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            com.example.xavi.proyectoxavigimenez.R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            com.example.xavi.proyectoxavigimenez.R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)
        }

        return super.onOptionsItemSelected(item)
    }
}

