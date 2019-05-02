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
        when(item?.itemId) {
            R.id.menu -> showToast("Menú")
        }

        return super.onOptionsItemSelected(item)
    }

    private fun showToast(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show()
    }

}
