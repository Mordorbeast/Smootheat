package com.example.xavi.proyectoxavigimenez.recetas

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.ListView
import android.widget.SearchView
import com.example.xavi.proyectoxavigimenez.R
import com.example.xavi.proyectoxavigimenez.Receta
import com.example.xavi.proyectoxavigimenez.aprende_a_cocinar.PantallaAprendeACocinar
import com.example.xavi.proyectoxavigimenez.lista_compra.PantallaListaCompra
import com.example.xavi.proyectoxavigimenez.nevera.PantallaNevera
import kotlinx.android.synthetic.main.pantalla_recetas.*




class PantallaRecetas : AppCompatActivity(), SearchView.OnQueryTextListener{


    //var db = FirebaseFirestore.getInstance()

    val receta1 = Receta("patatas","descripcion brebe","ingredientes xavi","pasos xavi",R.drawable.abc_ab_share_pack_mtrl_alpha,"video")
    val receta2 = Receta("alcachofa","descripcion brebe","ingredientes pablo","pasos pablo",R.drawable.abc_btn_check_to_on_mtrl_015,"video")
    val receta3 = Receta("pepinos","descripcion brebe","ingredientes uri","pasos uri",R.drawable.abc_btn_radio_to_on_mtrl_000,"video")

    val recetas = arrayListOf<Receta>(receta1,receta2,receta3)

    val customAdptor = RecetasAdapter(this, recetas)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pantalla_recetas)

        setSupportActionBar(my_toolbar3 as Toolbar)

        val listView = findViewById<ListView>(R.id.listViewRecetas)

        listView.adapter=customAdptor

        listView.setOnItemClickListener{ _, _, position, _ ->
            val intent = Intent(this, Pantalla_Recetas2::class.java)
            intent.putExtra("receta",customAdptor.recetas[position])
            startActivity(intent)
        }

        val searchView = findViewById<SearchView>(R.id.buscador_receta)
        searchView.setOnQueryTextListener(this)
    }


    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String): Boolean {
        customAdptor.filtro(newText)
        return false
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
            R.id.recetas_actionbar -> startActivity(intent5)
            R.id.nevera_actionbar -> startActivity(intent6)
            R.id.aprendeCocinar_actionbar -> startActivity(intent7)
            R.id.listaCompra_actionbar -> startActivity(intent8)
        }

        return super.onOptionsItemSelected(item)
    }
}


