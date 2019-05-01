package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_fila_lista_compra.*
import kotlinx.android.synthetic.main.activity_pantalla_lista_compra.*
import kotlinx.android.synthetic.main.add_fila_lista_compra.*


class add_fila_lista_compra : AppCompatActivity() {
/*
    companion object {
        const val REQUEST_CODE = 1234
    }
*/


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_lista_compra)

        setSupportActionBar(my_toolbar6 as Toolbar)

        val alimentos = arrayListOf<Alimento>()

        addAlimento_fila_lista_compra.setOnClickListener(){

            val alimento = alimento.text.toString()

            if(alimento.trim() == "" || alimento.isEmpty()){
                Toast.makeText(this, "No puede estar vacio", Toast.LENGTH_SHORT).show()
            }else{
                val alimento1 = Alimento(alimento)

                val intent = Intent()
                intent.putExtra("alimento1",alimento1)
                setResult(Activity.RESULT_OK, intent)

                finish()
            }

        }
    }
/*
    private fun getBundle(): Bundle {
        val bundle = Bundle()
        bundle.putString(lista_compra.ALIMENTO_EXTRA, alimento.text.toString())
        return bundle
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

class Alimento(var alimento: String) : Parcelable{
    constructor(parcel: Parcel) : this(
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(alimento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Alimento> {
        override fun createFromParcel(parcel: Parcel): Alimento {
            return Alimento(parcel)
        }

        override fun newArray(size: Int): Array<Alimento?> {
            return arrayOfNulls(size)
        }
    }

}