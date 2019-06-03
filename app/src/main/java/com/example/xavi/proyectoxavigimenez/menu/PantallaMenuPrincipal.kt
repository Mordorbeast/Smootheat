package com.example.xavi.proyectoxavigimenez.menu

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.pantalla_menu_principal.*




class PantallaMenuPrincipal : AppCompatActivity(), AdapterView.OnItemClickListener {


    private val textos = arrayListOf<String>("RECETAS","NEVERA","APRENDE A COCINAR","LISTA DE LA COMPRA")
    private val adaptador = MenuAdapter(this, textos)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_menu_principal)

        setSupportActionBar(my_toolbar as Toolbar)

        val gridView = findViewById<GridView>(R.id.gridLayout_menu)

        gridView.adapter = adaptador
    }



    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Toast.makeText(this, "You Clicked: " + adaptador.textos[position], Toast.LENGTH_SHORT).show()
    }



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
