package com.example.xavi.proyectoxavigimenez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_pantalla_menu_principal.*

class Pantalla_MenuPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_menu_principal)

        setSupportActionBar(my_toolbar as Toolbar)

        boton_recetas.setOnClickListener{
            val intent1 = Intent(this, recetas::class.java)
            startActivityForResult(intent1,1)
        }

        boton_nevera.setOnClickListener{
            val intent2 = Intent(this, Pantalla_nevera::class.java)
            startActivityForResult(intent2,1)
        }

        boton_aprende_a_cocinar.setOnClickListener{
            val intent3 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
            startActivityForResult(intent3,1)
        }

        boton_lista_compra.setOnClickListener{
            val intent4 = Intent(this, lista_compra::class.java)
            startActivityForResult(intent4,1)
        }


    }


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
