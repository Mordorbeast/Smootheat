package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import kotlinx.android.synthetic.main.pantalla_lista_compra.*


class lista_compra : AppCompatActivity() {


    val alimento1 = Alimento("patatas", "")
    val alimento2 = Alimento("arroz", "")
    val alimento3 = Alimento("pepino", "")
    val alimento4 = Alimento("manzana", "")
    val alimento5 = Alimento("galletas", "")
    val alimento6 = Alimento("platano", "")

    val alimentos = arrayListOf<Alimento>(alimento1,alimento2,alimento3,alimento4,alimento5,alimento6)

    val customAdptor = ListaCompraAdapter(this, alimentos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_lista_compra)

        setSupportActionBar(my_toolbar2 as Toolbar)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento1")
        var productAdd = intent.getStringExtra("alimento1")

        val listView = findViewById<ListView>(R.id.listViewCompra)


        listView.adapter=customAdptor


/*
        listView.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, "You Clicked:"+" "+customAdptor.alimentos[position], Toast.LENGTH_SHORT).show()
        }
*/

        addAlimento_lista_compra.setOnClickListener(){
            val intent = Intent(this, add_fila_lista_compra::class.java)
            startActivityForResult(intent,1)
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                var name = data!!.getParcelableExtra<Alimento>("alimento1").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento1"))
                customAdptor.notifyDataSetChanged()
                Toast.makeText(this, "Se ha añadido el alimento $name", Toast.LENGTH_SHORT).show()

            }
        }
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

