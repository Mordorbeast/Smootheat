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
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.nevera.Pantalla_nevera
import com.example.xavi.proyectoxavigimenez.recetas.Pantalla_Recetas
import kotlinx.android.synthetic.main.add_fila_lista_compra.*


class add_fila_lista_compra : AppCompatActivity() {

    var alimento_OK = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_lista_compra)

        setSupportActionBar(my_toolbar7 as Toolbar)

        val alimentos = arrayListOf<Alimento>()

        addAlimento_fila_nevera.setOnClickListener(){

            val aliment = alimento.text.toString()

            if(aliment.trim() == "" || aliment.isEmpty()){
                //Toast.makeText(this, "No puede estar vacio", Toast.LENGTH_SHORT).show()
                alimento.error = "No puede estar vacio"
            }else{
                alimento_OK = true
            }



            if(alimento_OK == true){
                val alimento1 = Alimento(aliment, "")

                val intent = Intent()
                intent.putExtra("alimento1",alimento1)
                setResult(Activity.RESULT_OK, intent)

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
        val intent5 = Intent(this, Pantalla_Recetas::class.java)
        val intent6 = Intent(this, Pantalla_nevera::class.java)
        val intent7 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
        val intent8 = Intent(this, Pantalla_lista_compra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)
        }

        return super.onOptionsItemSelected(item)
    }
}

