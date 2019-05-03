package com.example.xavi.proyectoxavigimenez.nevera

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.add_fila_nevera.*

class add_fila_neveraActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    var alimento_OK = false
    //var tipoAlimento_OK = false
    var tipos = arrayOf("Frutas", "Verduras y hortalizas", "Leche y derivados", "Carne y embutidos", "Pescados y mariscos", "Huevos", "Legumbres", "Cereales", "Frutos secos", "Bebidas", "Salsas")
    var spinner: Spinner? = null
    var tipoAlimento = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_nevera)

        setSupportActionBar(my_toolbar7 as Toolbar)

        spinner = this.spinner_sample
        spinner!!.onItemSelectedListener = this

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipos)
        // Set layout to use when the list of choices appear
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.adapter = aa

        addAlimento_fila_nevera.setOnClickListener(){

            val aliment = alimento.text.toString()
            //val tipoAliment = tipoAlimento.text.toString()


            if(aliment.trim() == "" || aliment.isEmpty()){
                //Toast.makeText(this, "No puede estar vacio", Toast.LENGTH_SHORT).show()
                alimento.error = "No puede estar vacio"
            }else{
                alimento_OK = true
            }
/*
            if(tipoAliment.trim() == "" || tipoAliment.isEmpty()){
                //Toast.makeText(this, "No puede estar vacio", Toast.LENGTH_SHORT).show()
                tipoAlimento.error = "No puede estar vacio"
            }else{
                tipoAlimento_OK = true
            }
*/
            if(alimento_OK == true){ //&& tipoAlimento_OK == true
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
        menuInflater.inflate(R.menu.menu_action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.menu -> showToast("Menú")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }
}
