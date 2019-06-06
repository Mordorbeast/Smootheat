package com.example.xavi.proyectoxavigimenez.nevera

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import com.example.xavi.proyectoxavigimenez.Alimento
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.recetas.PantallaRecetas
import kotlinx.android.synthetic.main.content_pantalla_nevera_2.*
import kotlinx.android.synthetic.main.pantalla_nevera.*

class PantallaNevera : AppCompatActivity() {

    val alimento1 = Alimento("patatas", "hortaliza", "nevera")
    val alimento2 = Alimento("lomo", "carne", "nevera")
    val alimento3 = Alimento("pepino", "verdura", "nevera")
    val alimento4 = Alimento("manzana", "fruta", "nevera")
    val alimento5 = Alimento("ternera", "carne", "nevera")
    val alimento6 = Alimento("platano", "fruta", "nevera")

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

        botonFlotante.setOnClickListener { view ->
            val intent = Intent(this, AddFilaNevera::class.java)
            startActivityForResult(intent,AddFilaNevera.REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == AddFilaNevera.REQUEST_CODE) {
            if (resultCode == RESULT_OK) {

                val name = data!!.getParcelableExtra<Alimento>("alimento2").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento2"))
                customAdptor.notifyDataSetChanged()
                //Toast.makeText(this, getString(R.string.toast_addAlimento) + name, Toast.LENGTH_SHORT).show()

            }
        }
    }

    //toolbar
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.action_bar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent6 = Intent(this, PantallaNevera::class.java)
        val intent7 = Intent(this, PantallaAprendeACocinar::class.java)
        val intent8 = Intent(this, PantallaListaCompra::class.java)

        when(item?.itemId) {
            R.id.recetas_actionbar -> startActivity(Intent(this, PantallaRecetas::class.java))
            R.id.nevera_actionbar -> startActivity(intent6)
            R.id.aprendeCocinar_actionbar -> startActivity(intent7)
            R.id.listaCompra_actionbar -> startActivity(intent8)
        }

        return super.onOptionsItemSelected(item)
    }

}
