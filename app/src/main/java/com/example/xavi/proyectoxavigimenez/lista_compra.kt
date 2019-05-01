package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_pantalla_lista_compra.*


class lista_compra : AppCompatActivity() {

    companion object {
        const val ALIMENTO_EXTRA = "alimento"
    }

    val alimento1 = Alimento("patatas")
    val alimento2 = Alimento("arroz")
    val alimento3 = Alimento("pepino")
    val alimento4 = Alimento("manzana")
    val alimento5 = Alimento("galletas")
    val alimento6 = Alimento("platano")

    val alimentos = arrayListOf<Alimento>(alimento1,alimento2,alimento3,alimento4,alimento5,alimento6)

    val customAdptor = AlimentoAdapter(this,alimentos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_lista_compra)

        setSupportActionBar(my_toolbar2 as Toolbar)

        var cfgOptions = intent.getParcelableExtra<Alimento>("alimento1")
        var productAdd = intent.getStringExtra("alimento1")

        val listView = findViewById<ListView>(R.id.listViewCompra)


        listView.adapter=customAdptor

        //customAdptor.names.set(customAdptor.names.count(), ALIMENTO_EXTRA)

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
/*
    private fun getBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(ALIMENTO_EXTRA, alimentoAnadido.text.toString() )
        return bundle
    }
*/

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {

                var name = data!!.getParcelableExtra<Alimento>("alimento1").alimento
                alimentos.add(data.getParcelableExtra<Alimento>("alimento1"))
                customAdptor.notifyDataSetChanged()
                Toast.makeText(this, "Se ha añadido el alimento $name", Toast.LENGTH_SHORT).show()
                //Log.d("Aliemento añadido","Aliemnto: ${name}")

            }
        }
    }
/*
    private fun updateViews(data: Intent) {
        alimentoAnadido.setText(data.getStringExtra(ALIMENTO_EXTRA))
    }
*/

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

/*
class CustomAdptor(private val context: Activity): BaseAdapter() {

    var names =
        arrayOf("Apple", "Strawberry", "Pomegranates", "Oranges", "Watermelon", "Bananas", "Kiwi", "Tomato", "Grapes")


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val view1 = inflater.inflate(R.layout.activity_fila_lista_compra,null)
        var fName = view1.findViewById<TextView>(R.id.nombreAlimento)
        fName.setText(names[position])
        return view1
    }

    override fun getItem(position: Int): Any {
        return names[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return names.size
    }
}
*/