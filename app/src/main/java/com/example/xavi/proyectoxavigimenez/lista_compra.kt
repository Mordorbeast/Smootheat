package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.*
import kotlinx.android.synthetic.main.activity_pantalla_lista_compra.*
import kotlinx.android.synthetic.main.activity_pantalla_menu_principal.*
import kotlinx.android.synthetic.main.activity_toolbar.*


class lista_compra : AppCompatActivity() {

    companion object {
        const val EMAIL_ALIMENTO = "alimento"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pantalla_lista_compra)

        setSupportActionBar(my_toolbar2 as Toolbar)



        var listView = findViewById<ListView>(R.id.listViewCompra)

        val customAdptor = CustomAdptor(this)
        listView.adapter=customAdptor

        listView.setOnItemClickListener{ parent, view, position, id ->
            Toast.makeText(this, "You Clicked:"+" "+customAdptor.names[position], Toast.LENGTH_SHORT).show()
        }

        add.setOnClickListener(){
            val intent = Intent(this, add_fila_lista_compra::class.java)
            intent.putExtras(getRegisterBundle())
            startActivityForResult(intent,add_fila_lista_compra.REQUEST_CODE)
        }
    }

    private fun getRegisterBundle(): Bundle {
        val bundle = Bundle()
        //bundle.putString(EMAIL_ALIMENTO, a ) ----------------------------------------
        return bundle
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

class CustomAdptor(private val context: Activity): BaseAdapter() {

    //Array of fruits names
    var names =
        arrayOf("Apple", "Strawberry", "Pomegranates", "Oranges", "Watermelon", "Bananas", "Kiwi", "Tomato", "Grapes")

    //Array of fruits desc
    /*var desc = arrayOf(
        "1",
        "2 ",
        "3",
        "4",
        "5",
        "6",
        "7",
        "8",
        "9"
    )*/

    //Array of fruits images
    /*var image = intArrayOf(
        R.drawable.abc_ab_share_pack_mtrl_alpha,
        R.drawable.abc_action_bar_item_background_material,
        R.drawable.abc_btn_check_material,
        R.drawable.abc_btn_check_to_on_mtrl_015,
        R.drawable.abc_btn_radio_to_on_mtrl_000,
        R.drawable.abc_btn_switch_to_on_mtrl_00001,
        R.drawable.abc_cab_background_internal_bg,
        R.drawable.abc_list_selector_holo_dark,
        R.drawable.abc_textfield_default_mtrl_alpha
    )*/


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.layoutInflater
        val view1 = inflater.inflate(R.layout.activity_fila_lista_compra,null)
        //val fimage = view1.findViewById<ImageView>(R.id.fimageView)
        var fName = view1.findViewById<TextView>(R.id.nombreAlimento)
        //var fDesc = view1.findViewById<TextView>(R.id.fDesc)
        //fimage.setImageResource(image[position])
        fName.setText(names[position])
        //fDesc.setText(desc[position])
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