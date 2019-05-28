package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import kotlinx.android.synthetic.main.pantalla_recetas.*
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.Pantalla_aprende_a_cocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.Pantalla_lista_compra
import com.example.xavi.proyectoxavigimenez.nevera.Pantalla_nevera

class Pantalla_Recetas : AppCompatActivity() {

    val receta1 = Receta("patatas","xavi","descripcion brebe","descripcion larga",R.drawable.abc_ab_share_pack_mtrl_alpha,"video")
    val receta2 = Receta("alcachofa","pablo","descripcion brebe","descripcion larga",R.drawable.abc_btn_check_to_on_mtrl_015,"video")
    val receta3 = Receta("pepinos","uri","descripcion brebe","descripcion larga",R.drawable.abc_btn_radio_to_on_mtrl_000,"video")

    val recetas = arrayListOf<Receta>(receta1,receta2,receta3)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_recetas)

        setSupportActionBar(my_toolbar3 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewRecetas)

        val customAdptor = RecetasAdapter(this, recetas)
        listView.adapter=customAdptor

        listView.setOnItemClickListener{ parent, view, position, id ->
            //Toast.makeText(this, "You Clicked:"+" "+customAdptor.nombreReceta[position], Toast.LENGTH_SHORT).show()

            val intent = Intent(this, Pantalla_Recetas2::class.java)
            intent.putExtra("receta",customAdptor.recetas[position])
            startActivityForResult(intent,1)




            //setResult(Activity.RESULT_OK, intent)
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


