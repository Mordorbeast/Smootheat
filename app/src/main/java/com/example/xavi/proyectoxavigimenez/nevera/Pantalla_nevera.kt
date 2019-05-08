package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.lista_compra
import com.example.xavi.proyectoxavigimenez.recetas.recetas
import kotlinx.android.synthetic.main.pantalla_nevera.*


class Pantalla_nevera : AppCompatActivity() {

    val alimento1 = Alimento("patatas", "hortaliza")
    val alimento2 = Alimento("lomo", "carne")
    val alimento3 = Alimento("pepino", "verdura")
    val alimento4 = Alimento("manzana", "fruta")
    val alimento5 = Alimento("ternera", "carne")
    val alimento6 = Alimento("platano", "fruta")

    val alimentos = arrayListOf<Alimento>(alimento1,alimento2,alimento3,alimento4,alimento5,alimento6)

    val customAdptor = NeveraAdapter(this, alimentos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_nevera)

        setSupportActionBar(my_toolbar4 as Toolbar)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento2")
        var alimentoAdd = intent.getStringExtra("alimento2")

        val listView = findViewById<ListView>(R.id.listViewNevera)


        listView.adapter=customAdptor

        addAlimento_nevera.setOnClickListener(){
            val intent = Intent(this, add_fila_neveraActivity::class.java)
            startActivityForResult(intent,1)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                var name = data!!.getParcelableExtra<Alimento>("alimento2").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento2"))
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
        val intent5 = Intent(this, recetas::class.java)
        val intent6 = Intent(this, Pantalla_nevera::class.java)
        val intent7 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
        val intent8 = Intent(this, lista_compra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)
        }

        return super.onOptionsItemSelected(item)
    }

}
