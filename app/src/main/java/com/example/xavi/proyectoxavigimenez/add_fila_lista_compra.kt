package com.example.xavi.proyectoxavigimenez

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.add_fila_lista_compra.*


class add_fila_lista_compra : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE = 1234
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_fila_lista_compra)

        setSupportActionBar(my_toolbar6 as Toolbar)

        add.setOnClickListener(){
            var alimento  = alimento.text.toString()

            //intent.putExtra(EXTRA_alimento,alimento) --------------------------------
            setResult(Activity.RESULT_OK, intent)
            finish()
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