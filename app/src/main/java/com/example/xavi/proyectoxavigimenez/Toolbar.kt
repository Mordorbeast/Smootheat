package com.example.xavi.proyectoxavigimenez

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Toolbar : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toolbar)

        //para hacer la toolbar -> poner en values/styles .NoAcctionBar para quitar la predefinida y añadir lo de abajo
        //ns q hacer para q no falle

        //val myToolbar = findViewById<View>(R.id.my_toolbar) as Toolbar
        //setSupportActionBar(myToolbar)
    }
}
