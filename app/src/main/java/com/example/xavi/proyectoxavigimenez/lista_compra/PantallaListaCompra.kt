package com.example.xavi.proyectoxavigimenez.lista_compra

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.content_pantalla_lista_compra.*

import kotlinx.android.synthetic.main.pantalla_lista_compra.*

class PantallaListaCompra : AppCompatActivity() {

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

        listView.adapter = customAdptor

        botonFlotante.setOnClickListener { view ->
            val intent = Intent(this, AddFilaListaCompra::class.java)
            startActivityForResult(intent,1)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                val name = data!!.getParcelableExtra<Alimento>("alimento1").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento1"))
                customAdptor.notifyDataSetChanged()
                Toast.makeText(this, getString(R.string.toast_addAlimento) + name, Toast.LENGTH_SHORT).show()

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
