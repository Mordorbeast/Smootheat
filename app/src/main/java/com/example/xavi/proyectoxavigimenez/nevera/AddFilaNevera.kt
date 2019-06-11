package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.xavi.proyectoxavigimenez.PantallaLogin
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.add_fila_nevera.*

class AddFilaNevera : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var db = FirebaseFirestore.getInstance()

    private var alimento_OK = false
    private var tipos = arrayOf(
        getString(R.string.nevera_tipo_Frutas),
        getString(R.string.nevera_tipo_Verduras_hortalizas),
        getString(R.string.nevera_tipo_Leche_derivados),
        getString(R.string.nevera_tipo_carne),
        getString(R.string.nevera_tipo_pescado),
        getString(R.string.nevera_tipo_pasta),
        getString(R.string.nevera_tipo_frutosSecos),
        getString(R.string.nevera_tipo_bebidas),
        getString(R.string.nevera_tipo_salsas),
        getString(R.string.nevera_tipo_otros)
    )
    var spinner: Spinner? = null
    var tipoAlimento = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_nevera)

        setSupportActionBar(my_toolbar7 as Toolbar)

        spinner = this.spinner_tiposAlimentos
        spinner!!.onItemSelectedListener = this


        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner!!.adapter = aa


        addAlimento_fila_nevera.setOnClickListener{
            val aliment = alimento.text.toString()

            if(aliment.trim() == "" || aliment.isEmpty()){
                alimento.error = getString(R.string.error_noVacio)
            }else{
                alimento_OK = true
            }

            if(alimento_OK){
                val data = HashMap<String, String>()
                data[getString(R.string.bbdd_campo_nombre)] = aliment
                data[getString(R.string.bbdd_campo_tipoAlimento)] = tipoAlimento
                data[getString(R.string.bbdd_campo_uso)] = getString(R.string.bbdd_campo_uso_nevera)

                val nombreDoc = aliment + getString(R.string.bbdd_pk_alimneto_nevera)

                db.collection(getString(R.string.bbdd_coleccion_alimento)).document(nombreDoc).set(data)

                finish()
            }
        }
    }

    //spinner
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        tipoAlimento = tipos[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        tipoAlimento = tipos[0]
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
