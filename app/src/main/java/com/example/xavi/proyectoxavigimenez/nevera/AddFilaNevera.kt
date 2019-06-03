package com.example.xavi.proyectoxavigimenez.nevera

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.add_fila_nevera.*

class AddFilaNevera : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var alimento_OK = false
    var tipos = arrayOf("Frutas", "Verduras y hortalizas", "Leche y derivados", "Carne y embutidos", "Pescados y mariscos", "Huevos", "Legumbres", "Cereales", "Frutos secos", "Bebidas", "Salsas", "Otros")
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


        addAlimento_fila_nevera.setOnClickListener(){

            val aliment = alimento.text.toString()

            if(aliment.trim() == "" || aliment.isEmpty()){
                alimento.error = getString(R.string.error_noVacio)
            }else{
                alimento_OK = true
            }

            if(alimento_OK == true){
                val alimento2 = Alimento(aliment, tipoAlimento)

                intent.putExtra("alimento2",alimento2)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }
        }
    }

    //spinner
    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        tipoAlimento = tipos[position]
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

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
