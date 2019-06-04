package com.example.xavi.proyectoxavigimenez.lista_compra

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.add_fila_lista_compra.*
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore









class AddFilaListaCompra : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 3
    }

    private var alimento_OK = false

    var db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_lista_compra)

        setSupportActionBar(my_toolbar7 as Toolbar)

        addAlimento_fila_nevera.setOnClickListener(){

            val aliment = alimento.text.toString()

            if(aliment.trim() == "" || aliment.isEmpty()){
                alimento.error = getString(R.string.error_noVacio)
            }else{
                alimento_OK = true
            }

            if(alimento_OK == true){


                val alimento1 = Alimento(aliment, "")

                val intent = Intent()
                intent.putExtra("alimento1",alimento1)
                setResult(Activity.RESULT_OK, intent)

/*
                val alimentos = db.collection("alimentoListaCompra")

                val data1 = HashMap<String, String>()
                data1["nombre"] = aliment

                alimentos.document("6ZX5u4wlv9Og65wZCwuH").set(data1)
*/




                finish()
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
            R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)
        }

        return super.onOptionsItemSelected(item)
    }
}

