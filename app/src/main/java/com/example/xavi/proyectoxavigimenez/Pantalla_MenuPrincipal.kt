package com.example.xavi.proyectoxavigimenez

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.lista_compra
import com.example.xavi.proyectoxavigimenez.nevera.Pantalla_nevera
import com.example.xavi.proyectoxavigimenez.recetas.recetas
import kotlinx.android.synthetic.main.pantalla_menu_principal.*

class Pantalla_MenuPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_menu_principal)

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
        val intent5 = Intent(this, recetas::class.java)
        val intent6 = Intent(this, Pantalla_nevera::class.java)
        val intent7 = Intent(this, Pantalla_aprende_a_cocinar::class.java)
        var intent8 = Intent(this, lista_compra::class.java)

        when(item?.itemId) {
            R.id.menu -> showToast("Menú")

            R.id.recetas_actionbar -> startActivityForResult(intent5,1)
            R.id.nevera_actionbar -> startActivityForResult(intent6,1)
            R.id.aprendeCocinar_actionbar -> startActivityForResult(intent7,1)
            R.id.listaCompra_actionbar -> startActivityForResult(intent8,1)









        }

        return super.onOptionsItemSelected(item)
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

}
